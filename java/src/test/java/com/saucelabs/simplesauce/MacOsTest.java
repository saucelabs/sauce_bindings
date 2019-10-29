package com.saucelabs.simplesauce;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MacOsTest extends BaseConfigurationTest{
    @Test
    public void withMacOsMojave_returnsMacOs1014() {
        sauceOptions.withMacOsMojave();
        sauce = instantiateSauceSession();

        sauce.start();
        String actualOsThatWasSet = getSessionPlatformString();
        assertEquals(Platforms.MAC_OS.MOJAVE, actualOsThatWasSet);
    }

    private String getSessionPlatformString() {
        return sauce.currentSessionCapabilities.getPlatform().toString();
    }

    @Test
    public void withMacOsHighSierra_returnsMacOs1013() {
        sauceOptions.withMacOsHighSierra();
        sauce = instantiateSauceSession();

        sauce.start();
        String actualOsThatWasSet = getSessionPlatformString();
        assertEquals(Platforms.MAC_OS.HIGH_SIERRA, actualOsThatWasSet);
    }
    @Test
    public void withMacOsSierra_returnsMacOs1012() {
        sauceOptions.withMacOsSierra();
        sauce = instantiateSauceSession();

        sauce.start();
        String actualOsThatWasSet = getSessionPlatformString();
        assertEquals(Platforms.MAC_OS.SIERRA, actualOsThatWasSet);
    }
    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void withMacOsElCapitan_returnsMacOs1011() {
        sauceOptions.withMacOsXElCapitan();
        sauce = instantiateSauceSession();

        sauce.start();
        String actualOsThatWasSet = getSessionPlatformString();
        assertEquals(Platforms.MAC_OS.EL_CAPITAN, actualOsThatWasSet);
    }
    @Test
    public void withMacOsYosemite_returnsMacOsX1010() {
        sauceOptions.withMacOsXYosemite();
        sauce = instantiateSauceSession();

        sauce.start();
        String actualOsThatWasSet = getSessionPlatformString();
        assertEquals(Platforms.MAC_OS.YOSEMITE, actualOsThatWasSet);
    }
    @Test
    public void defaultSafari_browserVersionIs12_0() {
        sauceOptions.withSafari();
        sauce = instantiateSauceSession();

        sauce.start();

        //TODO mockSauceSession.sauceSessionCapabilities can be turned into a method, maybe on the session
        //class that allows easier access to the caps
        String safariVersionSetThroughSauceSession = sauce.currentSessionCapabilities.getVersion();
        assertEquals("12.0", safariVersionSetThroughSauceSession);
    }
    @Test
    public void defaultSafari_macOsVersionIsMojave() {
        sauceOptions.withSafari();
        sauce = instantiateSauceSession();

        sauce.start();

        String safariVersionSetThroughSauceSession = getSessionPlatformString();
        assertEquals(Platforms.MAC_OS.MOJAVE, safariVersionSetThroughSauceSession);
    }
    @Test
    public void withSafari_browserName_setToSafari() {
        sauceOptions.withSafari(SafariVersion._8);
        sauce = instantiateSauceSession();

        sauce.start();

        String actualBrowserNameSetThroughSauceSession = sauce.currentSessionCapabilities.getBrowserName();
        assertEquals("safari", actualBrowserNameSetThroughSauceSession);
    }
    @Test
    public void withSafari_versionChangedFromDefault_returnsCorrectVersion() {
        sauceOptions.withSafari(SafariVersion._8);
        sauce = instantiateSauceSession();

        sauce.start();

        String actualBrowserVersionSetThroughSauceSession = sauce.currentSessionCapabilities.getVersion();
        assertEquals("8.0", actualBrowserVersionSetThroughSauceSession);
    }
    @Test
    public void withSafari_versionNotSet_returnsLatest() {
        sauceOptions.withSafari("");
        sauce = instantiateSauceSession();

        sauce.start();

        String actualBrowserVersionSetThroughSauceSession =
                sauce.currentSessionCapabilities.getVersion();
        assertEquals("latest", actualBrowserVersionSetThroughSauceSession);
    }
}
