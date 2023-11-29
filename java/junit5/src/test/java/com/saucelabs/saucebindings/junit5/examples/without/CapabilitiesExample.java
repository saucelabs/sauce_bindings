package com.saucelabs.saucebindings.junit5.examples.without;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.safari.SafariOptions;

public class CapabilitiesExample {
  WebDriver driver;
  TestInfo testInfo;
  SessionId sessionId;

  @RegisterExtension public SauceTestWatcher watcher = new SauceTestWatcher();

  @BeforeEach
  public void startSession(TestInfo testInfo) {
    this.testInfo = testInfo;
    driver = new RemoteWebDriver(getSauceUrl(), getCapabilities());
    sessionId = ((RemoteWebDriver) driver).getSessionId();
  }

  @Test
  public void capabilitiesExample() {
    ((JavascriptExecutor) driver).executeScript("sauce:context=Navigating to Swag Labs");
    driver.get("https://www.saucedemo.com/");
  }

  private Capabilities getCapabilities() {
    SafariOptions options = new SafariOptions();
    options.setPlatformName("macOS 12");
    options.setBrowserVersion("latest");
    Map<String, Object> sauceOptions = new HashMap<>();
    sauceOptions.put("idleTimeout", 30);
    options.setCapability("sauce:options", sauceOptions);

    return options;
  }

  private URL getSauceUrl() {
    try {
      return new URL("https://ondemand.us-west-1.saucelabs.com/wd/hub");
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
  }

  private class SauceTestWatcher implements TestWatcher {
    @Override
    public void testSuccessful(ExtensionContext context) {
      printResults();
      try {
        ((JavascriptExecutor) driver).executeScript("sauce:job-result=passed");
        driver.quit();
      } catch (Exception e) {
        System.out.println("problem with using driver: " + e);
      }
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
      printResults();

      try {
        ((JavascriptExecutor) driver).executeScript("sauce:job-result=failed");
        driver.quit();
      } catch (Exception e) {
        System.out.println("problem with using driver: " + e);
      }
    }

    private void printResults() {
      String sauceReporter =
          String.format(
              "SauceOnDemandSessionID=%s job-name=%s", sessionId, testInfo.getDisplayName());
      String sauceTestLink =
          String.format("Test Job Link: https://app.saucelabs.com/tests/%s", sessionId);
      System.out.print(sauceReporter + "\n" + sauceTestLink + "\n");
    }
  }
}
