package com.saucelabs.simplesauce;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimeoutTest extends BaseConfigurationTest {
    private SauceTimeout sauceTimeout = sauceOptions.getSauceTimeout();

    @Test
    public void commandTimeout_canBeSet() {
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
