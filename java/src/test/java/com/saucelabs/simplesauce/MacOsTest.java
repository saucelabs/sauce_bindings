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

        String actualOsThatWasSet = sauceOptions.getPlatformName();
        assertEquals(expectedMacOsVersion, actualOsThatWasSet);
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
