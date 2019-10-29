package com.saucelabs.simplesauce;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LinuxTest extends BaseConfigurationTest {
    @Test
    public void withLinux_setsPlatformToLinux() {
        sauceOptions.withLinux();
        mockSauceSession = instantiateSauceSession();

        mockSauceSession.start();
        String actualOsSetInConfig = mockSauceSession.currentSessionCapabilities.getPlatform().toString();
        assertEquals("linux", actualOsSetInConfig.toLowerCase());
    }
}
