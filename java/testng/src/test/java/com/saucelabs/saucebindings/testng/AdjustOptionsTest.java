package com.saucelabs.saucebindings.testng;

import com.saucelabs.saucebindings.JobVisibility;
import com.saucelabs.saucebindings.options.SauceOptions;
import org.openqa.selenium.Capabilities;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class AdjustOptionsTest extends SauceBaseTest {

    @Override
    protected SauceOptions createSauceOptions() {
        return SauceOptions.firefox()
                .setMaxDuration(Duration.ofMinutes(30))
                .setJobVisibility(JobVisibility.TEAM)
                .setBrowserVersion("87.0")
                .build();
    }

    @Test
    public void useCustomOptions() {
        Capabilities caps = getDriver().getCapabilities();
        assertEquals("firefox", caps.getBrowserName());
        assertEquals("87.0", caps.getCapability("browserVersion"));
    }
}
