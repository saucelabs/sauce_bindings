package com.saucelabs.saucebindings.junit4.examples;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.junit4.SauceBindingsWatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class QuickStartExample {
  private SauceSession session;
  private WebDriver driver;

  // 1. Use the SauceBindingsWatcher rule
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
    // 4. Use the session instance to do Sauce Labs things
    session.annotate("Navigating to Swag Labs");

    // 5. Use the driver instance to do Selenium things
    driver.get("https://www.saucedemo.com/");
  }
}
