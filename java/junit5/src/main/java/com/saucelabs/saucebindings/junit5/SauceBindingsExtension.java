package com.saucelabs.saucebindings.junit5;

import com.saucelabs.saucebindings.CITools;
import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.SystemManager;
import com.saucelabs.saucebindings.options.SauceOptions;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Logger;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.yaml.snakeyaml.Yaml;

public class SauceBindingsExtension implements TestWatcher, BeforeEachCallback, ParameterResolver {
  private static final Logger LOGGER = Logger.getLogger(SauceBindingsExtension.class.getName());
  private final String build;
  protected SauceOptions sauceOptions;
  protected DataCenter dataCenter;

  public SauceBindingsExtension() {
    this(new SauceOptions(), DataCenter.US_WEST);
  }

  private SauceBindingsExtension(SauceOptions sauceOptions, DataCenter dataCenter) {
    if (Objects.equals(SystemManager.get("SAUCE_CONFIG_OVERRIDE"), "true")) {
      File defaultLocation = new File(System.getProperty("user.dir") + "/sauce-config.yml");
      this.sauceOptions = optionsFromConfig(defaultLocation);
    } else {
      this.sauceOptions = sauceOptions;
    }

    this.dataCenter = dataCenter;
    this.build = CITools.getBuildName() + ": " + CITools.getBuildNumber();
  }

  @SneakyThrows
  static SauceOptions optionsFromConfig(File location) {
    InputStream input = new FileInputStream(location);
    Yaml yaml = new Yaml();
    Map<String, Object> data = yaml.load(input);
    List<String> topLevelKeys = new ArrayList<>(data.keySet());

    String configKey =
        System.getProperty(
            "sauce.platform.key",
            System.getenv().getOrDefault("SAUCE_PLATFORM_KEY", topLevelKeys.get(0)));

    Map<String, Object> configData = (Map<String, Object>) data.get(configKey);
    SauceOptions options = new SauceOptions();
    options.mergeCapabilities(configData);
    return options;
  }

  public void enable() {
    System.setProperty("sauce.enabled", "true");
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public void beforeEach(ExtensionContext context) {
    SauceOptions options = updateOptions(context);
    SauceSession session = new SauceSession(options);
    session.setDataCenter(dataCenter);
    WebDriver driver = session.start();

    getStore(context).put("session", session);
    getStore(context).put("driver", driver);
  }

  private SauceOptions updateOptions(ExtensionContext context) {
    SauceOptions options = sauceOptions.copy();
    options.sauce().setBuild(build);
    updateTestName(options, context);
    updateCustomData(options);
    updateTags(options, context);

    return options;
  }

  private void updateTestName(SauceOptions options, ExtensionContext context) {
    // Use value specified by @DisplayName annotation if present
    if (context.getRequiredTestMethod().getDeclaredAnnotation(DisplayName.class) != null) {
      options.sauce().setName(context.getDisplayName());
    } else {
      String className = context.getRequiredTestClass().getSimpleName();
      String methodName = context.getRequiredTestMethod().getName();
      options.sauce().setName(className + ": " + methodName);
    }
  }

  private void updateCustomData(SauceOptions options) {
    Properties prop = new Properties();
    try (InputStream input = getClass().getResourceAsStream("/app.properties")) {
      prop.load(input);
      String version = prop.getProperty("version", "unknown");
      options.sauce().getCustomData().put("sauce-bindings-junit5", version);
    } catch (IOException ignored) {
      options.sauce().getCustomData().put("sauce-bindings-junit5", "unknown");
    }
  }

  private void updateTags(SauceOptions options, ExtensionContext context) {
    List<String> tags = options.sauce().getTags();
    if (tags != null) {
      tags.addAll(context.getTags());
    } else {
      options.sauce().setTags(new ArrayList<>(context.getTags()));
    }
  }

  private ExtensionContext.Store getStore(ExtensionContext context) {
    return context.getStore(
        ExtensionContext.Namespace.create(getClass(), context.getRequiredTestMethod()));
  }

  @Override
  public void testSuccessful(ExtensionContext context) {
    SauceSession session = (SauceSession) getStore(context).get("session");
    try {
      session.stop(true);
    } catch (NoSuchSessionException e) {
      LOGGER.severe(
          "Driver quit prematurely; Remove calls to `driver.quit()` to allow"
              + "  SauceBindingsExtension to stop the test");
    }
  }

  @Override
  public void testFailed(ExtensionContext context, Throwable cause) {
    SauceSession session = (SauceSession) getStore(context).get("session");
    try {
      session.stop(cause);
    } catch (NoSuchSessionException e) {
      LOGGER.severe(
          "Driver quit prematurely; Remove calls to `driver.quit()` to allow"
              + "  SauceBindingsExtension to stop the test");
    }
  }

  @Override
  public void testAborted(ExtensionContext context, Throwable cause) {
    LOGGER.fine("Test Aborted: " + cause.getMessage());
    SauceSession session = (SauceSession) getStore(context).get("session");
    if (session != null) {
      session.abort(cause);
    }
  }

  @Override
  public void testDisabled(ExtensionContext context, Optional<String> reason) {
    LOGGER.info(
        "A Sauce session was not started for " + context.getDisplayName() + " because " + reason);
  }

  @Override
  public boolean supportsParameter(
      ParameterContext parameterContext, ExtensionContext extensionContext) {
    boolean session = parameterContext.getParameter().getType() == SauceSession.class;
    boolean driver = parameterContext.getParameter().getType() == WebDriver.class;
    return session || driver;
  }

  @Override
  public Object resolveParameter(
      ParameterContext parameterContext, ExtensionContext extensionContext) {
    if (parameterContext.getParameter().getType() == WebDriver.class) {
      return getStore(extensionContext).get("driver");
    } else if (parameterContext.getParameter().getType() == SauceSession.class) {
      return getStore(extensionContext).get("session");
    } else {
      throw new RuntimeException("Only session and driver instances are supported");
    }
  }

  public static class Builder {
    private SauceOptions sauceOptions = new SauceOptions();
    private DataCenter dataCenter = DataCenter.US_WEST;

    public Builder withSauceOptions(SauceOptions sauceOptions) {
      this.sauceOptions = sauceOptions;
      return this;
    }

    public Builder withCapabilities(Capabilities capabilities) {
      this.sauceOptions.mergeCapabilities(capabilities.asMap());
      return this;
    }

    public Builder withDataCenter(DataCenter dataCenter) {
      this.dataCenter = dataCenter;
      return this;
    }

    @SneakyThrows
    public Builder withConfigFile(File file) {
      this.sauceOptions = optionsFromConfig(file);
      return this;
    }

    public SauceBindingsExtension build() {
      return new SauceBindingsExtension(sauceOptions, dataCenter);
    }
  }
}
