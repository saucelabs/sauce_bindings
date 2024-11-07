package com.saucelabs.saucebindings.junit4.examples;

import com.deque.html.axecore.results.Results;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.junit4.SauceBindingsWatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class AccessibilityExample {
  private SauceSession session;
  private WebDriver driver;

  // 1. Use the SauceBindingsWatcher rule with defaults
  @Rule public SauceBindingsWatcher sauceWatcher = new SauceBindingsWatcher();

  // 2. Enable this watcher in the static block
  static {
    SauceBindingsWatcher.enable();
  }

  // 3. Get variables created by the watcher
  @Before
  public void storeVariables() {
    this.session = sauceWatcher.getSession();
    this.driver = sauceWatcher.getDriver();
  }

  @Test
  public void startSession() {
    driver.get("https://www.saucedemo.com/");

    // 4. store the accessibility results into an object
    Results results = session.getAccessibilityResults();
    // if you want, you can assert on the accessibilityResults
  }
}
