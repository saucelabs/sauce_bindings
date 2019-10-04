package com.saucelabs.simplesauce.unit;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LinuxTests extends BaseConfigurationTest {
    @Test
    public void withLinux_setsPlatformToLinux() {
        sauceOptions.withLinux();
        mockSauceSession = instantiateSauceSession();

        mockSauceSession.start();
        String actualOsSetInConfig = mockSauceSession.sauceSessionCapabilities.getPlatform().toString();
        assertEquals("linux", actualOsSetInConfig.toLowerCase());
    }
}
