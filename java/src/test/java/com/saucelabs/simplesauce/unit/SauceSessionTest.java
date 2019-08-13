package com.saucelabs.simplesauce.unit;

import com.saucelabs.simplesauce.*;
import com.saucelabs.simplesauce.interfaces.EnvironmentManager;
import com.saucelabs.simplesauce.interfaces.RemoteDriverInterface;
import org.hamcrest.core.IsNot;
import org.hamcrest.text.IsEqualIgnoringCase;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.MutableCapabilities;

import java.net.MalformedURLException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SauceSessionTest {

    private SauceSession concreteSauceSession;
    private SauceSession fakeSauceSession;
    private EnvironmentManager fakeEnvironmentManager;

    @Before
    public void setUp()
    {
        concreteSauceSession = new SauceSession();
        RemoteDriverInterface fakeRemoteDriver = mock(RemoteDriverInterface.class);
        fakeEnvironmentManager = mock(EnvironmentManager.class);
        fakeSauceSession = new SauceSession(fakeRemoteDriver, fakeEnvironmentManager);
    }

    @Test
    public void startSession_defaultConfig_usWestDataCenter() throws MalformedURLException
    {
        fakeSauceSession.start();

        String expectedDataCenterUrl = DataCenter.USWest;
        assertThat(fakeSauceSession.sauceDataCenter,
                IsEqualIgnoringCase.equalToIgnoringCase(expectedDataCenterUrl));
    }
    @Test(expected = SauceEnvironmentVariablesNotSetException.class)
    public void getUserName_usernameNotSetInEnvironmentVariable_throwsException() throws SauceEnvironmentVariablesNotSetException {
        fakeSauceSession.getUserName();
    }
    @Test
    public void getUserName_usernameSetInEnvironmentVariable_returnsValue() throws SauceEnvironmentVariablesNotSetException {
        when(fakeEnvironmentManager.getEnvironmentVariable("SAUCE_USERNAME")).thenReturn("test-name");
        String actualUserName = fakeSauceSession.getUserName();
        assertThat(actualUserName, IsNot.not(""));

    }
    @Test(expected = SauceEnvironmentVariablesNotSetException.class)
    public void getAccessKey_keyNotSetInEnvironmentVariable_throwsException() throws SauceEnvironmentVariablesNotSetException {
        fakeSauceSession.getAccessKey();
    }
    @Test
    public void getAccessKey_keySetInEnvironmentVariable_returnsValue() throws SauceEnvironmentVariablesNotSetException {
        when(fakeEnvironmentManager.getEnvironmentVariable(anyString())).thenReturn(anyString());
        fakeSauceSession.getUserName();
    }
    @Test
    public void defaultConstructor_instantiated_setsConcreteDriverManager()
    {
        assertThat(concreteSauceSession.getDriverManager(), instanceOf(ConcreteRemoteDriver.class));
    }

    @Test
    public void startSession_setsBrowserKey() throws MalformedURLException {
        fakeSauceSession.start();
        String expectedBrowserCapabilityKey = "browserName";
        String actualBrowser = fakeSauceSession.sauceSessionCapabilities.getCapability(expectedBrowserCapabilityKey).toString();
        assertThat(actualBrowser, IsNot.not(""));
    }
    @Test
    @Ignore("The problem with this approach is that you need to know which method" +
        "to call to get the desired behavior. However, if we move the logic out from" +
        "the setSauceOptions() method into another method, this test will no longer work." +
        "So this test is implementation specific. The test above is not.")
    public void getCapabilities_browserNameCapSet_validKeyExists2() {
        concreteSauceSession.setSauceOptions();
        String expectedBrowserCapabilityKey = "browserName";
        String actualBrowser = concreteSauceSession.setSauceOptions().getCapability(expectedBrowserCapabilityKey).toString();
        assertThat(actualBrowser, IsNot.not(""));
    }
    @Test
    public void setCapability_platformName_returnsCorrectOs() throws MalformedURLException {
        fakeSauceSession.start();
        String correctPlatformKey = "platformName";
        String actualBrowser = fakeSauceSession.setSauceOptions().getCapability(correctPlatformKey).toString();
        assertThat(actualBrowser, IsEqualIgnoringCase.equalToIgnoringCase("Windows 10"));
    }
    @Test
    public void setCapability_browserVersion_returnsCorrectVersion() throws MalformedURLException {
        fakeSauceSession.start();
        String correctKey = "browserVersion";
        String actualBrowser = fakeSauceSession.setSauceOptions().getCapability(correctKey).toString();
        assertThat(actualBrowser, IsEqualIgnoringCase.equalToIgnoringCase("latest"));
    }
    @Test
    public void noSauceOptionsSet_whenCreated_defaultIsChrome()
    {
        String actualBrowser = concreteSauceSession.setSauceOptions().getBrowserName();
        assertThat(actualBrowser, IsEqualIgnoringCase.equalToIgnoringCase("Chrome"));
    }
    @Test
    public void noSauceOptionsSet_whenCreated_defaultIsWindows10() {
        String actualOs = concreteSauceSession.setSauceOptions().getPlatform().name();
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
        concreteSauceSession.setSauceOptions();
        boolean hasAccessKey = concreteSauceSession.getSauceOptionsCapability().asMap().containsKey("accessKey");
        assertTrue("You need to have Sauce Credentials set (SAUCE_USERNAME, SAUCE_ACCESSKEY) before this unit test will pass", hasAccessKey);
    }

    @Test
    public void defaultSafari_notSet_returnsLatestVersion()
    {
        fakeSauceSession.withSafari();

        String safariVersion = fakeSauceSession.setSauceOptions().getVersion();

        assertThat(safariVersion, IsEqualIgnoringCase.equalToIgnoringCase("latest"));
    }
    @Test
    public void withSafari_browserName_setToSafari()
    {
        fakeSauceSession.withSafari();
        String actualBrowserName = fakeSauceSession.setSauceOptions().getBrowserName();
        assertThat(actualBrowserName, IsEqualIgnoringCase.equalToIgnoringCase("safari"));
    }
    @Test
    public void withSafari_versionChangedFromDefault_returnsCorrectVersion()
    {
        fakeSauceSession.withSafari().withBrowserVersion("11.1");
        String safariVersion = fakeSauceSession.setSauceOptions().getVersion();
        assertThat(safariVersion, IsEqualIgnoringCase.equalToIgnoringCase("11.1"));
    }
    @Test
    //TODO How to parameterize this?
    public void withOs_changedFromDefault_returnsCorrectOs()
    {
        fakeSauceSession.withPlatform("Windows 10");
        String actualOs = fakeSauceSession.setSauceOptions().getPlatform().toString();
        assertThat(actualOs, IsEqualIgnoringCase.equalToIgnoringCase("WIN10"));
    }
    @Test
    @Ignore("Future enhancement")
    public void withOs_linux_allowsOnlyChromeOrFirefox()
    {
        fakeSauceSession.withPlatform("Linux");
    }
    @Test
    @Ignore("Future enhancement")
    public void withOs_windows10_doesntAllowSafari() throws MalformedURLException {
        fakeSauceSession.withPlatform("Windows 10");
    }
    @Test
    @Ignore("Future enhancement")
    public void withOs_windows8_1_allowsOnlyChromeOrFfOrIe()
    {
        fakeSauceSession.withPlatform("Windows 8.1");
    }
    @Test
    @Ignore("Future enhancement")
    public void withOs_windows8_allowsOnlyChromeOrFfOrIe()
    {
        fakeSauceSession.withPlatform("Windows 8");
    }
    @Test
    @Ignore("Future enhancement")
    public void withOs_mac_allowsOnlyChromeOrFfOrSafari()
    {
        fakeSauceSession.withPlatform("Windows 8");
    }
    @Test
    @Ignore("Future enhancement")
    public void withSafari_versionChangedToInvalid_shouldNotBePossible()
    {
        //TODO it should not be possible to set an invalid version
        fakeSauceSession.withSafari().withBrowserVersion("1234");
    }
}
