package com.saucelabs.saucebindings.options;

import com.google.common.collect.ImmutableMap;
import org.junit.Assert;
import org.junit.Test;

/**
 * This Test uses the default SauceOptions constructor then adds configs via Map using mergeCapabilities
 *
 * This functionality has been replaced by supporting config files themselves in the constructor
 */
public class SauceOptionsDeprecatedMapTest {
    private SauceOptions sauceOptions = new SauceOptions();

    @Test
    public void setsSauceOptionsCapabilityFromMap() {
        sauceOptions.mergeCapabilities(ImmutableMap.of("sauce:options", ImmutableMap.of("name", "Valid")));
        Assert.assertEquals("Valid", sauceOptions.sauce().getName());
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void setsBadOptionFromMap() {
        sauceOptions.mergeCapabilities(ImmutableMap.of("invalid", "capability"));
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void setsBadSauceCapabilityFromMap() {
        sauceOptions.mergeCapabilities(ImmutableMap.of("sauce", ImmutableMap.of("invalid", "capability")));
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void setsBadBrowserFromMap() {
        sauceOptions.mergeCapabilities(ImmutableMap.of("browserName", "netscape"));
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void setsBadJobVisibilityFromMap() {
        sauceOptions.mergeCapabilities(ImmutableMap.of("sauce", ImmutableMap.of("jobVisibility", "invalid")));
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void setsBadPromptBehaviorFromMap() {
        sauceOptions.mergeCapabilities(ImmutableMap.of("unhandledPromptBehavior", "invalid"));
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void setsBadTimeoutFromMap() {
        sauceOptions.mergeCapabilities(ImmutableMap.of("timeouts", ImmutableMap.of("invalid", 1)));
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void setsBadPrerunFromMap() {
        sauceOptions.mergeCapabilities(ImmutableMap.of("sauce", ImmutableMap.of("prerun", ImmutableMap.of("invalid", ""))));
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void setsBadPageLoadFromMap() {
        sauceOptions.mergeCapabilities(ImmutableMap.of("pageLoadStrategy", "fast"));
    }

    @Test(expected = InvalidSauceOptionsArgumentException.class)
    public void setsBadPlatformFromMap() {
        sauceOptions.mergeCapabilities(ImmutableMap.of("platformName", "MAC"));
    }


}
