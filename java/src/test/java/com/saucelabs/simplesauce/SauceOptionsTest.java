package com.saucelabs.simplesauce;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.BrowserType;

import static org.junit.Assert.assertEquals;

public class SauceOptionsTest {
    private SauceOptions options = new SauceOptions();

    @Test
    public void usesLatestChromeWindowsVersions() {
        assertEquals(BrowserType.CHROME, options.getBrowserName());
        assertEquals("latest", options.getBrowserVersion());
        assertEquals("Windows 10", options.getPlatformName());
    }

    @Test
    public void updatesW3CValues() {
        options.setBrowserName(BrowserType.FIREFOX);
        options.setPlatformName(Platforms.MAC_OS_HIGH_SIERRA.getOsVersion());
        options.setBrowserVersion("68");

        assertEquals(BrowserType.FIREFOX, options.getBrowserName());
        assertEquals("68", options.getBrowserVersion());
        assertEquals("macOS 10.13", options.getPlatformName());
    }
}
