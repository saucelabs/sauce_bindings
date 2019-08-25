package com.saucelabs.simplesauce.unit;

import com.saucelabs.simplesauce.Platforms;
import com.saucelabs.simplesauce.SafariVersion;
import org.junit.Test;

import java.net.MalformedURLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.Assert.assertEquals;

public class MacOsTests extends BaseConfigurationTest{
    @Test
    public void withMacOsMojave_returnsMacOs1014() {
        fakeSauceSession.withMacOsMojave();
        fakeSauceSession.start();
        String actualOsThatWasSet = fakeSauceSession.sauceSessionCapabilities.getPlatform().toString();
        assertEquals("macOS 10.14", actualOsThatWasSet);
    }
    @Test
    public void withMacOsHighSierra_returnsMacOs1013() {
        fakeSauceSession.withMacOsHighSierra();
        fakeSauceSession.start();
        String actualOsThatWasSet = fakeSauceSession.sauceSessionCapabilities.getPlatform().toString();
        assertEquals("macOS 10.13", actualOsThatWasSet);
    }
    @Test
    public void withMacOsSierra_returnsMacOs1012() {
        fakeSauceSession.withMacOsSierra();
        fakeSauceSession.start();
        String actualOsThatWasSet = fakeSauceSession.sauceSessionCapabilities.getPlatform().toString();
        assertEquals("macOS 10.12", actualOsThatWasSet);
    }
    @Test
    public void withMacOsElCapitan_returnsMacOs1011() {
        fakeSauceSession.withMacOsXElCapitan();
        fakeSauceSession.start();
        String actualOsThatWasSet = fakeSauceSession.sauceSessionCapabilities.getPlatform().toString();
        assertEquals("OS X 10.11", actualOsThatWasSet);
    }
    @Test
    public void withMacOsYosemite_returnsMacOsX1010() {
        fakeSauceSession.withMacOsXYosemite();
        fakeSauceSession.start();
        String actualOsThatWasSet = fakeSauceSession.sauceSessionCapabilities.getPlatform().toString();
        assertEquals("OS X 10.10", actualOsThatWasSet);
    }
    @Test
    public void defaultSafari_browserVersionIs12_0() {
        fakeSauceSession.withSafari();
        fakeSauceSession.start();

        String safariVersionSetThroughSauceSession = fakeSauceSession.sauceSessionCapabilities.getVersion();
        assertEquals("12.0", safariVersionSetThroughSauceSession);
    }
    @Test
    public void defaultSafari_macOsVersionIsMojave() {
        fakeSauceSession.withSafari();
        fakeSauceSession.start();

        String safariVersionSetThroughSauceSession = fakeSauceSession.sauceSessionCapabilities.getPlatform().toString();
        assertThat(Platforms.MAC_OS_MOJAVE, equalToIgnoringCase(safariVersionSetThroughSauceSession));
    }
    @Test
    public void withSafari_browserName_setToSafari() {
        fakeSauceSession.withSafari(SafariVersion._8);
        fakeSauceSession.start();

        String actualBrowserNameSetThroughSauceSession = fakeSauceSession.sauceSessionCapabilities.getBrowserName();
        assertEquals("safari", actualBrowserNameSetThroughSauceSession);
    }
    @Test
    public void withSafari_versionChangedFromDefault_returnsCorrectVersion() {
        fakeSauceSession.withSafari(SafariVersion._8);
        fakeSauceSession.start();

        String actualBrowserVersionSetThroughSauceSession = fakeSauceSession.sauceSessionCapabilities.getVersion();
        assertEquals("8.0", actualBrowserVersionSetThroughSauceSession);
    }
}
