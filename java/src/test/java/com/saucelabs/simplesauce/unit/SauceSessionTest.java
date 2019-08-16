package com.saucelabs.simplesauce.unit;

import com.saucelabs.simplesauce.ConcreteRemoteDriver;
import com.saucelabs.simplesauce.DataCenter;
import com.saucelabs.simplesauce.SauceSession;
import com.saucelabs.simplesauce.interfaces.EnvironmentManager;
import com.saucelabs.simplesauce.interfaces.RemoteDriverInterface;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.net.MalformedURLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SauceSessionTest {

    private SauceSession concreteSauceSession;
    private SauceSession fakeSauceSession;
    private EnvironmentManager fakeEnvironmentManager;

    @Before
    public void setUp() throws MalformedURLException {
        concreteSauceSession = new SauceSession();
        RemoteDriverInterface fakeRemoteDriver = mock(RemoteDriverInterface.class);
        fakeEnvironmentManager = mock(EnvironmentManager.class);
        fakeSauceSession = new SauceSession(fakeRemoteDriver, fakeEnvironmentManager);
        when(fakeEnvironmentManager.getEnvironmentVariable("SAUCE_USERNAME")).thenReturn("test-name");
        when(fakeEnvironmentManager.getEnvironmentVariable("SAUCE_ACCESS_KEY")).thenReturn("accessKey");

        fakeSauceSession.start();
    }

    @Test
    public void startSession_defaultConfig_usWestDataCenter() {
        String expectedDataCenterUrl = DataCenter.USWest;
        assertEquals(expectedDataCenterUrl, fakeSauceSession.sauceDataCenter);
    }
    @Test
    public void getUserName_usernameSetInEnvironmentVariable_returnsValue()  {
        when(fakeEnvironmentManager.getEnvironmentVariable("SAUCE_USERNAME")).thenReturn("test-name");
        String actualUserName = fakeSauceSession.getUserName();
        assertNotEquals("",actualUserName);

    }
    @Test
    public void getAccessKey_keySetInEnvironmentVariable_returnsValue() {
        when(fakeEnvironmentManager.getEnvironmentVariable("SAUCE_ACCESS_KEY")).thenReturn("accessKey");
        String actualAccessKey = fakeSauceSession.getAccessKey();
        assertNotEquals("", actualAccessKey);
    }
    @Test
    public void defaultConstructor_instantiated_setsConcreteDriverManager()
    {
        assertTrue(concreteSauceSession.getDriverManager() instanceof ConcreteRemoteDriver);
    }

    @Test
    public void startSession_setsBrowserKey() {
        String expectedBrowserCapabilityKey = "browserName";
        String actualBrowser = fakeSauceSession.sauceSessionCapabilities.getCapability(expectedBrowserCapabilityKey).toString();
        assertNotEquals("", actualBrowser);
    }
    @Test
    public void start_setsPlatformNameKey() {
        String correctPlatformKey = "platformName";
        String browserSetInSauceSession = fakeSauceSession.sauceSessionCapabilities.getCapability(correctPlatformKey).toString();
        assertEquals("Windows 10", browserSetInSauceSession);
    }
    @Test
    public void defaultBrowserIsLatest() {
        String correctKey = "browserVersion";
        String browserSetThroughSauceSession = fakeSauceSession.sauceSessionCapabilities.getCapability(correctKey).toString();
        assertEquals("latest", browserSetThroughSauceSession);
    }
    @Test
    public void defaultIsChrome()
    {
        String actualBrowser = fakeSauceSession.sauceSessionCapabilities.getBrowserName();
        assertEquals("Chrome", actualBrowser);
    }
    @Test
    public void defaultIsWindows10() {
        String actualOs = fakeSauceSession.sauceSessionCapabilities.getPlatform().name();
        assertEquals("WIN10", actualOs);
    }
    @Test
    public void sauceOptions_defaultConfiguration_setsSauceOptions() {
        boolean hasAccessKey = fakeSauceSession.getSauceOptionsCapability().asMap().containsKey("accessKey");
        assertTrue("You need to have Sauce Credentials set (SAUCE_USERNAME, SAUCE_ACCESSKEY) before this unit test will pass", hasAccessKey);
    }

    @Test
    public void defaultSafari_notSet_returnsLatestVersion() throws MalformedURLException {
        fakeSauceSession.withSafari();
        fakeSauceSession.start();

        String safariVersionSetThroughSauceSession = fakeSauceSession.sauceSessionCapabilities.getVersion();
        assertEquals("latest", safariVersionSetThroughSauceSession);
    }
    @Test
    public void withSafari_browserName_setToSafari() throws MalformedURLException {
        fakeSauceSession.withSafari();
        fakeSauceSession.start();

        String actualBrowserNameSetThroughSauceSession = fakeSauceSession.sauceSessionCapabilities.getBrowserName();
        assertEquals("safari", actualBrowserNameSetThroughSauceSession);
    }
    @Test
    public void withSafari_versionChangedFromDefault_returnsCorrectVersion() throws MalformedURLException {
        fakeSauceSession.withSafari().withBrowserVersion("11.1");
        fakeSauceSession.start();

        String actualBrowserVersionSetThroughSauceSession = fakeSauceSession.sauceSessionCapabilities.getVersion();
        assertEquals("11.1", actualBrowserVersionSetThroughSauceSession);
    }
    @Test
    //TODO How to parameterize this?
    public void withOs_changedFromDefault_returnsCorrectOs()
    {
        fakeSauceSession.withPlatform("Windows 10");
        String actualOsSetThroughSauceSession = fakeSauceSession.sauceSessionCapabilities.getPlatform().toString();
        assertEquals("WIN10", actualOsSetThroughSauceSession);
    }
    @Test
    @Ignore("Future enhancement")
    public void withOs_linux_allowsOnlyChromeOrFirefox()
    {
        fakeSauceSession.withPlatform("Linux");
        fail();
    }
    @Test
    @Ignore("Future enhancement")
    public void withOs_windows10_doesntAllowSafari() {
        fakeSauceSession.withPlatform("Windows 10");
        fail();
    }
    @Test
    @Ignore("Future enhancement")
    public void withOs_windows8_1_allowsOnlyChromeOrFfOrIe()
    {
        fakeSauceSession.withPlatform("Windows 8.1");
        fail();
    }
    @Test
    @Ignore("Future enhancement")
    public void withOs_windows8_allowsOnlyChromeOrFfOrIe()
    {
        fakeSauceSession.withPlatform("Windows 8");
        fail();
    }
    @Test
    @Ignore("Future enhancement")
    public void withOs_mac_allowsOnlyChromeOrFfOrSafari()
    {
        fakeSauceSession.withPlatform("Windows 8");
        fail();
    }
    @Test
    @Ignore("Future enhancement")
    public void withSafari_versionChangedToInvalid_shouldNotBePossible()
    {
        //TODO it should not be possible to set an invalid version
        fakeSauceSession.withSafari().withBrowserVersion("1234");
        fail();
    }
}
