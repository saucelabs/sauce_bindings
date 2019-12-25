package com.saucelabs.simplesauce;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.openqa.selenium.MutableCapabilities;

import static org.junit.Assert.assertEquals;

public class TimeoutTest extends BaseConfigurationTest {
    @Before
    public void mockSession() {
        sauce = Mockito.spy(new SauceSession(sauceOptions));
        Mockito.doReturn(dummyRemoteDriver).when(sauce).createRemoteWebDriver();
    }

    @Test
    public void commandTimeout_canBeSet() {
        sauce.getTimeouts().setCommandTimeout(100);
        sauce.start();
        assertCorrectCommandSetOnRemoteSession("commandTimeout", 100);
    }

    private void assertCorrectCommandSetOnRemoteSession(String commandTimeout, int expectedTimeout) {
        Object sauceOptions = sauce.getCurrentSessionCapabilities().asMap().get("sauce:options");
        Object commandTimeoutSetInCaps = ((MutableCapabilities) sauceOptions).getCapability(commandTimeout);
        assertEquals(expectedTimeout, commandTimeoutSetInCaps);
    }

    @Test
    public void idleTimeout_canBeSet() {
        sauce.getTimeouts().setIdleTimeout(100);
        sauce.start();
        assertCorrectCommandSetOnRemoteSession("idleTimeout", 100);
    }
    @Test
    public void maxDuration_canBeSet() throws MaxTestDurationTimeoutExceededException {
        int maxTestDurationInSec = 1800;
        sauce.getTimeouts().setMaxTestDurationTimeout(maxTestDurationInSec);
        sauce.start();
        assertCorrectCommandSetOnRemoteSession("maxDuration", maxTestDurationInSec);
    }
    @Test(expected = MaxTestDurationTimeoutExceededException.class)
    public void maxDuration_setTo1HigherThanMax_throwsException() throws MaxTestDurationTimeoutExceededException {
        int maxTestDurationInSec = 1801;
        sauce.getTimeouts().setMaxTestDurationTimeout(maxTestDurationInSec);
    }
    @Test()
    public void maxDuration_setTo1LowerThanMax_noException() throws MaxTestDurationTimeoutExceededException {
        int maxTestDurationInSec = 1799;
        sauce.getTimeouts().setMaxTestDurationTimeout(maxTestDurationInSec);
        sauce.start();
        assertCorrectCommandSetOnRemoteSession("maxDuration", maxTestDurationInSec);
    }
}
