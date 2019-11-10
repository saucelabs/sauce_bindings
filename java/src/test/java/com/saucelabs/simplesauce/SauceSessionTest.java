package com.saucelabs.simplesauce;

import com.saucelabs.simplesauce.interfaces.EnvironmentManager;
import com.saucelabs.simplesauce.interfaces.RemoteDriverInterface;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SauceSessionTest {

    //TODO duplication in 3 classes, excluding DataCenterTest
    private SauceSession sauce;
    private EnvironmentManager dummyEnvironmentManager;
    private RemoteDriverInterface dummyRemoteDriver;
    private SauceOptions options;

    @Before
    public void setUp() {
        dummyRemoteDriver = mock(RemoteDriverInterface.class);
        dummyEnvironmentManager = mock(EnvironmentManager.class);
        sauce = new SauceSession(dummyRemoteDriver, dummyEnvironmentManager);
        when(dummyEnvironmentManager.getEnvironmentVariable("SAUCE_USERNAME")).thenReturn("test-name");
        when(dummyEnvironmentManager.getEnvironmentVariable("SAUCE_ACCESS_KEY")).thenReturn("accessKey");

        sauce.start();
    }
    @Test
    public void sauceSession_defaultSauceOptions_returnsChromeBrowser() {
        options = new SauceOptions();
        dummyRemoteDriver = mock(RemoteDriverInterface.class);

        sauce = new SauceSession(options, dummyRemoteDriver, dummyEnvironmentManager);
        sauce.start();
        String actualBrowser = sauce.currentSessionCapabilities.getCapability("browserName").toString();
        assertEquals("Chrome", actualBrowser);
    }
    @Test
    public void startSession_defaultConfig_usWestDataCenter() {
        String expectedDataCenterUrl = DataCenter.USWest;
        assertEquals(expectedDataCenterUrl, sauce.sauceDataCenter);
    }
    @Test
    public void getUserName_usernameSetInEnvironmentVariable_returnsValue()  {
        when(dummyEnvironmentManager.getEnvironmentVariable("SAUCE_USERNAME")).thenReturn("test-name");
        String actualUserName = sauce.getUserName();
        assertNotEquals("",actualUserName);
    }
    @Test
    public void getAccessKey_keySetInEnvironmentVariable_returnsValue() {
        when(dummyEnvironmentManager.getEnvironmentVariable("SAUCE_ACCESS_KEY")).thenReturn("accessKey");
        String actualAccessKey = sauce.getAccessKey();
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
        String actualBrowser = sauce.currentSessionCapabilities.getCapability(expectedBrowserCapabilityKey).toString();
        assertNotEquals("", actualBrowser);
    }
    @Test
    public void start_setsPlatformNameKey() {
        String correctPlatformKey = "platformName";
        String browserSetInSauceSession = sauce.currentSessionCapabilities.getCapability(correctPlatformKey).toString();
        assertEquals("Windows 10", browserSetInSauceSession);
    }
    @Test
    public void defaultBrowserIsLatest() {
        String correctKey = "browserVersion";
        String browserSetThroughSauceSession = sauce.currentSessionCapabilities.getCapability(correctKey).toString();
        assertEquals("latest", browserSetThroughSauceSession);
    }
    @Test
    public void defaultIsChrome()
    {
        String actualBrowser = sauce.currentSessionCapabilities.getBrowserName();
        assertEquals("Chrome", actualBrowser);
    }
    @Test
    public void defaultIsWindows10() {
        String actualOs = sauce.currentSessionCapabilities.getPlatform().name();
        assertEquals("WIN10", actualOs);
    }
    @Test
    public void sauceOptions_defaultConfiguration_setsSauceOptions() {
        boolean hasAccessKey = sauce.getSauceOptionsCapability().asMap().containsKey("accessKey");
        assertTrue("You need to have Sauce Credentials set (SAUCE_USERNAME, SAUCE_ACCESSKEY) before this unit test will pass", hasAccessKey);
    }
    @Test
    public void sauceOptions_startWithChrome_startsChrome() {
        dummyRemoteDriver = mock(RemoteDriverInterface.class);
        options = new SauceOptions();
        options.withChrome();

        sauce = new SauceSession(options, dummyRemoteDriver, dummyEnvironmentManager);
        sauce.start();

        String actualBrowser = sauce.currentSessionCapabilities.getBrowserName();
        assertEquals("Chrome", actualBrowser);
    }
    //TODO make this test work
    @Test
    @Ignore("Not sure how to make this work with Mockito. To make sure that the .quit() is actually called on the webDriver")
    public void stop_callsDriverQuit() {
        RemoteDriverInterface dummyDriver = mock(RemoteDriverInterface.class);
        sauce = new SauceSession(dummyDriver, dummyEnvironmentManager);
        sauce.start();
        sauce.stop();
        verify(dummyDriver).quit();
    }
}
