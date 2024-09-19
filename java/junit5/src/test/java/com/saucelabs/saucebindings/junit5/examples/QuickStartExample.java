package com.saucelabs.saucebindings.junit5.examples;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.junit5.SauceBindingsExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;

public class QuickStartExample {
  WebDriver driver;
  SauceSession session;

  @RegisterExtension static SauceBindingsExtension sauceExtension = new SauceBindingsExtension();

  @BeforeEach
  public void storeVariables() {
    // env variables are automatically read from system
    session = sauceExtension.getSession();
    driver = sauceExtension.getDriver();
  }

  @Test
  public void quickStartExample() {
    //method name and build name automatically captured
    session.annotate("Navigating to Swag Labs");
    driver.get("https://www.saucedemo.com/");
    //driver.quit() and test status automatically set with no extra code
  }
}
