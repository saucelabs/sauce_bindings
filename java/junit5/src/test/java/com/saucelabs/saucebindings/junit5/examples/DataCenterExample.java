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

  // 1. Register Sauce Bindings extension with custom data center
  @RegisterExtension
  static SauceBindingsExtension sauceExtension =
      SauceBindingsExtension.builder().withDataCenter(DataCenter.EU_CENTRAL).build();

  // 2. Enable extension (this also can be done by running with -Dsauce.enabled=true)
  static {
    sauceExtension.enable();
  }

  // 3. Get variables created by the Sauce Bindings extension
  @BeforeEach
  public void setUp(SauceSession session, WebDriver driver) {
    this.session = session;
    this.driver = driver;
  }

  @Test
  public void dataCenterExample() {
    // 4. Use the session instance to do Sauce Labs things
    session.annotate("Navigating to Swag Labs");

    // 5. Use the driver instance to do Selenium things
    driver.get("https://www.saucedemo.com/");
  }
}
