package com.saucelabs.saucebindings.junit4.examples;

import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.junit4.SauceBindingsExtension;
import com.saucelabs.saucebindings.options.SauceOptions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

// 1. Extend the provided base test class
public class ExtensionTest {
  WebDriver driver;
  SauceSession session;

  public static SauceOptions createSauceOptions() {
    return SauceOptions.safari()
        .setPlatformName(SaucePlatform.MAC_CATALINA)
        .setBrowserVersion("15")
        .build();
  }

  @Rule
  public SauceBindingsExtension sauceExtension =
      SauceBindingsExtension.builder()
          .capabilities(createSauceOptions())
          .dataCenter(DataCenter.US_WEST)
          .build();

  @Before
  public void setup() {
    driver = sauceExtension.getDriver();
    session = sauceExtension.getSession();
  }

  @Test
  public void startSession() {
    // 2. Session and Driver are created automatically by superclass

    // 3. Use the driver in your tests just like normal
    driver.get("https://www.saucedemo.com/");

    // 4. Session is stopped and results are sent to Sauce Labs automatically by the superclass
  }
}
