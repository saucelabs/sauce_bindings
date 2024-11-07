package com.saucelabs.saucebindings.junit5.examples;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.junit5.SauceBindingsExtension;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariOptions;

public class CapabilitiesExample {
  WebDriver driver;
  SauceSession session;

  // 1. Create static method with Selenium Capabilities
  private static Capabilities getCapabilities() {
    SafariOptions browserOptions = new SafariOptions();
    browserOptions.setPlatformName("macOS 12");
    browserOptions.setBrowserVersion("latest");
    Map<String, Object> sauceOptions = new HashMap<>();
    sauceOptions.put("idleTimeout", 30);
    browserOptions.setCapability("sauce:options", sauceOptions);
    return browserOptions;
  }

  // 2. Register Sauce Bindings extension with these capabilities
  @RegisterExtension
  static SauceBindingsExtension sauceExtension =
      SauceBindingsExtension.builder().withCapabilities(getCapabilities()).build();

  // 3. Enable extension (this also can be done by running with -Dsauce.enabled=true)
  static {
    sauceExtension.enable();
  }

  // 4. Get variables created by the Sauce Bindings extension
  @BeforeEach
  public void setUp(SauceSession session, WebDriver driver) {
    this.session = session;
    this.driver = driver;
  }

  @Test
  public void capabilitiesExample() {
    // 5. Use the session instance to do Sauce Labs things
    session.annotate("Navigating to Swag Labs");

    // 6. Use the driver instance to do Selenium things
    driver.get("https://www.saucedemo.com/");
  }
}
