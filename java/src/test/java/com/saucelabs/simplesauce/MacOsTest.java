package com.saucelabs.simplesauce;

import com.saucelabs.simplesauce.enums.MacVersion;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MacOsTest extends BaseTestConfiguration {
    @Test
    public void withMojave_returnsValidOsConfiguration() {
        sauceOptions.withMac(MacVersion.Mojave);
        sauce = instantiateSauceSession();
        sauce.start();

        assertEquals("macOS 10.14", sauce.getCurrentSessionCapabilities().getPlatform().toString());
    }

    @Test
    public void withHighSierra_returnsValidOsConfiguration() {
        sauceOptions.withMac(MacVersion.HighSierra);
        sauce = instantiateSauceSession();
        sauce.start();

        assertEquals("macOS 10.13", sauce.getCurrentSessionCapabilities().getPlatform().toString());
    }

    @Test
    public void withSierra_returnsValidOsConfiguration() {
        sauceOptions.withMac(MacVersion.Sierra);
        sauce = instantiateSauceSession();
        sauce.start();

        assertEquals("macOS 10.12", sauce.getCurrentSessionCapabilities().getPlatform().toString());
    }

    @Test
    public void withElCapitan_returnsValidOsConfiguration() {
        sauceOptions.withMac(MacVersion.ElCapitan);
        sauce = instantiateSauceSession();
        sauce.start();

        assertEquals("OS X 10.11", sauce.getCurrentSessionCapabilities().getPlatform().toString());
    }

    @Test
    public void withYosemite_returnsValidOsConfiguration() {
        sauceOptions.withMac(MacVersion.Yosemite);
        sauce = instantiateSauceSession();
        sauce.start();

        assertEquals("OS X 10.10", sauce.getCurrentSessionCapabilities().getPlatform().toString());
    }

    @Test
    public void defaultSafari_browserVersionIs12_0() {
        sauceOptions.withSafari();
        sauce = instantiateSauceSession();
        sauce.start();

        String safariVersionSetThroughSauceSession = sauce.getCurrentSessionCapabilities().getVersion();
        assertEquals("latest", safariVersionSetThroughSauceSession);
    }

    @Test
    public void defaultSafari_macOsVersionIsMojave() {
        sauceOptions.withSafari();
        sauce = instantiateSauceSession();
        sauce.start();

        String safariVersionSetThroughSauceSession = sauce.getCurrentSessionCapabilities().getPlatform().toString();
        assertEquals(Platforms.MAC_OS.MOJAVE.getPlatform().getOsVersion(), safariVersionSetThroughSauceSession);
    }

    @Test
    public void withSafari_browserName_setToSafariEnum() {
        sauceOptions.withSafari(SafariVersion._8);
        sauce = instantiateSauceSession();
        sauce.start();

        String actualBrowserNameSetThroughSauceSession = sauce.getCurrentSessionCapabilities().getBrowserName();
        assertEquals("safari", actualBrowserNameSetThroughSauceSession);
    }

    @Test
    public void withSafari_browserName_setToSafariString() {
        sauceOptions.withSafari(SafariVersion._8.getVersion());
        sauce = instantiateSauceSession();
        sauce.start();

        String actualBrowserNameSetThroughSauceSession = sauce.getCurrentSessionCapabilities().getBrowserName();
        assertEquals("safari", actualBrowserNameSetThroughSauceSession);
    }

    @Test
    public void withSafari_versionChangedFromDefault_returnsCorrectVersion() {
        sauceOptions.withSafari(SafariVersion._8);
        sauce = instantiateSauceSession();
        sauce.start();

        String actualBrowserVersionSetThroughSauceSession = sauce.getCurrentSessionCapabilities().getVersion();
        assertEquals("8.0", actualBrowserVersionSetThroughSauceSession);
    }
    @Test
    public void withSafari_versionNotSet_returnsLatest() {
        sauceOptions.withSafari("");
        sauce = instantiateSauceSession();
        sauce.start();

        String actualBrowserVersionSetThroughSauceSession =
                sauce.getCurrentSessionCapabilities().getVersion();
        assertEquals("latest", actualBrowserVersionSetThroughSauceSession);
    }
}
