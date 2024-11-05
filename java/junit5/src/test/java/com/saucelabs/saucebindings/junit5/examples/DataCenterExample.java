package com.saucelabs.saucebindings.junit5.examples;

import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.junit5.SauceBindingsExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;

public class DataCenterExample {
  WebDriver driver;
  SauceSession session;

  // Register extension with custom data center
  @RegisterExtension
  static SauceBindingsExtension sauceExtension =
      SauceBindingsExtension.builder().withDataCenter(DataCenter.EU_CENTRAL).build();

  // Enable extension (this also can be done by running with -Dsaucelabs.enable=true)
  static {
    sauceExtension.enable();
  }

  @BeforeEach
  public void setUp(SauceSession session, WebDriver driver) {
    this.session = session;
    this.driver = driver;
  }

  @Test
  public void dataCenterExample() {
    session.annotate("Navigating to Swag Labs");
    driver.get("https://www.saucedemo.com/");
  }
}
