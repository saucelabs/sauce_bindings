package com.saucelabs.saucebindings.junit4;

import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.options.SauceOptions;
import java.time.Duration;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

public class OptionsTest {

  public static SauceOptions setSauceOptions() {
    return SauceOptions.safari()
        .setPlatformName(SaucePlatform.MAC_MONTEREY)
        .setIdleTimeout(Duration.ofSeconds(30))
        .build();
  }

  @Rule
  public SauceBindingsExtension sauceExtension =
      SauceBindingsExtension.builder().capabilities(setSauceOptions()).build();

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
