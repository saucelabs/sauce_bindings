package com.saucelabs.saucebindings.junit5.examples;

import com.deque.html.axecore.results.Results;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.junit5.SauceBindingsExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;

public class AccessibilityExample {
  WebDriver driver;
  SauceSession session;

  // 1. Register Sauce Bindings extension with defaults
  @RegisterExtension static SauceBindingsExtension sauceExtension = new SauceBindingsExtension();

  // 2. Enable extension (this also can be done by running with -Dsauce.enabled=true)
  static {
    sauceExtension.enable();
  }

  // 3. Get variables created by the Sauce Bindings extension
  @BeforeEach
  public void setUp(SauceSession session) {
    this.session = session;
    this.driver = session.getDriver();
  }

  @Test
  public void accessibilityExample() {
    // 4. Use the driver instance to do Selenium things
    driver.get("https://www.saucedemo.com/");

    // 5. store the accessibility results into an object
    Results accessibilityResults = session.getAccessibilityResults();

    // 6. you can assert on the accessibilityResults as desired
  }
}
