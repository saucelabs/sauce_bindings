package com.saucelabs.saucebindings.testng;

import com.saucelabs.saucebindings.JobVisibility;
import com.saucelabs.saucebindings.options.SauceOptions;
import java.time.Duration;
import org.openqa.selenium.Capabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdjustOptionsTest extends SauceBaseTest {

  @Override
  protected SauceOptions createSauceOptions() {
    return SauceOptions.firefox()
        .setMaxDuration(Duration.ofMinutes(30))
        .setJobVisibility(JobVisibility.TEAM)
        .setBrowserVersion("127.0")
        .build();
  }

  @Test
  public void useCustomOptions() {
    Capabilities caps = getDriver().getCapabilities();
    Assert.assertEquals("firefox", caps.getBrowserName());
    Assert.assertEquals("127.0", caps.getCapability("browserVersion"));
  }
}
