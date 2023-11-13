package com.saucelabs.saucebindings.junit5;

import com.saucelabs.saucebindings.JobVisibility;
import com.saucelabs.saucebindings.options.SauceOptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Capabilities;

import java.time.Duration;

public class AdjustOptionsTest extends SauceBaseTest {

  @Override
  public SauceOptions createSauceOptions() {
    return SauceOptions.firefox()
        .setMaxDuration(Duration.ofMinutes(30))
        .setJobVisibility(JobVisibility.TEAM)
        .setBrowserVersion("87.0")
        .build();
  }

  @Test
  public void useCustomOptions() {
    Capabilities caps = driver.getCapabilities();
    Assertions.assertEquals("firefox", caps.getBrowserName());
    Assertions.assertEquals("87.0", caps.getCapability("browserVersion"));
  }
}
