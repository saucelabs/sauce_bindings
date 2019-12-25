package com.saucelabs.simplesauce;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MacOsTest extends BaseTestConfiguration {
    @Test
    public void withMojave_returnsValidOsConfiguration() {
        sauceOptions.withMac(MacVersion.Mojave);

        String actualPlatformSetInConfig = sauceOptions.getPlatformName();
        assertEquals("macOS 10.14", actualPlatformSetInConfig);
    }

    @Test
    public void withHighSierra_returnsValidOsConfiguration() {
        sauceOptions.withMac(MacVersion.HighSierra);

        String actualPlatformSetInConfig = sauceOptions.getPlatformName();
        assertEquals("macOS 10.13", actualPlatformSetInConfig);
    }

    @Test
    public void withSierra_returnsValidOsConfiguration() {
        sauceOptions.withMac(MacVersion.Sierra);

        String actualPlatformSetInConfig = sauceOptions.getPlatformName();
        assertEquals("macOS 10.12", actualPlatformSetInConfig);
    }

    @Test
    public void withElCapitan_returnsValidOsConfiguration() {
        sauceOptions.withMac(MacVersion.ElCapitan);

        String actualPlatformSetInConfig = sauceOptions.getPlatformName();
        assertEquals("OS X 10.11", actualPlatformSetInConfig);
    }

    @Test
    public void withYosemite_returnsValidOsConfiguration() {
        sauceOptions.withMac(MacVersion.Yosemite);

        String actualPlatformSetInConfig = sauceOptions.getPlatformName();
        assertEquals("OS X 10.10", actualPlatformSetInConfig);
    }

    @Test
    public void defaultSafari_browserVersionIs12_0() {
        sauceOptions.withSafari();

        String actualBrowserVersionSetInConfig = sauceOptions.getBrowserVersion();
        assertEquals("latest", actualBrowserVersionSetInConfig);
    }

    @Test
    public void defaultSafari_macOsVersionIsMojave() {
        sauceOptions.withSafari();

        String actualPlatformSetInConfig = sauceOptions.getPlatformName();
        assertEquals(Platforms.MAC_OS.MOJAVE.getPlatform().getOsVersion(), actualPlatformSetInConfig);
    }

    @Test
    public void withSafari_browserName_setToSafariEnum() {
        sauceOptions.withSafari(SafariVersion._8);

        String actualBrowserNameSetThroughSauceSession = sauceOptions.getBrowserName();
        assertEquals("safari", actualBrowserNameSetThroughSauceSession);
    }

    @Test
    public void withSafari_browserName_setToSafariString() {
        sauceOptions.withSafari(SafariVersion._8.getVersion());

        String actualBrowserNameSetThroughSauceSession = sauceOptions.getBrowserName();
        assertEquals("safari", actualBrowserNameSetThroughSauceSession);
    }

    @Test
    public void withSafari_versionChangedFromDefault_returnsCorrectVersion() {
        sauceOptions.withSafari(SafariVersion._8);

        String actualBrowserVersionSetThroughSauceSession = sauceOptions.getBrowserVersion();
        assertEquals("8.0", actualBrowserVersionSetThroughSauceSession);
    }
    @Test
    public void withSafari_versionNotSet_returnsLatest() {
        sauceOptions.withSafari("");

        String actualBrowserVersionSetThroughSauceSession = sauceOptions.getBrowserVersion();
        assertEquals("latest", actualBrowserVersionSetThroughSauceSession);
    }
}
