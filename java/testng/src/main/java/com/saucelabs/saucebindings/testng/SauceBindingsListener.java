package com.saucelabs.saucebindings.testng;

import com.saucelabs.saucebindings.SauceSession;

import java.util.Arrays;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import lombok.Getter;
import org.openqa.selenium.NoSuchSessionException;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Getter
public class SauceBindingsListener implements ITestListener, IInvokedMethodListener {
  private static final Logger LOGGER = Logger.getLogger(SauceBindingsListener.class.getName());

  @Override
  public void beforeInvocation(IInvokedMethod invokedMethod, ITestResult result) {
    if (invokedMethod.isConfigurationMethod() || invokedMethod.isTestMethod()) {
      SessionContext.setEnabled(result);
    }
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    SauceSession session = SessionContext.getSession(result);

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
    SauceSession session = SessionContext.getSession(result);

    if (session == null) {
      LOGGER.warning("Session appears not to have started; check for problems in before hook");
    } else {
      try {
        Throwable cause = result.getThrowable();

        session.annotate("Failure Reason: " + cause.getMessage());

        String stackTrace =
            Arrays.stream(cause.getStackTrace())
                .map(StackTraceElement::toString)
                .collect(Collectors.joining("\n"));
        session.annotate(stackTrace);

        session.stop(false);
      } catch (NoSuchSessionException e) {
        LOGGER.severe(
            "Driver quit prematurely; Remove calls to `driver.quit()` to allow"
                + " SauceBindingsExtension to stop the test");
      }
    }
  }
}
