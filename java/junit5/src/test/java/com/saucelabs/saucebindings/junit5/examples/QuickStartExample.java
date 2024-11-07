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

  // Register extension with defaults
  @RegisterExtension static SauceBindingsExtension sauceExtension = new SauceBindingsExtension();

  // Enable extension (this also can be done by running with -Dsauce.enabled=true)
  static {
    sauceExtension.enable();
  }

  @BeforeEach
  public void setUp(SauceSession session, WebDriver driver) {
    this.session = session;
    this.driver = driver;
  }

  @Test
  public void quickStartExample() {
    session.annotate("Navigating to Swag Labs");
    driver.get("https://www.saucedemo.com/");
  }
}
