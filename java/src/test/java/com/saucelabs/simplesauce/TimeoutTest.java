package com.saucelabs.simplesauce;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.MutableCapabilities;

import static org.junit.Assert.assertEquals;

public class TimeoutTest extends BaseTestConfiguration {
    private SauceOptions options = new SauceOptions();
    private SauceTimeout sauceTimeout;

    @Before
    public void setup() {
        sauceTimeout = options.getSauceTimeout();
    }
    
    @Test
    public void commandTimeout_canBeSet() {
        int maxTestDurationInSec = 1800;
        sauceTimeout.setCommandTimeout(100);

        assertEquals(sauceTimeout.getCommandTimeout(), 100);
    }

    @Test
    public void idleTimeout_canBeSet() {
        int idleTimeout = 100;

        sauceTimeout.setIdleTimeout(idleTimeout);
        assertEquals(idleTimeout, sauceTimeout.getIdleTimeout());
    }
    @Test
    public void maxDuration_canBeSet() throws MaxTestDurationTimeoutExceededException {
        int maxTestDurationInSec = 1800;
        sauceTimeout.setMaxTestDurationTimeout(maxTestDurationInSec);

        assertEquals(maxTestDurationInSec, sauceTimeout.getMaxTestDurationTimeout());
    }
    @Test(expected = MaxTestDurationTimeoutExceededException.class)
    public void maxDuration_setTo1HigherThanMax_throwsException() throws MaxTestDurationTimeoutExceededException {
        int maxTestDurationInSec = 1801;
        sauceTimeout.setMaxTestDurationTimeout(maxTestDurationInSec);
    }
}
