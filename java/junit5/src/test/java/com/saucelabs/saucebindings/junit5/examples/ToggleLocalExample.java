package com.saucelabs.saucebindings.junit5.examples;

import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.junit5.SauceBindingsExtension;
import com.saucelabs.saucebindings.options.SauceOptions;
import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
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

  // Allow registering multiple test watchers
  @RegisterExtension
  static SauceBindingsExtension sauceExtension =
          new SauceBindingsExtension.Builder().withSauceOptions(getSauceOptions()).build();

  @RegisterExtension TestWatcher testWatcher = new LocalTestWatcher();

  // Run tests with this property set to "false" to execute on Sauce Labs
  @BeforeAll
  public static void disableSauce() {
    System.setProperty("sauce.disabled", "true");
  }

  @BeforeEach
  public void setUp(SauceSession session, WebDriver driver) {
    this.session = session;
    this.driver = SauceSession.isDisabled() ? new ChromeDriver() : driver;
  }

  @Test
  public void localExample() {
    // This code executes whether running locally or on Sauce
    driver.get("https://www.saucedemo.com/");

    // This code executes if Sauce enabled, and is ignored when disabled
    Assertions.assertDoesNotThrow(
        () -> {
          session.annotate("This gets ignored");
          session.addTags(List.of("ignored"));
          session.stopNetwork();
          session.enableLogging();
          session.getAccessibilityResults();
        });
  }

  private static SauceOptions getSauceOptions() {
    ChromeOptions chromeOptions = getCapabilities();

    return SauceOptions.chrome(chromeOptions)
        .setPlatformName(SaucePlatform.MAC_CATALINA)
        .setIdleTimeout(Duration.ofSeconds(30))
        .build();
  }

  private static ChromeOptions getCapabilities() {
    return new ChromeOptions();
  }

  // Do not quit the driver if running on Sauce Labs
  public class LocalTestWatcher implements TestWatcher {
    @Override
    public void testSuccessful(ExtensionContext context) {
      System.out.println("Test Succeeded");
      if (SauceSession.isDisabled()) {
        driver.quit();
      }
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
      System.out.println("Test Failed: " + cause.getMessage());
      if (SauceSession.isDisabled()) {
        driver.quit();
      }
    }
  }

  @AfterAll
  public static void resetSauce() {
    System.clearProperty("sauce.disabled");
  }
}
