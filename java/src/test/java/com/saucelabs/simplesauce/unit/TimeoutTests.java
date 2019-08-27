package com.saucelabs.simplesauce.unit;

import org.junit.Test;
import org.openqa.selenium.MutableCapabilities;

import static org.junit.Assert.assertEquals;

public class TimeoutTests extends BaseConfigurationTest {
    //maxDuration timeout should not even be settable. Default is 30 min and we will not
    //allow our users to set a higher time than that
    @Test
    public void commandTimeout_canBeSet() {
        fakeSauceSession.timeouts.setCommandTimeout(100);
        fakeSauceSession.start();
        Object sauceOptions = fakeSauceSession.sauceSessionCapabilities.asMap().get("sauce:options");
        Object commandTimeoutSetInCaps = ((MutableCapabilities) sauceOptions).getCapability("commandTimeout");
        assertEquals(100, commandTimeoutSetInCaps);
    }
    @Test
    public void idleTimeout_canBeSet() {
        fakeSauceSession.timeouts.setIdleTimeout(100);
        fakeSauceSession.start();
        Object sauceOptions = fakeSauceSession.sauceSessionCapabilities.asMap().get("sauce:options");
        Object commandTimeoutSetInCaps = ((MutableCapabilities) sauceOptions).getCapability("idleTimeout");
        assertEquals(100, commandTimeoutSetInCaps);
    }
}
