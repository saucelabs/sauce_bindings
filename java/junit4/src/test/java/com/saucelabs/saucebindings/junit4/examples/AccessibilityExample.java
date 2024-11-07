package com.saucelabs.saucebindings.junit4.examples;

import com.deque.html.axecore.results.Results;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.junit4.SauceBindingsWatcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class AccessibilityExample {
  private SauceSession session;
  private WebDriver driver;

  // 1. Use the SauceBindingsWatcher rule
  @Rule public SauceBindingsWatcher sauceWatcher = new SauceBindingsWatcher();

  // 2. Enable SauceBindingsWatcher rule
  static {
    SauceBindingsWatcher.enable();
  }

  // 3. Get variables created by Watcher
  @Before
  public void storeVariables() {
    this.session = sauceWatcher.getSession();
    this.driver = sauceWatcher.getDriver();
  }

  @Test
  public void startSession() {
    // 4. Use the driver in your tests just like normal
    driver.get("https://www.saucedemo.com/");

    // 5. Get and assert on accessibility results
    Results results = session.getAccessibilityResults();
    Assert.assertEquals(3, results.getViolations().size());

    // 6. Session is stopped and results are sent to Sauce Labs automatically by the superclass
  }
}
