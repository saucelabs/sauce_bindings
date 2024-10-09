package com.saucelabs.saucebindings.junit5.examples;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.junit5.SauceBindingsExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ToggleLocalExample {
  WebDriver driver;
  SauceSession session;

  // Register multiple test watchers for local and sauce execution
  @RegisterExtension static SauceBindingsExtension sauceExtension = new SauceBindingsExtension();
  @RegisterExtension TestWatcher testWatcher = new LocalTestWatcher();

  // Change this property to "true" to run locally
  @BeforeAll
  public static void toggleSauce() {
    System.setProperty("sauce.disabled", "false");
  }

  @BeforeEach
  public void setUp(SauceSession session, WebDriver driver) {
    this.session = session;
    this.driver = SauceSession.isDisabled() ? new ChromeDriver() : driver;
  }

  @Test
  public void toggleExample() {
    driver.get("https://www.saucedemo.com/");
    session.annotate("This gets ignored when sauce is disabled");
  }

  public class LocalTestWatcher implements TestWatcher {
    @Override
    public void testSuccessful(ExtensionContext context) {
      System.out.println("Test Succeeded");
      // Do not quit the driver if sauce is not disabled
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
}
