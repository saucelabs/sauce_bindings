package com.saucelabs.simplesauce;

import com.saucelabs.simplesauce.enums.MacVersion;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)
public class MacOsTest extends BaseConfigurationTest {
    @DataProvider
    public static Object[][] expectedMacOsVersions() {
        return new Object[][]{
                {MacVersion.Mojave, "macOS 10.14"},
                {MacVersion.HighSierra, "macOS 10.13"},
                {MacVersion.Sierra, "macOS 10.12"},
                {MacVersion.ElCapitan, "OS X 10.11"},
                {MacVersion.Yosemite, "OS X 10.10"}
        };
    }

    @Test
    @UseDataProvider("expectedMacOsVersions")
    public void withMacOs_returnsValidOsConfiguration(MacVersion version, String expectedMacOsVersion) {
        sauceOptions.withMac(version);
        startSauceSession();

        String actualOsThatWasSet = getSessionPlatformString();
        assertEquals(expectedMacOsVersion, actualOsThatWasSet);
    }

    private String getSessionPlatformString() {
        return sauce.getCurrentSessionCapabilities().getPlatform().toString();
    }

    @Test
    public void defaultSafari_browserVersionIs12_0() {
        sauceOptions.withSafari();
        startSauceSession();

        //TODO mockSauceSession.sauceSessionCapabilities can be turned into a method, maybe on the session
        //class that allows easier access to the caps
        String safariVersionSetThroughSauceSession = sauce.getCurrentSessionCapabilities().getVersion();
        assertEquals("latest", safariVersionSetThroughSauceSession);
    }

    @Test
    public void defaultSafari_macOsVersionIsMojave() {
        sauceOptions.withSafari();
        startSauceSession();

        String safariVersionSetThroughSauceSession = getSessionPlatformString();
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
