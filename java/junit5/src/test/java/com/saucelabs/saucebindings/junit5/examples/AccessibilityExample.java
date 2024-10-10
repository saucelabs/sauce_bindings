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

  @RegisterExtension static SauceBindingsExtension sauceExtension = new SauceBindingsExtension();

  @BeforeEach
  public void setUp(SauceSession session) {
    this.session = session;
    this.driver = session.getDriver();
  }

  @Test
  public void accessibilityExample() {
    driver.get("https://www.saucedemo.com/");
    // store the accessibility results into an object
    Results accessibilityResults = session.getAccessibilityResults();
    // if you want, you can assert on the accessibilityResults
  }
}
