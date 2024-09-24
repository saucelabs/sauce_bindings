package com.saucelabs.saucebindings.junit4.examples;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.junit4.SauceBindingsWatcher;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Assert;
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

  // To run test on Sauce Labs, change this to "false"
  @BeforeClass
  public static void disableSauce() {
    System.setProperty("sauce.disabled", "true");
  }

  // 1. Use the SauceBindingsWatcher rule
  @Rule public SauceBindingsWatcher sauceWatcher = new SauceBindingsWatcher();

  // 2. Get variables created by Watcher
  @Before
  public void storeVariables() {
    if (SauceSession.isDisabled()) {
      this.session = sauceWatcher.getSession();
      this.driver = sauceWatcher.getDriver();
    } else {
      driver = new ChromeDriver();
    }
  }

  @Test
  public void localSession() {
    // This code executes whether running locally or on Sauce
    driver.get("https://www.saucedemo.com/");

    // This code executes if Sauce enabled, and is ignored when disabled
    try {
      session.annotate("This gets ignored");
      session.addTags(List.of("ignored"));
      session.stopNetwork();
      session.enableLogging();
      session.getAccessibilityResults();
    } catch (Exception e) {
      Assert.fail("There should not be an exception here");
    }
  }

  @AfterClass
  public static void resetSauce() {
    System.clearProperty("sauce.disabled");
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
