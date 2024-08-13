package com.saucelabs.saucebindings.junit4;

import com.saucelabs.saucebindings.JobVisibility;
import com.saucelabs.saucebindings.options.SauceOptions;
import java.time.Duration;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Capabilities;

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
    Assert.assertEquals("firefox", caps.getBrowserName());
    Assert.assertEquals("87.0", caps.getCapability("browserVersion"));
  }
}
