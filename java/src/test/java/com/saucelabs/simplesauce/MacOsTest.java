package com.saucelabs.simplesauce;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MacOsTest extends BaseConfigurationTest{
    @Test
    public void withMacOsMojave_returnsMacOs1014() {
        sauceOptions.withMacOsMojave();
        mockSauceSession = instantiateSauceSession();

        mockSauceSession.start();
        String actualOsThatWasSet = getSessionPlatformString();
        assertEquals(Platforms.MAC_OS.MOJAVE, actualOsThatWasSet);
    }

    private String getSessionPlatformString() {
        return mockSauceSession.currentSessionCapabilities.getPlatform().toString();
    }

    @Test
    public void withMacOsHighSierra_returnsMacOs1013() {
        sauceOptions.withMacOsHighSierra();
        mockSauceSession = instantiateSauceSession();

        mockSauceSession.start();
        String actualOsThatWasSet = getSessionPlatformString();
        assertEquals(Platforms.MAC_OS.HIGH_SIERRA, actualOsThatWasSet);
    }
    @Test
    public void withMacOsSierra_returnsMacOs1012() {
        sauceOptions.withMacOsSierra();
        mockSauceSession = instantiateSauceSession();

        mockSauceSession.start();
        String actualOsThatWasSet = getSessionPlatformString();
        assertEquals(Platforms.MAC_OS.SIERRA, actualOsThatWasSet);
    }
    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void withMacOsElCapitan_returnsMacOs1011() {
        sauceOptions.withMacOsXElCapitan();
        mockSauceSession = instantiateSauceSession();

        mockSauceSession.start();
        String actualOsThatWasSet = getSessionPlatformString();
        assertEquals(Platforms.MAC_OS.EL_CAPITAN, actualOsThatWasSet);
    }
    @Test
    public void withMacOsYosemite_returnsMacOsX1010() {
        sauceOptions.withMacOsXYosemite();
        mockSauceSession = instantiateSauceSession();

        mockSauceSession.start();
        String actualOsThatWasSet = getSessionPlatformString();
        assertEquals(Platforms.MAC_OS.YOSEMITE, actualOsThatWasSet);
    }
    @Test
    public void defaultSafari_browserVersionIs12_0() {
        sauceOptions.withSafari();
        mockSauceSession = instantiateSauceSession();

        mockSauceSession.start();

        //TODO mockSauceSession.sauceSessionCapabilities can be turned into a method, maybe on the session
        //class that allows easier access to the caps
        String safariVersionSetThroughSauceSession = mockSauceSession.currentSessionCapabilities.getVersion();
        assertEquals("12.0", safariVersionSetThroughSauceSession);
    }
    @Test
    public void defaultSafari_macOsVersionIsMojave() {
        sauceOptions.withSafari();
        mockSauceSession = instantiateSauceSession();

        mockSauceSession.start();

        String safariVersionSetThroughSauceSession = getSessionPlatformString();
        assertEquals(Platforms.MAC_OS.MOJAVE, safariVersionSetThroughSauceSession);
    }
    @Test
    public void withSafari_browserName_setToSafari() {
        sauceOptions.withSafari(SafariVersion._8);
        mockSauceSession = instantiateSauceSession();

        mockSauceSession.start();

        String actualBrowserNameSetThroughSauceSession = mockSauceSession.currentSessionCapabilities.getBrowserName();
        assertEquals("safari", actualBrowserNameSetThroughSauceSession);
    }
    @Test
    public void withSafari_versionChangedFromDefault_returnsCorrectVersion() {
        sauceOptions.withSafari(SafariVersion._8);
        mockSauceSession = instantiateSauceSession();

        mockSauceSession.start();

        String actualBrowserVersionSetThroughSauceSession = mockSauceSession.currentSessionCapabilities.getVersion();
        assertEquals("8.0", actualBrowserVersionSetThroughSauceSession);
    }
    @Test
    public void withSafari_versionNotSet_returnsLatest() {
        sauceOptions.withSafari("");
        mockSauceSession = instantiateSauceSession();

        mockSauceSession.start();

        String actualBrowserVersionSetThroughSauceSession =
                mockSauceSession.currentSessionCapabilities.getVersion();
        assertEquals("latest", actualBrowserVersionSetThroughSauceSession);
    }
}
