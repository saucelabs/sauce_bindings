package com.saucelabs.simplesauce;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SauceSessionTest {
    private SauceSession sauce;
    private EnvironmentManager dummyEnvironmentManager;
    private RemoteWebDriver dummyRemoteDriver = mock(RemoteWebDriver.class);
    private SauceOptions options = new SauceOptions();

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        //TODO duplication in setup in BaseConfigurationTest. Can be moved out of here
        //and combined into a single setup()
        dummyEnvironmentManager = mock(EnvironmentManager.class);
        when(dummyEnvironmentManager.getEnvironmentVariable("SAUCE_USERNAME")).thenReturn("test-name");
        when(dummyEnvironmentManager.getEnvironmentVariable("SAUCE_ACCESS_KEY")).thenReturn("accessKey");

        sauce = spy(new SauceSession(options, dummyEnvironmentManager));
        doReturn(dummyRemoteDriver).when(sauce).createRemoteWebDriver();
    }

    @Test
    public void sauceSession_defaultSauceOptions_returnsChromeBrowser() {
        sauce.start();
        String actualBrowser = sauce.getCurrentSessionCapabilities().getCapability("browserName").toString();
        assertEquals("chrome", actualBrowser);
    }

    @Test
    public void startSession_defaultConfig_usWestDataCenter() {
        String expectedDataCenterEndpoint = DataCenter.US_WEST.getEndpoint();
        assertEquals(expectedDataCenterEndpoint, sauce.getSauceDataCenter());
    }

    @Test
    public void defaultSauceURL() {
        String dataCenterEndpoint = DataCenter.US_WEST.getEndpoint();
        String expetedSauceUrl = "https://test-name:accessKey@" + dataCenterEndpoint + "/wd/hub";
        assertEquals(expetedSauceUrl, sauce.getSauceUrl().toString());
    }

    @Test
    public void setsSauceURLDirectly() throws MalformedURLException {
        sauce.setSauceUrl(new URL("http://example.com"));
        String expetedSauceUrl = "http://example.com";
        assertEquals(expetedSauceUrl, sauce.getSauceUrl().toString());
    }

    @Test
    public void getUserName_usernameSetInEnvironmentVariable_returnsValue() {
        when(dummyEnvironmentManager.getEnvironmentVariable("SAUCE_USERNAME")).thenReturn("test-name");
        String actualUserName = sauce.getUserName();
        assertNotEquals("", actualUserName);
    }

    @Test
    public void getAccessKey_keySetInEnvironmentVariable_returnsValue() {
        when(dummyEnvironmentManager.getEnvironmentVariable("SAUCE_ACCESS_KEY")).thenReturn("accessKey");
        String actualAccessKey = sauce.getAccessKey();
        assertNotEquals("", actualAccessKey);
    }

    @Test
    public void startSession_setsBrowserKey() {
        sauce.start();

        String expectedBrowserCapabilityKey = "browserName";
        String actualBrowser = sauce.getCurrentSessionCapabilities().getCapability(expectedBrowserCapabilityKey).toString();
        assertNotEquals("", actualBrowser);
    }

    @Test
    public void start_setsPlatformNameKey() {
        sauce.start();

        String correctPlatformKey = "platformName";
        String browserSetInSauceSession = sauce.getCurrentSessionCapabilities().getCapability(correctPlatformKey).toString();
        assertEquals("Windows 10", browserSetInSauceSession);
    }

    @Test
    public void defaultBrowserIsLatest() {
        sauce.start();

        String correctKey = "browserVersion";
        String browserSetThroughSauceSession = sauce.getCurrentSessionCapabilities().getCapability(correctKey).toString();
        assertEquals("latest", browserSetThroughSauceSession);
    }

    @Test
    public void defaultIsChrome() {
        sauce.start();

        String actualBrowser = sauce.getCurrentSessionCapabilities().getBrowserName();
        assertEquals("chrome", actualBrowser);
    }

    @Test
    public void defaultIsWindows10() {
        sauce.start();

        String actualOs = sauce.getCurrentSessionCapabilities().getPlatform().name();
        assertEquals("WIN10", actualOs);
    }

    @Test
    public void sauceOptions_startWithChrome_startsChrome() {
        options.withChrome();

        sauce = spy(new SauceSession(options, dummyEnvironmentManager));
        doReturn(dummyRemoteDriver).when(sauce).createRemoteWebDriver();
        sauce.start();

        String actualBrowser = sauce.getCurrentSessionCapabilities().getBrowserName();
        assertEquals("chrome", actualBrowser);
    }

    @Test
    public void stop_noParams_callsDriverQuit() {
        sauce.start();
        sauce.stop();

        verify(dummyRemoteDriver).quit();
    }
}
