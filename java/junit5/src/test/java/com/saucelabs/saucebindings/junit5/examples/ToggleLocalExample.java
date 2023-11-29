package com.saucelabs.saucebindings.junit5.examples;

import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.junit5.SauceBindingsExtension;
import com.saucelabs.saucebindings.options.SauceOptions;
import java.time.Duration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
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
    // TODO: Allow getting session even when disabled
    if (isSauceEnabled()) {
      session = sauceExtension.getSession();
      driver = sauceExtension.getDriver();
    } else {
      driver = new ChromeDriver(getCapabilities());
    }
  }

  @Test
  public void localExample() {
    // TODO: Allow this method to be ignored if Sauce is disabled
    if (isSauceEnabled()) {
      session.annotate("Navigating to Swag Labs");
    }
    driver.get("https://www.saucedemo.com/");
  }

  private static SauceOptions getSauceOptions() {
    ChromeOptions chromeOptions = getCapabilities();

    return SauceOptions.chrome(chromeOptions)
        .setPlatformName(SaucePlatform.MAC_CATALINA)
        .setIdleTimeout(Duration.ofSeconds(30))
        .build();
  }

  private static ChromeOptions getCapabilities() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--hide-scrollbars");

    return chromeOptions;
  }

  // TODO: Implement this as a method in SauceSession directly
  private boolean isSauceEnabled() {
    String value = System.getenv("SAUCE_DISABLED");
    return Boolean.parseBoolean(value) || Boolean.getBoolean("sauce.disabled");
  }

  public class LocalTestWatcher implements TestWatcher {
    @Override
    public void testSuccessful(ExtensionContext context) {
      if (!isSauceEnabled()) {
        System.out.println("Test Succeeded");
        driver.quit();
      }
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
      if (!isSauceEnabled()) {
        System.out.println("Test Failed");
        driver.quit();
      }
    }
  }
}
