package com.saucelabs.simplesauce;

import org.junit.Test;
import org.openqa.selenium.MutableCapabilities;

import static org.junit.Assert.assertEquals;

public class TimeoutTest extends BaseConfigurationTest {
    @Test
    public void commandTimeout_canBeSet() {
        sauce.timeouts.setCommandTimeout(100);
        sauce.start();
        assertCorrectCommandSetOnRemoteSession("commandTimeout", 100);
    }

    private void assertCorrectCommandSetOnRemoteSession(String commandTimeout, int expectedTimeout) {
        Object sauceOptions = sauce.currentSessionCapabilities.asMap().get("sauce:options");
        Object commandTimeoutSetInCaps = ((MutableCapabilities) sauceOptions).getCapability(commandTimeout);
        assertEquals(expectedTimeout, commandTimeoutSetInCaps);
    }

    @Test
    public void idleTimeout_canBeSet() {
        sauce.timeouts.setIdleTimeout(100);
        sauce.start();
        assertCorrectCommandSetOnRemoteSession("idleTimeout", 100);
    }
    @Test
    public void maxDuration_canBeSet() throws MaxTestDurationTimeoutExceededException {
        int maxTestDurationInSec = 1800;
        sauce.timeouts.setMaxTestDurationTimeout(maxTestDurationInSec);
        sauce.start();
        assertCorrectCommandSetOnRemoteSession("maxDuration", maxTestDurationInSec);
    }
    @Test(expected = MaxTestDurationTimeoutExceededException.class)
    public void maxDuration_setTo1HigherThanMax_throwsException() throws MaxTestDurationTimeoutExceededException {
        int maxTestDurationInSec = 1801;
        sauce.timeouts.setMaxTestDurationTimeout(maxTestDurationInSec);
    }
    @Test()
    public void maxDuration_setTo1LowerThanMax_noException() throws MaxTestDurationTimeoutExceededException {
        int maxTestDurationInSec = 1799;
        sauce.timeouts.setMaxTestDurationTimeout(maxTestDurationInSec);
        sauce.start();
        assertCorrectCommandSetOnRemoteSession("maxDuration", maxTestDurationInSec);
    }
}
