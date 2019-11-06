package com.saucelabs.simplesauce;

import com.saucelabs.simplesauce.enums.MacVersion;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.Assert.assertEquals;

@RunWith(DataProviderRunner.class)
public class MacOsTest extends BaseConfigurationTest{

    @DataProvider
    public static Object[][] expectedMacOsVersions() {
        return new Object[][] {
                { MacVersion.Mojave, "macOS 10.14" },
                { MacVersion.HighSierra, "macOS 10.13" },
                { MacVersion.Sierra, "macOS 10.12" },
                { MacVersion.ElCapitan, "OS X 10.11" },
                { MacVersion.Yosemite, "OS X 10.10" }
        };
    }

    @Test
    @UseDataProvider("expectedMacOsVersions")
    public void withMacOs_returnsValidOsConfiguration(MacVersion version, String expectedMacOsVersion) {
        sauceOptions.withMac(version);
        mockSauceSession = instantiateSauceSession();

        mockSauceSession.start();
        String actualOsThatWasSet = getSessionPlatformString();
        assertEquals(expectedMacOsVersion, actualOsThatWasSet);
    }

    private String getSessionPlatformString() {
        return mockSauceSession.sauceSessionCapabilities.getPlatform().toString();
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
