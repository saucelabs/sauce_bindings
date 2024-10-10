package com.saucelabs.saucebindings.junit5;

import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import java.time.Duration;
import java.util.List;

import com.saucelabs.saucerest.DataCenter;
import com.saucelabs.saucerest.SauceREST;
import com.saucelabs.saucerest.api.JobsEndpoint;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
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

  @SneakyThrows
  @Test
  @Tag("one")
  @Tag("two")
    public void setsTags(WebDriver driver) {
    SauceREST rest = new SauceREST(DataCenter.US_WEST);
    String id =  ((RemoteWebDriver) driver).getSessionId().toString();
    List<String> tags = rest.getJobsEndpoint().getJobDetails(id).tags;
    Assertions.assertTrue(tags.contains("one"));
    Assertions.assertTrue(tags.contains("one"));
  }
}
