package com.saucelabs.saucebindings.testng;

import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;
import lombok.Setter;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class SauceBindingsListener implements ITestListener {
  private static final Logger LOGGER = Logger.getLogger(SauceBindingsListener.class.getName());
  @Setter private static SauceOptions sauceOptions = new SauceOptions();
  @Setter private static DataCenter dataCenter = DataCenter.US_WEST;

  public static void setCapabilities(Capabilities capabilities) {
    SauceOptions options = new SauceOptions();

    // TODO: Update Sauce Bindings to handle "sauce:options" the same as it handles "sauce
    Map<String, Object> capabilitiesMap = new HashMap<>(capabilities.asMap());
    Optional.ofNullable(capabilitiesMap.get("sauce:options"))
        .filter(Map.class::isInstance)
        .map(Map.class::cast)
        .ifPresent(
            sauceOptionsMap -> {
              capabilitiesMap.put("sauce", sauceOptionsMap);
              capabilitiesMap.remove("sauce:options");
            });

    options.mergeCapabilities(capabilitiesMap);
    setSauceOptions(options);
  }

  public static WebDriver getDriver(ITestContext context) {
    return (WebDriver) context.getAttribute("driver");
  }

  public static SauceSession getSession(ITestContext context) {
    return (SauceSession) context.getAttribute("session");
  }

  public static void startSession(Method method, ITestContext context) {
    if (sauceOptions.sauce().getName() == null) {
      sauceOptions.sauce().setName(method.getName());
    }
    SauceSession session = new SauceSession(sauceOptions);
    session.setDataCenter(dataCenter);
    WebDriver driver = session.start();

    context.setAttribute("session", session);
    context.setAttribute("driver", driver);
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
}
