package com.saucelabs.saucebindings.junit5.examples;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.junit5.SauceBindingsExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RunLocalExample {
  WebDriver driver;
  SauceSession session;

  // Register Sauce Bindings extension with defaults
  @RegisterExtension static SauceBindingsExtension sauceExtension = new SauceBindingsExtension();
  // Register additional test watcher(s) for local execution
  @RegisterExtension TestWatcher testWatcher = new LocalTestWatcher();

  @BeforeEach
  public void setUp(SauceSession session, WebDriver driver) {
    this.session = session;
    this.driver = SauceSession.isEnabled() ? driver : new ChromeDriver();
  }

  // Sauce Labs execution is disabled by default,
  // To run tests without Sauce, do not enable the extension (`sauceExtension.enable()`)
  // and do not execute with `-Dsaucelabs.enabled=true`
  static {
    System.out.println("Sauce Bindings Extension not Enabled");
    // sauceExtension.enable();
  }

  @Test
  public void toggleExample() {
    // This code executes whether running locally or on Sauce
    driver.get("https://www.saucedemo.com/");
    session.annotate("This gets ignored when sauce is disabled");
  }

  // Make sure only local drivers are quit in the local test watcher
  public class LocalTestWatcher implements TestWatcher {
    @Override
    public void testSuccessful(ExtensionContext context) {
      System.out.println("Test Succeeded");
      if (SauceSession.isEnabled()) {
        throw new RuntimeException("Test should not run when Extension is enabled");
      } else {
        driver.quit();
      }
    }
  }
}
