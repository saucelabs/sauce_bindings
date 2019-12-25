package com.saucelabs.simplesauce;

import com.saucelabs.simplesauce.enums.MacVersion;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MacOsTest extends BaseTestConfiguration {
    @Test
    public void withMojave_returnsValidOsConfiguration() {
        sauceOptions.withMac(MacVersion.Mojave);
        startSauceSession();

        assertEquals("macOS 10.14", sauce.getCurrentSessionCapabilities().getPlatform().toString());
    }

    @Test
    public void withHighSierra_returnsValidOsConfiguration() {
        sauceOptions.withMac(MacVersion.HighSierra);
        startSauceSession();

        assertEquals("macOS 10.13", sauce.getCurrentSessionCapabilities().getPlatform().toString());
    }

    @Test
    public void withSierra_returnsValidOsConfiguration() {
        sauceOptions.withMac(MacVersion.Sierra);
        startSauceSession();

        assertEquals("macOS 10.12", sauce.getCurrentSessionCapabilities().getPlatform().toString());
    }

    @Test
    public void withElCapitan_returnsValidOsConfiguration() {
        sauceOptions.withMac(MacVersion.ElCapitan);
        startSauceSession();

        assertEquals("OS X 10.11", sauce.getCurrentSessionCapabilities().getPlatform().toString());
    }

    @Test
    public void withYosemite_returnsValidOsConfiguration() {
        sauceOptions.withMac(MacVersion.Yosemite);
        startSauceSession();

        assertEquals("OS X 10.10", sauce.getCurrentSessionCapabilities().getPlatform().toString());
    }

    @Test
    public void defaultSafari_browserVersionIs12_0() {
        sauceOptions.withSafari();
        startSauceSession();

        String safariVersionSetThroughSauceSession = sauce.getCurrentSessionCapabilities().getVersion();
        assertEquals("latest", safariVersionSetThroughSauceSession);
    }

    @Test
    public void defaultSafari_macOsVersionIsMojave() {
        sauceOptions.withSafari();
        startSauceSession();

        String safariVersionSetThroughSauceSession = sauce.getCurrentSessionCapabilities().getPlatform().toString();
        assertEquals(Platforms.MAC_OS.MOJAVE.getPlatform().getOsVersion(), safariVersionSetThroughSauceSession);
    }

    @Test
    public void withSafari_browserName_setToSafariEnum() {
        sauceOptions.withSafari(SafariVersion._8);
        startSauceSession();

        String actualBrowserNameSetThroughSauceSession = sauce.getCurrentSessionCapabilities().getBrowserName();
        assertEquals("safari", actualBrowserNameSetThroughSauceSession);
    }

    @Test
    public void withSafari_browserName_setToSafariString() {
        sauceOptions.withSafari(SafariVersion._8.getVersion());
        startSauceSession();

        String actualBrowserNameSetThroughSauceSession = sauce.getCurrentSessionCapabilities().getBrowserName();
        assertEquals("safari", actualBrowserNameSetThroughSauceSession);
    }

    @Test
    public void withSafari_versionChangedFromDefault_returnsCorrectVersion() {
        sauceOptions.withSafari(SafariVersion._8);
        startSauceSession();

        String actualBrowserVersionSetThroughSauceSession = sauce.getCurrentSessionCapabilities().getVersion();
        assertEquals("8.0", actualBrowserVersionSetThroughSauceSession);
    }

    @Test
    public void withSafari_versionNotSet_returnsLatest() {
        sauceOptions.withSafari("");
        startSauceSession();

        String actualBrowserVersionSetThroughSauceSession =
                sauce.getCurrentSessionCapabilities().getVersion();
        assertEquals("latest", actualBrowserVersionSetThroughSauceSession);
    }
}
