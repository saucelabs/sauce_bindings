package com.saucelabs.simplesauce;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LinuxTest extends BaseConfigurationTest {
    @Test
    public void withLinux_setsPlatformToLinux() {
        sauceOptions.withLinux();
        sauce = instantiateSauceSession();

        sauce.start();
        String actualOsSetInConfig = sauce.getCurrentSessionCapabilities().getPlatform().toString();
        assertEquals("linux", actualOsSetInConfig.toLowerCase());
    }
}
