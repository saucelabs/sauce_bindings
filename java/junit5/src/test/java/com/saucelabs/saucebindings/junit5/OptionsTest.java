package com.saucelabs.saucebindings.junit5;

import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import java.time.Duration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class OptionsTest {
  public static SauceOptions getSauceOptions() {
    return SauceOptions.safari()
        .setPlatformName(SaucePlatform.MAC_MONTEREY)
        .setIdleTimeout(Duration.ofSeconds(30))
        .build();
  }

  @RegisterExtension
  static SauceBindingsExtension sauceExtension =
      new SauceBindingsExtension.Builder().withSauceOptions(getSauceOptions()).build();

  @Test
  public void useCustomOptions(SauceSession session, WebDriver driver) {
    Assertions.assertEquals(
        "Safari", ((RemoteWebDriver) driver).getCapabilities().getBrowserName());

    Assertions.assertDoesNotThrow(
        () -> {
          session.annotate("Annotating test");
          driver.get("https://www.saucedemo.com/");
        },
        "Driver and Session should be available");
  }
}
