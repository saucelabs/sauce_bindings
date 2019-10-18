package com.saucelabs.simplesauce;

import com.saucelabs.simplesauce.ConcreteRemoteDriver;
import com.saucelabs.simplesauce.DataCenter;
import com.saucelabs.simplesauce.SauceOptions;
import com.saucelabs.simplesauce.SauceSession;
import com.saucelabs.simplesauce.interfaces.EnvironmentManager;
import com.saucelabs.simplesauce.interfaces.RemoteDriverInterface;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SauceSessionTest {

    //TODO duplication in 3 classes, excluding DataCenterTest
    private SauceSession mockSauceSession;
    private EnvironmentManager fakeEnvironmentManager;

    @Before
    public void setUp() {
        RemoteDriverInterface fakeRemoteDriver = mock(RemoteDriverInterface.class);
        fakeEnvironmentManager = mock(EnvironmentManager.class);
        mockSauceSession = new SauceSession(fakeRemoteDriver, fakeEnvironmentManager);
        when(fakeEnvironmentManager.getEnvironmentVariable("SAUCE_USERNAME")).thenReturn("test-name");
        when(fakeEnvironmentManager.getEnvironmentVariable("SAUCE_ACCESS_KEY")).thenReturn("accessKey");

        mockSauceSession.start();
    }
    @Test
    public void sauceSession_takesSauceOptions() {
        SauceOptions options = new SauceOptions();
        SauceSession session = new SauceSession(options);
        assertNotNull(session);
    }
    @Test
    public void sauceSession_defaultSauceOptions_returnsChromeBrowser() {
        SauceOptions options = new SauceOptions();
        RemoteDriverInterface fakeRemoteDriver = mock(RemoteDriverInterface.class);

        SauceSession session = new SauceSession(options,fakeRemoteDriver, fakeEnvironmentManager);
        session.start();
        String actualBrowser = session.sauceSessionCapabilities.getCapability("browserName").toString();
        assertEquals("Chrome", actualBrowser);
    }
    @Test
    public void startSession_defaultConfig_usWestDataCenter() {
        String expectedDataCenterUrl = DataCenter.USWest;
        assertEquals(expectedDataCenterUrl, mockSauceSession.sauceDataCenter);
    }
    @Test
    public void getUserName_usernameSetInEnvironmentVariable_returnsValue()  {
        when(fakeEnvironmentManager.getEnvironmentVariable("SAUCE_USERNAME")).thenReturn("test-name");
        String actualUserName = mockSauceSession.getUserName();
        assertNotEquals("",actualUserName);
    }
    @Test
    public void getAccessKey_keySetInEnvironmentVariable_returnsValue() {
        when(fakeEnvironmentManager.getEnvironmentVariable("SAUCE_ACCESS_KEY")).thenReturn("accessKey");
        String actualAccessKey = mockSauceSession.getAccessKey();
        assertNotEquals("", actualAccessKey);
    }
    @Test
    public void defaultConstructor_instantiated_setsConcreteDriverManager()
    {
        SauceSession concreteSauceSession = new SauceSession();
        assertTrue(concreteSauceSession.getDriverManager() instanceof ConcreteRemoteDriver);
    }

    @Test
    public void startSession_setsBrowserKey() {
        String expectedBrowserCapabilityKey = "browserName";
        String actualBrowser = mockSauceSession.sauceSessionCapabilities.getCapability(expectedBrowserCapabilityKey).toString();
        assertNotEquals("", actualBrowser);
    }
    @Test
    public void start_setsPlatformNameKey() {
        String correctPlatformKey = "platformName";
        String browserSetInSauceSession = mockSauceSession.sauceSessionCapabilities.getCapability(correctPlatformKey).toString();
        assertEquals("Windows 10", browserSetInSauceSession);
    }
    @Test
    public void defaultBrowserIsLatest() {
        String correctKey = "browserVersion";
        String browserSetThroughSauceSession = mockSauceSession.sauceSessionCapabilities.getCapability(correctKey).toString();
        assertEquals("latest", browserSetThroughSauceSession);
    }
    @Test
    public void defaultIsChrome()
    {
        String actualBrowser = mockSauceSession.sauceSessionCapabilities.getBrowserName();
        assertEquals("Chrome", actualBrowser);
    }
    @Test
    public void defaultIsWindows10() {
        String actualOs = mockSauceSession.sauceSessionCapabilities.getPlatform().name();
        assertEquals("WIN10", actualOs);
    }
    @Test
    public void sauceOptions_defaultConfiguration_setsSauceOptions() {
        boolean hasAccessKey = mockSauceSession.getSauceOptionsCapability().asMap().containsKey("accessKey");
        assertTrue("You need to have Sauce Credentials set (SAUCE_USERNAME, SAUCE_ACCESSKEY) before this unit test will pass", hasAccessKey);
    }
    @Test
    public void sauceOptions_startWithChrome_startsChrome() {
        RemoteDriverInterface fakeRemoteDriver = mock(RemoteDriverInterface.class);
        SauceOptions options = new SauceOptions();
        options.withChrome();
        mockSauceSession = new SauceSession(options, fakeRemoteDriver, fakeEnvironmentManager);
        mockSauceSession.start();
        String actualBrowser = mockSauceSession.sauceSessionCapabilities.getBrowserName();
        assertEquals("Chrome", actualBrowser);
    }

    @Test
    @Ignore("Future enhancement")
    public void withOs_linux_allowsOnlyChromeOrFirefox()
    {
        fail();
    }
    @Test
    @Ignore("Future enhancement")
    public void withOs_windows10_doesntAllowSafari() {
        fail();
    }
    @Test
    @Ignore("Future enhancement")
    public void withOs_windows8_1_allowsOnlyChromeOrFfOrIe()
    {
        fail();
    }
    @Test
    @Ignore("Future enhancement")
    public void withOs_windows8_allowsOnlyChromeOrFfOrIe()
    {
        fail();
    }

}
