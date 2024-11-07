package com.saucelabs.saucebindings.junit4;

import com.saucelabs.saucebindings.CITools;
import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Logger;
import lombok.Getter;
import org.junit.AssumptionViolatedException;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;

@Getter
public class SauceBindingsWatcher extends TestWatcher {
  private static final Logger LOGGER = Logger.getLogger(SauceBindingsWatcher.class.getName());
  private final SauceOptions sauceOptions;
  private final DataCenter dataCenter;
  private SauceSession session;
  private WebDriver driver;
  private static final String build = CITools.getBuildName() + ": " + CITools.getBuildNumber();

  public SauceBindingsWatcher() {
    this(new SauceOptions(), DataCenter.US_WEST);
  }

  private SauceBindingsWatcher(SauceOptions sauceOptions, DataCenter dataCenter) {
    this.sauceOptions = sauceOptions;
    this.dataCenter = dataCenter;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static void enable() {
    System.setProperty("sauce.enabled", "true");
  }

  @Override
  protected void starting(Description description) {
    sauceOptions.sauce().setBuild(build);
    updateTestName(description);
    updateCustomData();
    updateTags(description);

    session = new SauceSession(sauceOptions);
    session.setDataCenter(dataCenter);
    driver = session.start();
  }

  public void updateTestName(Description description) {
    String className = description.getClassName();
    String simpleClassName = className.substring(className.lastIndexOf(".") + 1);
    sauceOptions.sauce().setName(simpleClassName + ": " + description.getMethodName());
  }

  private void updateCustomData() {
    Properties prop = new Properties();
    try (InputStream input = getClass().getResourceAsStream("/app.properties")) {
      prop.load(input);
      String version = prop.getProperty("version", "unknown");
      sauceOptions.sauce().getCustomData().put("sauce-bindings-junit4", version);
    } catch (IOException ignored) {
      sauceOptions.sauce().getCustomData().put("sauce-bindings-junit4", "unknown");
    }
  }

  private void updateTags(Description description) {
    List<String> tags =
        Objects.requireNonNullElseGet(sauceOptions.sauce().getTags(), ArrayList::new);

    List<Annotation> annotations = (List<Annotation>) description.getAnnotations();
    for (Annotation annotation : annotations) {
      if (annotation instanceof Category) {
        Category category = (Category) annotation;
        for (Class<?> categoryClass : category.value()) {
          tags.add(categoryClass.getSimpleName());
        }
      }
    }

    if (!tags.isEmpty()) {
      sauceOptions.sauce().setTags(tags);
    }
  }

  @Override
  protected void succeeded(Description description) {
    try {
      session.stop(true);
    } catch (NoSuchSessionException e) {
      LOGGER.severe(
          "Driver quit prematurely; Remove calls to `driver.quit()` to allow"
              + " SauceBindingsExtension to stop the test");
    }
  }

  @Override
  protected void failed(Throwable e, Description description) {
    if (session != null) {
      try {
        session.stop(e);
      } catch (NoSuchSessionException ex) {
        LOGGER.severe(
            "Driver quit prematurely; Remove calls to `driver.quit()` to allow"
                + " SauceBindingsExtension to stop the test");
      }
    }
  }

  @Override
  public void skipped(AssumptionViolatedException e, Description description) {
    LOGGER.fine("Test Aborted: " + e.getMessage());
    if (session != null) {
      session.abort(e);
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

    public SauceBindingsWatcher build() {
      return new SauceBindingsWatcher(sauceOptions, dataCenter);
    }
  }
}
