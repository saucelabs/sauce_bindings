package com.saucelabs.saucebindings.junit4.examples;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.junit4.SauceBindingsWatcher;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariOptions;

public class CapabilitiesExample {
  WebDriver driver;
  SauceSession session;

  // 1. Create Selenium Capabilities instance in a static method
  private static Capabilities createCapabilities() {
    SafariOptions browserOptions = new SafariOptions();
    browserOptions.setPlatformName("macOS 12");
    browserOptions.setBrowserVersion("latest");
    Map<String, Object> sauceOptions = new HashMap<>();
    sauceOptions.put("idleTimeout", 30);
    browserOptions.setCapability("sauce:options", sauceOptions);
    return browserOptions;
  }

  // 2. Use the SauceBindingsWatcher rule with these capabilities
  @Rule
  public SauceBindingsWatcher sauceWatcher =
      SauceBindingsWatcher.builder().withCapabilities(createCapabilities()).build();

  // 3. Enable this watcher in the static block
  static {
    SauceBindingsWatcher.enable();
  }

  // 4. Get variables created by the watcher
  @Before
  public void storeVariables() {
    this.session = sauceWatcher.getSession();
    this.driver = sauceWatcher.getDriver();
  }

  @Test
  public void basicOptions() {
    // 5. Use the session instance to do Sauce Labs things
    session.annotate("Navigating to Swag Labs");

    // 6. Use the driver instance to do Selenium things
    driver.get("https://www.saucedemo.com/");
  }
}
