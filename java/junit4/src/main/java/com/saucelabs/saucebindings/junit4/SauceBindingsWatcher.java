package com.saucelabs.saucebindings.junit4;

import com.saucelabs.saucebindings.CITools;
import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;
import lombok.Getter;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;

@Getter
public class SauceBindingsWatcher extends TestWatcher {
  private static final Logger logger = Logger.getLogger(SauceBindingsWatcher.class.getName());
  private final SauceOptions sauceOptions;
  private final DataCenter dataCenter;
  private SauceSession session;
  private WebDriver driver;
  private static final String buildName = CITools.getBuildName() + ": " + CITools.getBuildNumber();

  public SauceBindingsWatcher() {
    this(new SauceOptions(), DataCenter.US_WEST);
  }

  private SauceBindingsWatcher(SauceOptions sauceOptions, DataCenter dataCenter) {
    this.sauceOptions = sauceOptions;
    this.dataCenter = dataCenter;
  }

  @Override
  protected void starting(Description description) {
    updateOptions(description);

    session = new SauceSession(sauceOptions);
    session.setDataCenter(dataCenter);
    driver = session.start();
  }

  private void updateOptions(Description description) {
    String className = description.getClassName();
    String simpleClassName = className.substring(className.lastIndexOf(".") + 1);
    sauceOptions.sauce().setName(simpleClassName + ": " + description.getMethodName());
    sauceOptions.sauce().setBuild(buildName);

    Properties prop = new Properties();
    try (InputStream input = getClass().getResourceAsStream("/app.properties")) {
      prop.load(input);
      String version = prop.getProperty("version", "unknown");
      sauceOptions.sauce().getCustomData().put("sauce-bindings-junit4", version);
    } catch (IOException ignored) {
      sauceOptions.sauce().getCustomData().put("sauce-bindings-junit4", "unknown");
    }

    List<String> tags = sauceOptions.sauce().getTags();

    List<Annotation> annotations = (List<Annotation>) description.getAnnotations();
    for (Annotation annotation : annotations) {
      if (annotation instanceof Category) {
        Category category = (Category) annotation;
        for (Class<?> categoryClass : category.value()) {
          if (tags == null) {
            tags = new ArrayList<>();
          }
          tags.add(categoryClass.getSimpleName());
        }
      }
    }

    if (tags != null) {
      sauceOptions.sauce().setTags(tags);
    }
  }

  @Override
  protected void succeeded(Description description) {
    try {
      session.stop(true);
    } catch (NoSuchSessionException e) {
      logger.severe(
          "Driver quit prematurely; Remove calls to `driver.quit()` to allow"
              + " SauceBindingsExtension to stop the test");
    }
  }

  @Override
  protected void failed(Throwable e, Description description) {
    if (session != null) {
      try {
        session.annotate("Failure Reason: " + e.getMessage());

        Arrays.stream(e.getStackTrace())
            .map(StackTraceElement::toString)
            .filter(line -> !line.contains("sun"))
            .forEach(session::annotate);

        session.stop(false);
      } catch (NoSuchSessionException ex) {
        logger.severe(
            "Driver quit prematurely; Remove calls to `driver.quit()` to allow"
                + " SauceBindingsExtension to stop the test");
      }
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
