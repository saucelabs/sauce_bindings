package com.saucelabs.simplesauce;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LinuxTest extends BaseTestConfiguration {
    @Test
    public void withLinux_setsPlatformToLinux() {
        sauceOptions.withLinux();
        startSauceSession();

        String actualOsSetInConfig = sauce.getCurrentSessionCapabilities().getPlatform().toString();
        assertEquals("linux", actualOsSetInConfig.toLowerCase());
    }
}
