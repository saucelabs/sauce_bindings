package com.saucelabs.saucebindings.junit4.examples;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.junit4.SauceBindingsWatcher;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ToggleLocalExample {
  private SauceSession session;
  private WebDriver driver;

  // Change this property to "true" to run locally
  @BeforeClass
  public static void disableSauce() {
    System.setProperty("sauce.disabled", "false");
  }

  // 1. Set multiple rules for when Sauce is and isn't enabled
  @Rule public SauceBindingsWatcher sauceWatcher = new SauceBindingsWatcher();
  @Rule public TestWatcher localWatcher = new LocalTestWatcher();

  // 2. Start driver if running locally
  @Before
  public void storeVariables() {
    this.session = sauceWatcher.getSession();
    this.driver = SauceSession.isDisabled() ? new ChromeDriver() : sauceWatcher.getDriver();
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
      if (SauceSession.isDisabled()) {
        driver.quit();
      }
    }

    @Override
    public void failed(Throwable e, Description description) {
      System.out.println("Test Failed: " + e.getMessage());
      if (SauceSession.isDisabled()) {
        driver.quit();
      }
    }
  }
}
