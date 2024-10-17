package com.saucelabs.saucebindings.junit4.examples;

import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.junit4.SauceBindingsWatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class DataCenterExample {
  private SauceSession session;
  private WebDriver driver;

  // 1. Pass in desired Datacenter to the SauceBindingsWatcher rule
  @Rule
  public SauceBindingsWatcher sauceWatcher =
      SauceBindingsWatcher.builder().withDataCenter(DataCenter.EU_CENTRAL).build();

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
