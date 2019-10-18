package com.saucelabs.simplesauce.unit;

import com.saucelabs.simplesauce.Platforms;
import com.saucelabs.simplesauce.SafariVersion;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.Assert.assertEquals;

public class MacOsTests extends BaseConfigurationTest{
    @Test
    public void withMacOsMojave_returnsMacOs1014() {
        sauceOptions.withMacOsMojave();
        mockSauceSession = instantiateSauceSession();

        mockSauceSession.start();
        String actualOsThatWasSet = getSessionPlatformString();
        assertEquals("macOS 10.14", actualOsThatWasSet);
    }

    private String getSessionPlatformString() {
        return mockSauceSession.sauceSessionCapabilities.getPlatform().toString();
    }

    @Test
    public void withMacOsHighSierra_returnsMacOs1013() {
        sauceOptions.withMacOsHighSierra();
        mockSauceSession = instantiateSauceSession();

        mockSauceSession.start();
        String actualOsThatWasSet = getSessionPlatformString();
        assertEquals("macOS 10.13", actualOsThatWasSet);
    }
    @Test
    public void withMacOsSierra_returnsMacOs1012() {
        sauceOptions.withMacOsSierra();
        mockSauceSession = instantiateSauceSession();

        mockSauceSession.start();
        String actualOsThatWasSet = getSessionPlatformString();
        assertEquals("macOS 10.12", actualOsThatWasSet);
    }
    @Test
    public void withMacOsElCapitan_returnsMacOs1011() {
        sauceOptions.withMacOsXElCapitan();
        mockSauceSession = instantiateSauceSession();

        mockSauceSession.start();
        String actualOsThatWasSet = getSessionPlatformString();
        assertEquals("OS X 10.11", actualOsThatWasSet);
    }
    @Test
    public void withMacOsYosemite_returnsMacOsX1010() {
        sauceOptions.withMacOsXYosemite();
        mockSauceSession = instantiateSauceSession();

        mockSauceSession.start();
        String actualOsThatWasSet = getSessionPlatformString();
        assertEquals("OS X 10.10", actualOsThatWasSet);
    }
    @Test
    public void defaultSafari_browserVersionIs12_0() {
        sauceOptions.withSafari();
        mockSauceSession = instantiateSauceSession();

        mockSauceSession.start();

        //TODO mockSauceSession.sauceSessionCapabilities can be turned into a method, maybe on the session
        //class that allows easier access to the caps
        String safariVersionSetThroughSauceSession = mockSauceSession.sauceSessionCapabilities.getVersion();
        assertEquals("12.0", safariVersionSetThroughSauceSession);
    }
    @Test
    public void defaultSafari_macOsVersionIsMojave() {
        sauceOptions.withSafari();
        mockSauceSession = instantiateSauceSession();

        mockSauceSession.start();

        String safariVersionSetThroughSauceSession = getSessionPlatformString();
        assertThat(Platforms.MAC_OS_MOJAVE, equalToIgnoringCase(safariVersionSetThroughSauceSession));
    }
    @Test
    public void withSafari_browserName_setToSafari() {
        sauceOptions.withSafari(SafariVersion._8);
        mockSauceSession = instantiateSauceSession();

        mockSauceSession.start();

        String actualBrowserNameSetThroughSauceSession = mockSauceSession.sauceSessionCapabilities.getBrowserName();
        assertEquals("safari", actualBrowserNameSetThroughSauceSession);
    }
    @Test
    public void withSafari_versionChangedFromDefault_returnsCorrectVersion() {
        sauceOptions.withSafari(SafariVersion._8);
        mockSauceSession = instantiateSauceSession();

        mockSauceSession.start();

        String actualBrowserVersionSetThroughSauceSession = mockSauceSession.sauceSessionCapabilities.getVersion();
        assertEquals("8.0", actualBrowserVersionSetThroughSauceSession);
    }
    @Test
    public void withSafari_versionNotSet_returnsLatest() {
        sauceOptions.withSafari("");
        mockSauceSession = instantiateSauceSession();

        mockSauceSession.start();

        String actualBrowserVersionSetThroughSauceSession =
                mockSauceSession.sauceSessionCapabilities.getVersion();
        assertEquals("latest", actualBrowserVersionSetThroughSauceSession);
    }
}
