package com.saucelabs.simplesauce;

import com.saucelabs.simplesauce.interfaces.EnvironmentManager;
import com.saucelabs.simplesauce.interfaces.SauceRemoteDriver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SauceSessionTest {

    //TODO duplication in 3 classes, excluding DataCenterTest
    private SauceSession sauce;
    private EnvironmentManager dummyEnvironmentManager;
    private SauceRemoteDriver dummyRemoteDriver;
    private SauceOptions options;

    @Before
    public void setUp() {
        dummyRemoteDriver = mock(SauceRemoteDriver.class);
        dummyEnvironmentManager = mock(EnvironmentManager.class);
        sauce = new SauceSession(dummyRemoteDriver, dummyEnvironmentManager);
        when(dummyEnvironmentManager.getEnvironmentVariable("SAUCE_USERNAME")).thenReturn("test-name");
        when(dummyEnvironmentManager.getEnvironmentVariable("SAUCE_ACCESS_KEY")).thenReturn("accessKey");

        sauce.start();
    }
    @Test
    public void sauceSession_defaultSauceOptions_returnsChromeBrowser() {
        options = new SauceOptions();
        dummyRemoteDriver = mock(SauceRemoteDriver.class);

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
        assertTrue(concreteSauceSession.getDriverManager() instanceof SauceDriverImpl);
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
        options = new SauceOptions();
        options.withChrome();

        sauce = new SauceSession(options, dummyRemoteDriver, dummyEnvironmentManager);
        sauce.start();

        String actualBrowser = sauce.currentSessionCapabilities.getBrowserName();
        assertEquals("Chrome", actualBrowser);
    }
    @Test
    public void stop_callsDriverQuit() {
        WebDriver mockDriver = mock(WebDriver.class);

        sauce.start();
        sauce.stop(mockDriver);

        verify(mockDriver).quit();
    }
    @Test
    public void stop_driverNull_doesntCallDriverQuit() {
        WebDriver mockDriver = mock(WebDriver.class);

        sauce.start();
        sauce.stop(null);

        verify(mockDriver, times(0)).quit();
    }
}
