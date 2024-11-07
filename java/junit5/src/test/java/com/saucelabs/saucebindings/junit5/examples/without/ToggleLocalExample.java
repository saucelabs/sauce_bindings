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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

public class ToggleLocalExample {
  WebDriver driver;
  TestInfo testInfo;
  SessionId sessionId;

  @RegisterExtension TestWatcher testWatcher = new CustomTestWatcher();

  // All Sauce specific code must use conditionals to avoid errors
  // This is an example of how a user would toggle those conditionals
  // This property would typically get set in the run command that gets executed on the CI tool
  boolean sauceEnabled() {
    return Boolean.getBoolean("sauce.enabled");
  }

  @BeforeEach
  public void setup(TestInfo testInfo) {
    if (sauceEnabled()) {
      this.testInfo = testInfo;
      driver = new RemoteWebDriver(getSauceUrl(), getSauceCapabilities());
      this.sessionId = ((RemoteWebDriver) driver).getSessionId();
    } else {
      driver = new ChromeDriver(getLocalCapabilities());
    }
  }

  @Test
  public void navigationTest() {
    if (sauceEnabled()) {
      ((JavascriptExecutor) driver).executeScript("sauce:context=Navigating to Swag Labs");
    }

    driver.get("https://www.saucedemo.com/");
  }

  private ChromeOptions getLocalCapabilities() {
    return new ChromeOptions();
  }

  private Capabilities getSauceCapabilities() {
    ChromeOptions options = getLocalCapabilities();
    Map<String, Object> sauceOptions = new HashMap<>();
    sauceOptions.put("username", System.getenv("SAUCE_USERNAME"));
    sauceOptions.put("accessKey", System.getenv("SAUCE_ACCESS_KEY"));
    sauceOptions.put("name", testInfo.getDisplayName());
    sauceOptions.put("build", System.getProperty("build.name"));
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

  private class CustomTestWatcher implements TestWatcher {
    @Override
    public void testSuccessful(ExtensionContext context) {
      if (sauceEnabled()) {
        printSauceResults();
        try {
          ((JavascriptExecutor) driver).executeScript("sauce:job-result=passed");
        } catch (Exception e) {
          System.out.println("problem with using driver: " + e);
        }
      } else {
        System.out.println("Test Successful");
      }

      driver.quit();
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
      if (sauceEnabled()) {
        printSauceResults();
        try {
          ((JavascriptExecutor) driver).executeScript("sauce:job-result=failed");
        } catch (Exception e) {
          System.out.println("problem with using driver: " + e);
        }
      } else {
        System.out.println("Test Failed");
      }

      driver.quit();
    }

    private void printSauceResults() {
      String sauceReporter =
          String.format(
              "SauceOnDemandSessionID=%s job-name=%s", sessionId, testInfo.getDisplayName());
      String sauceTestLink =
          String.format("Test Job Link: https://app.saucelabs.com/tests/%s", sessionId);
      System.out.print(sauceReporter + "\n" + sauceTestLink + "\n");
    }
  }
}
