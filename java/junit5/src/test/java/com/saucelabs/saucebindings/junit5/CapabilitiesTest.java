package com.saucelabs.saucebindings.junit5;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

public class CapabilitiesTest {
  public static Capabilities getCapabilities() {
    SafariOptions browserOptions = new SafariOptions();
    browserOptions.setPlatformName("macOS 12");
    browserOptions.setBrowserVersion("latest");
    Map<String, Object> sauceOptions = new HashMap<>();
    sauceOptions.put("idleTimeout", 30);
    browserOptions.setCapability("sauce:options", sauceOptions);
    return browserOptions;
  }

  @RegisterExtension
  static SauceBindingsExtension sauceExtension = new SauceBindingsExtension(getCapabilities());

  @Test
  public void useCustomCapabilities() {
    RemoteWebDriver driver = (RemoteWebDriver) sauceExtension.getDriver();
    Assertions.assertEquals("Safari", driver.getCapabilities().getBrowserName());

    Assertions.assertDoesNotThrow(
        () -> {
          sauceExtension.getSession().annotate("Annotating test");
          driver.get("https://www.saucedemo.com/");
        },
        "Driver and Session should be available");
  }
}
