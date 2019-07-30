package com.saucelabs.simplesauce;

import org.hamcrest.core.IsNot;
import org.hamcrest.text.IsEqualIgnoringCase;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SauceSessionTest {

    private SauceSession sauceSession;

    @Before
    public void setUp()
    {
        sauceSession = new SauceSession();
    }

    @Test
    public void defaultConstructor_instantiated_setsConcreteDriverManager()
    {
        assertThat(sauceSession.getDriverManager(), instanceOf(ConcreteRemoteDriver.class));
    }

    @Test
    //TODO rename and refactor into logic similar to here: setCapability_platformName_returnsCorrectOs
    public void browserNameCapability_isSetToCorrectKey() throws MalformedURLException {
        RemoteDriverInterface fakeRemoteDriver = mock(RemoteDriverInterface.class);
        sauceSession = new SauceSession(fakeRemoteDriver);
        sauceSession.start();
        String expectedBrowserCapabilityKey = "browserName";
        String actualBrowser = sauceSession.setSauceOptions().getCapability(expectedBrowserCapabilityKey).toString();
        assertThat(actualBrowser, IsNot.not(""));
    }
    @Test
    @Ignore("The problem with this approach is that you need to know which method" +
        "to call to get the desired behavior. However, if we move the logic out from" +
        "the setSauceOptions() method into another method, this test will no longer work." +
        "So this test is implementation specific. The test above is not.")
    public void getCapabilities_browserNameCapSet_validKeyExists2() {
        sauceSession.setSauceOptions();
        String expectedBrowserCapabilityKey = "browserName";
        String actualBrowser = sauceSession.setSauceOptions().getCapability(expectedBrowserCapabilityKey).toString();
        assertThat(actualBrowser, IsNot.not(""));
    }
    @Test
    public void setCapability_platformName_returnsCorrectOs() throws MalformedURLException {
        RemoteDriverInterface fakeRemoteDriver = mock(RemoteDriverInterface.class);
        sauceSession = new SauceSession(fakeRemoteDriver);
        sauceSession.start();
        String correctPlatformKey = "platformName";
        String actualBrowser = sauceSession.setSauceOptions().getCapability(correctPlatformKey).toString();
        assertThat(actualBrowser, IsEqualIgnoringCase.equalToIgnoringCase("Windows 10"));
    }
    @Test
    public void setCapability_browserVersion_returnsCorrectVersion() throws MalformedURLException {
        RemoteDriverInterface fakeRemoteDriver = mock(RemoteDriverInterface.class);
        RemoteWebDriver driver = mock(RemoteWebDriver.class);
        sauceSession = new SauceSession(fakeRemoteDriver);
        when(fakeRemoteDriver.getRemoteWebDriver("abcd", sauceSession.setSauceOptions())).thenReturn(driver);

        sauceSession.start();
        String correctKey = "browserVersion";
        String actualBrowser = sauceSession.setSauceOptions().getCapability(correctKey).toString();
        assertThat(actualBrowser, IsEqualIgnoringCase.equalToIgnoringCase("latest"));
    }
    @Test
    public void noSauceOptionsSet_whenCreated_defaultIsChrome()
    {
        String actualBrowser = sauceSession.setSauceOptions().getBrowserName();
        assertThat(actualBrowser, IsEqualIgnoringCase.equalToIgnoringCase("Chrome"));
    }
    @Test
    public void noSauceOptionsSet_whenCreated_defaultIsWindows10() {
        String actualOs = sauceSession.setSauceOptions().getPlatform().name();
        assertThat(actualOs, IsEqualIgnoringCase.equalToIgnoringCase("win10"));
    }
    @Test
    public void noSauceOptionsSet_whenCreated_latestBrowserVersion()
    {
        MutableCapabilities caps = new SauceSession().setSauceOptions();
        String actualOperatingSystem = caps.getCapability("browserVersion").toString();
        assertThat(actualOperatingSystem, IsEqualIgnoringCase.equalToIgnoringCase("latest"));
    }
    @Test
    public void sauceOptions_defaultConfiguration_setsSauceOptions()
    {
        sauceSession.setSauceOptions();
        boolean hasAccessKey = sauceSession.getSauceOptionsCapability().asMap().containsKey("accessKey");
        assertTrue("You need to have Sauce Credentials set (SAUCE_USERNAME, SAUCE_ACCESSKEY) before this unit test will pass", hasAccessKey);
    }

    @Test
    public void defaultSafari_notSet_returnsLatestVersion()
    {
        RemoteDriverInterface fakeRemoteDriver = mock(RemoteDriverInterface.class);
        sauceSession = new SauceSession(fakeRemoteDriver);
        sauceSession.withSafari();

        String safariVersion = sauceSession.setSauceOptions().getVersion();

        assertThat(safariVersion, IsEqualIgnoringCase.equalToIgnoringCase("latest"));
    }
    @Test
    public void withSafari_browserName_setToSafari()
    {
        sauceSession.withSafari();
        String actualBrowserName = sauceSession.setSauceOptions().getBrowserName();
        assertThat(actualBrowserName, IsEqualIgnoringCase.equalToIgnoringCase("safari"));
    }
    @Test
    public void withSafari_versionChangedFromDefault_returnsCorrectVersion()
    {
        sauceSession.withSafari().withBrowserVersion(SafariVersion.elevenDotOne);
        String safariVersion = sauceSession.setSauceOptions().getVersion();
        assertThat(safariVersion, IsEqualIgnoringCase.equalToIgnoringCase("11.1"));
    }
    @Test
    //TODO How to parameterize this?
    public void withOs_changedFromDefault_returnsCorrectOs()
    {
        sauceSession.withPlatform("Windows 10");
        String actualOs = sauceSession.setSauceOptions().getPlatform().toString();
        assertThat(actualOs, IsEqualIgnoringCase.equalToIgnoringCase("WIN10"));
    }
    @Test
    @Ignore("Future enhancement")
    public void withOs_linux_allowsOnlyChromeOrFirefox()
    {
        sauceSession.withPlatform("Linux");
    }
    @Test
    @Ignore("Future enhancement")
    public void withOs_windows10_doesntAllowSafari() throws MalformedURLException {
        sauceSession.withPlatform("Windows 10");
    }
    @Test
    @Ignore("Future enhancement")
    public void withOs_windows8_1_allowsOnlyChromeOrFfOrIe()
    {
        sauceSession.withPlatform("Windows 8.1");
    }
    @Test
    @Ignore("Future enhancement")
    public void withOs_windows8_allowsOnlyChromeOrFfOrIe()
    {
        sauceSession.withPlatform("Windows 8");
    }
    @Test
    @Ignore("Future enhancement")
    public void withOs_mac_allowsOnlyChromeOrFfOrSafari()
    {
        sauceSession.withPlatform("Windows 8");
    }
    @Test
    @Ignore("Future enhancement")
    public void withSafari_versionChangedToInvalid_shouldNotBePossible()
    {
        //TODO it should not be possible to set an invalid version
        sauceSession.withSafari().withBrowserVersion("1234");
    }
}
