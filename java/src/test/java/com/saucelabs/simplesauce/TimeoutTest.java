package com.saucelabs.simplesauce;

import org.junit.Test;
import org.openqa.selenium.MutableCapabilities;

import static org.junit.Assert.assertEquals;

public class TimeoutTest extends BaseConfigurationTest {
    @Test
    public void commandTimeout_canBeSet() {
        mockSauceSession.timeouts.setCommandTimeout(100);
        mockSauceSession.start();
        assertCorrectCommandSetOnRemoteSession("commandTimeout", 100);
    }

    private void assertCorrectCommandSetOnRemoteSession(String commandTimeout, int expectedTimeout) {
        Object sauceOptions = mockSauceSession.currentSessionCapabilities.asMap().get("sauce:options");
        Object commandTimeoutSetInCaps = ((MutableCapabilities) sauceOptions).getCapability(commandTimeout);
        assertEquals(expectedTimeout, commandTimeoutSetInCaps);
    }

    @Test
    public void idleTimeout_canBeSet() {
        mockSauceSession.timeouts.setIdleTimeout(100);
        mockSauceSession.start();
        assertCorrectCommandSetOnRemoteSession("idleTimeout", 100);
    }
    @Test
    public void maxDuration_canBeSet() throws MaxTestDurationTimeoutExceeded {
        int maxTestDurationInSec = 1800;
        mockSauceSession.timeouts.setMaxTestDurationTimeout(maxTestDurationInSec);
        mockSauceSession.start();
        assertCorrectCommandSetOnRemoteSession("maxDuration", maxTestDurationInSec);
    }
    @Test(expected = MaxTestDurationTimeoutExceeded.class)
    public void maxDuration_setTo1HigherThanMax_throwsException() throws MaxTestDurationTimeoutExceeded {
        int maxTestDurationInSec = 1801;
        mockSauceSession.timeouts.setMaxTestDurationTimeout(maxTestDurationInSec);
    }
    @Test()
    public void maxDuration_setTo1LowerThanMax_noException() throws MaxTestDurationTimeoutExceeded {
        int maxTestDurationInSec = 1799;
        mockSauceSession.timeouts.setMaxTestDurationTimeout(maxTestDurationInSec);
        mockSauceSession.start();
        assertCorrectCommandSetOnRemoteSession("maxDuration", maxTestDurationInSec);
    }
}
