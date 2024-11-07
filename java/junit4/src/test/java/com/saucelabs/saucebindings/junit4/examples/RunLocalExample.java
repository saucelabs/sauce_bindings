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
  private WebDriver driver;
  private SauceSession session;

  // 1. Register Watchers
  // Register Sauce Bindings watcher with defaults
  @Rule public SauceBindingsWatcher sauceWatcher = new SauceBindingsWatcher();
  // Register additional test watcher(s) for local execution
  @Rule public TestWatcher localWatcher = new LocalTestWatcher();

  // Sauce Labs execution is disabled by default,
  // To run tests without Sauce, do not enable the watcher (`SauceBindingsWatcher.enable()`)
  // and do not execute with `-Dsauce.enabled=true`
  static {
    System.out.println("Sauce Bindings Extension not Enabled");
    // SauceBindingsWatcher.enable();
  }

  // Only start driver if Sauce Watcher is not enabled
  @Before
  public void storeVariables() {
    this.session = sauceWatcher.getSession();
    this.driver = SauceSession.isEnabled() ? sauceWatcher.getDriver() : new ChromeDriver();
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
