package com.saucelabs.saucebindings.junit4.examples;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.junit4.SauceBindingsWatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RunLocalExample {
  private SauceSession session;
  private WebDriver driver;

  // 1. Set multiple rules for when Sauce is and isn't enabled
  @Rule public SauceBindingsWatcher sauceWatcher = new SauceBindingsWatcher();
  @Rule public TestWatcher localWatcher = new LocalTestWatcher();

  // Sauce Labs execution is disabled by default,
  // To run tests without Sauce, do not enable the watcher (`SauceBindingsWatcher.enable()`)
  // and do not execute with `-Dsauce.enabled=true`
  static {
    System.out.println("Sauce Bindings Extension not Enabled");
    // SauceBindingsWatcher.enable();
  }

  // 2. Start driver if running locally
  @Before
  public void storeVariables() {
    this.session = sauceWatcher.getSession();
    this.driver = SauceSession.isEnabled() ? sauceWatcher.getDriver(): new ChromeDriver();
  }

  @Test
  public void localSession() {
    // This code executes whether running locally or on Sauce
    driver.get("https://www.saucedemo.com/");
    session.annotate("This gets ignored when sauce is disabled");
  }

  // Do not quit the driver if running on Sauce Labs
  public class LocalTestWatcher extends TestWatcher {
    @Override
    public void succeeded(Description description) {
      System.out.println("Test Succeeded");
      if (!SauceSession.isEnabled()) {
        driver.quit();
      }
    }

    @Override
    public void failed(Throwable e, Description description) {
      System.out.println("Test Failed: " + e.getMessage());
      if (!SauceSession.isEnabled()) {
        driver.quit();
      }
    }
  }
}
