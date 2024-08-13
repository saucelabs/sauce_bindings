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

  @RegisterExtension
  static SauceBindingsExtension sauceExtension = new SauceBindingsExtension(DataCenter.EU_CENTRAL);

  @BeforeEach
  public void storeVariables() {
    session = sauceExtension.getSession();
    driver = sauceExtension.getDriver();
  }

  @Test
  public void dataCenterExample() {
    session.annotate("Navigating to Swag Labs");
    driver.get("https://www.saucedemo.com/");
  }
}
