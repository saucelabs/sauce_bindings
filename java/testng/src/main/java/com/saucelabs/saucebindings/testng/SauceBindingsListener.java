package com.saucelabs.saucebindings.testng;

import com.saucelabs.saucebindings.CITools;
import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;
import lombok.Getter;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

@Getter
public class SauceBindingsListener implements ITestListener {
  private static final Logger LOGGER = Logger.getLogger(SauceBindingsListener.class.getName());
  private static final String buildName = CITools.getBuildName() + ": " + CITools.getBuildNumber();

  public static WebDriver getDriver(ITestContext context) {
    return (WebDriver) context.getAttribute("driver");
  }

  public static SauceSession getSession(ITestContext context) {
    return (SauceSession) context.getAttribute("session");
  }

  public static void startSession(
      SessionConfigurationBuilder builder, Method method, ITestContext context) {
    SauceOptions options = builder.getSauceOptions().copy();
    options.sauce().setBuild(buildName);
    updateTestName(options, method);
    updateCustomData(options);
    updateTags(options, method);

    SauceSession session = new SauceSession(options);
    session.setDataCenter(builder.getDataCenter());
    WebDriver driver = session.start();

    context.setAttribute("session", session);
    context.setAttribute("driver", driver);
  }

  public static void startSession(Method method, ITestContext context) {
    startSession(SessionConfigurationBuilder.defaultConfig(), method, context);
  }

  public static SessionConfigurationBuilder configure() {
    return new SessionConfigurationBuilder();
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    SauceSession session = (SauceSession) result.getTestContext().getAttribute("session");
    if (session == null) {
      LOGGER.warning("Session appears not to have started; check for problems in before hook");
    } else {
      try {
        session.stop(true);
      } catch (NoSuchSessionException e) {
        LOGGER.severe(
            "Driver quit prematurely; Remove calls to `driver.quit()` to allow"
                + " SauceBindingsExtension to stop the test");
      }
    }
  }

  @Override
  public void onTestFailure(ITestResult result) {
    SauceSession session = (SauceSession) result.getTestContext().getAttribute("session");
    if (session == null) {
      LOGGER.warning("Session appears not to have started; check for problems in before hook");
    } else {
      try {
        Throwable cause = result.getThrowable();

        session.annotate("Failure Reason: " + cause.getMessage());

        Arrays.stream(cause.getStackTrace())
            .map(StackTraceElement::toString)
            .filter(line -> !line.contains("sun"))
            .forEach(session::annotate);

        session.stop(false);
      } catch (NoSuchSessionException e) {
        LOGGER.severe(
            "Driver quit prematurely; Remove calls to `driver.quit()` to allow"
                + " SauceBindingsExtension to stop the test");
      }
    }
  }

  private static void updateTestName(SauceOptions options, Method method) {
    String testName = method.getAnnotation(Test.class).description();
    if (testName == null || testName.isEmpty()) {
      testName = method.getDeclaringClass().getSimpleName() + ": " + method.getName();
    }
    options.sauce().setName(testName);
  }

  private static void updateCustomData(SauceOptions options) {
    Properties prop = new Properties();
    try (InputStream input = SauceBindingsListener.class.getResourceAsStream("/app.properties")) {
      prop.load(input);
      String version = prop.getProperty("version", "unknown");
      options.sauce().getCustomData().put("sauce-bindings-testng", version);
    } catch (IOException ignored) {
      options.sauce().getCustomData().put("sauce-bindings-testng", "unknown");
    }
  }

  private static void updateTags(SauceOptions options, Method method) {
    Test testAnnotation = method.getAnnotation(Test.class);
    if (testAnnotation != null) {
      String[] groups = testAnnotation.groups();
      if (groups != null) {
        List<String> tags = options.sauce().getTags();
        tags = tags == null ? new ArrayList<>() : new ArrayList<>(tags);
        tags.addAll(Arrays.asList(groups));
        options.sauce().setTags(Arrays.asList(groups));
      }
    }
  }

  @Getter
  public static class SessionConfigurationBuilder {
    private SauceOptions sauceOptions = new SauceOptions();
    private DataCenter dataCenter = DataCenter.US_WEST;

    public static SessionConfigurationBuilder defaultConfig() {
      return new SessionConfigurationBuilder();
    }

    public SessionConfigurationBuilder withCapabilities(Capabilities capabilities) {
      this.sauceOptions.mergeCapabilities(capabilities.asMap());
      return this;
    }

    public SessionConfigurationBuilder withDataCenter(DataCenter dataCenter) {
      this.dataCenter = dataCenter;
      return this;
    }

    public SessionConfigurationBuilder withSauceOptions(SauceOptions sauceOptions) {
      this.sauceOptions = sauceOptions;
      return this;
    }
  }
}
