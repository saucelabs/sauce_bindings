package com.saucelabs.saucebindings.junit4;

import com.saucelabs.saucebindings.JobVisibility;
import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.options.SauceOptions;
import org.junit.Test;
import org.openqa.selenium.Capabilities;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class AdjustOptionsTest extends SauceBaseTest {

    public SauceOptions createSauceOptions() {
        return SauceOptions.firefox()
                .setMaxDuration(Duration.ofMinutes(30))
                .setJobVisibility(JobVisibility.TEAM)
                .setBrowserVersion("87.0")
                .build();
    }

    @Test
    public void useCustomOptions() throws AssertionError {
        Capabilities caps = driver.getCapabilities();
        assertEquals("firefox", caps.getBrowserName());
        assertEquals("87.0", caps.getCapability("browserVersion"));
    }
}
