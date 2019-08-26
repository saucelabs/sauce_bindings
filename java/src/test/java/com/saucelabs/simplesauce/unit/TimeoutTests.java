package com.saucelabs.simplesauce.unit;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimeoutTests extends BaseConfigurationTest {
    @Test
    public void maxTestDuration_defaultIs30Minutes() {
        int maxDurationTimeout = fakeSauceSession.timeouts.getMaxTestDuration();
        assertEquals(1800, maxDurationTimeout);
    }
}
