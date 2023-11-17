package com.saucelabs.saucebindings.junit4;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

public class CapabilitiesTest {

  public static Capabilities setCapabilities() {
    SafariOptions browserOptions = new SafariOptions();
    browserOptions.setPlatformName("macOS 12");
    browserOptions.setBrowserVersion("latest");
    Map<String, Object> sauceOptions = new HashMap<>();
    sauceOptions.put("idleTimeout", 30);
    browserOptions.setCapability("sauce:options", sauceOptions);
    return browserOptions;
  }

  static {
    System.setProperty("sauce.enabled", "true");
  }

  @Rule
  public SauceBindingsExtension sauceExtension =
      SauceBindingsExtension.builder().capabilities(setCapabilities()).build();

  @Test
  public void useCustomOptions() {
    RemoteWebDriver driver = (RemoteWebDriver) sauceExtension.getDriver();
    Assert.assertEquals("Safari", driver.getCapabilities().getBrowserName());

    try {
      sauceExtension.getSession().annotate("Annotating test");
      driver.get("https://www.saucedemo.com/");
    } catch (Exception e) {
      Assert.fail("Driver and Session should be available");
    }
  }
}
