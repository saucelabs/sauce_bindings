package com.saucelabs.saucebindings.junit5.examples;

import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.junit5.SauceBindingsExtension;
import com.saucelabs.saucebindings.options.SauceOptions;
import java.time.Duration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ToggleLocalExample {
  WebDriver driver;
  SauceSession session;

  @RegisterExtension
  static SauceBindingsExtension sauceExtension = new SauceBindingsExtension(getSauceOptions());

  @RegisterExtension TestWatcher testWatcher = new LocalTestWatcher();

  // To run test on Sauce Labs, change this to "false"
  @BeforeAll
  public static void disableSauce() {
    System.setProperty("sauce.disabled", "true");
  }

  @BeforeEach
  public void setup() {
    if (isSauceDisabled()) {
      driver = new ChromeDriver();
    } else {
      session = sauceExtension.getSession();
      driver = sauceExtension.getDriver();
    }
  }

  @AfterEach
  public void tearDown() {
    if (isSauceDisabled()) {
      driver.quit();
    }
  }

  @AfterAll
  public static void resetSauce() {
    System.clearProperty("sauce.disabled");
  }

  @Test
  public void localExample() {
    if (!isSauceDisabled()) {
      session.annotate("Navigating to Swag Labs");
    }
    driver.get("https://www.saucedemo.com/");
  }

  private static SauceOptions getSauceOptions() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--hide-scrollbars");

    return SauceOptions.chrome(chromeOptions)
        .setPlatformName(SaucePlatform.MAC_CATALINA)
        .setIdleTimeout(Duration.ofSeconds(30))
        .build();
  }

  private boolean isSauceDisabled() {
    String value = System.getenv("SAUCE_DISABLED");
    return Boolean.parseBoolean(value) || Boolean.getBoolean("sauce.disabled");
  }

  public class LocalTestWatcher implements TestWatcher {
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
      ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    }
  }
}
