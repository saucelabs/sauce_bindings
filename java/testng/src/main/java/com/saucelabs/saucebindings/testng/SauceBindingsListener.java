package com.saucelabs.saucebindings.testng;

import com.saucelabs.saucebindings.SauceSession;
import java.util.logging.Logger;
import lombok.Getter;
import org.openqa.selenium.NoSuchSessionException;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Getter
public class SauceBindingsListener implements ITestListener, IInvokedMethodListener {
  private static final Logger LOGGER = Logger.getLogger(SauceBindingsListener.class.getName());

  public static void enable() {
    System.setProperty("sauce.enabled", "true");
  }

  @Override
  public void beforeInvocation(IInvokedMethod invokedMethod, ITestResult result) {
    if (invokedMethod.isConfigurationMethod() || invokedMethod.isTestMethod()) {
      SessionContext.attachListener(result);
    }
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    SauceSession session = SessionContext.getSession(result);

    try {
      session.stop(true);
    } catch (NoSuchSessionException e) {
      LOGGER.severe(
          "Driver quit prematurely; Remove calls to `driver.quit()` to allow"
              + "  SauceBindingsExtension to stop the test");
    }
  }

  @Override
  public void onTestFailure(ITestResult result) {
    SauceSession session = SessionContext.getSession(result);

    try {
      session.stop(result.getThrowable());
    } catch (NoSuchSessionException e) {
      LOGGER.severe(
          "Driver quit prematurely; Remove calls to `driver.quit()` to allow"
              + "  SauceBindingsExtension to stop the test");
    }
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    LOGGER.info(
        "A Sauce session was not started for "
            + result.getTestName()
            + " because "
            + result.getSkipCausedBy());
  }
}
