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

  // 2. Get variables created by Watcher
  @Before
  public void storeVariables() {
    this.session = sauceWatcher.getSession();
    this.driver = sauceWatcher.getDriver();
  }

  @Test
  public void startSession() {
    // 3. Use the session instance to do Sauce Labs things
    session.annotate("Navigating to Swag Labs");

    // 4. Use the driver instance to do Selenium things
    driver.get("https://www.saucedemo.com/");

    // 5. Watcher does all teardown activities
  }
}
