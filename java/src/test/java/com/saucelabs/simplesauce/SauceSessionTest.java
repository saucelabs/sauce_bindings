package com.saucelabs.simplesauce;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({System.class})
public class SauceSessionTest {
    //TODO duplication in 3 classes, excluding DataCenterTest
    private SauceSession sauce;
    private RemoteWebDriver dummyRemoteDriver = mock(RemoteWebDriver.class);
    private SauceOptions options = new SauceOptions();

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        PowerMockito.mockStatic(System.class);
        PowerMockito.when(System.getenv(eq("SAUCE_USERNAME"))).thenReturn("test-name");
        PowerMockito.when(System.getenv(eq("SAUCE_ACCESS_KEY"))).thenReturn("accessKey");
        sauce = new SauceSession();

        sauce = Mockito.spy(new SauceSession(options));
        Mockito.doReturn(dummyRemoteDriver).when(sauce).createRemoteWebDriver();
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
    public void defaultSauceURL() throws MalformedURLException {
        String dataCenterEndpoint = DataCenter.US_WEST.getEndpoint();
        String user = System.getenv("SAUCE_USERNAME");
        String key = System.getenv("SAUCE_ACCESS_KEY");
        URL expetedSauceUrl = new URL("https://" + user + ":" + key + "@" + dataCenterEndpoint + ":443/wd/hub");
        assertEquals(expetedSauceUrl, sauce.getSauceUrl());
    }

    @Test
    public void setsSauceURLDirectly() throws MalformedURLException {
        sauce.setSauceUrl(new URL("http://example.com"));
        URL expetedSauceUrl = new URL("http://example.com");
        assertEquals(expetedSauceUrl, sauce.getSauceUrl());
    }

    @Test
    public void getUserName_usernameSetInEnvironmentVariable_returnsValue() {
        String actualUserName = sauce.getSauceUserName();
        assertNotEquals("",actualUserName);
    }

    @Test
    public void getAccessKey_keySetInEnvironmentVariable_returnsValue() {
        String actualAccessKey = sauce.getSauceAccessKey();
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
    public void sauceOptions_startWithFirefox_startsFirefox() {
        options = new SauceOptions();
        options.withFirefox();

        sauce = Mockito.spy(new SauceSession(options));
        Mockito.doReturn(dummyRemoteDriver).when(sauce).createRemoteWebDriver();
        sauce.start();

        String actualBrowser = sauce.getCurrentSessionCapabilities().getBrowserName();
        assertEquals("firefox", actualBrowser);
    }

    @Test
    public void startThrowsErrorWithoutUsername() {
        sauce.setSauceUserName(null);

        try {
            sauce.start();
            fail("Expected a SauceEnvironmentVariablesNotSetException to be thrown");
        } catch (SauceEnvironmentVariablesNotSetException exception) {
            assertEquals("Sauce Username was not provided", exception.getMessage());
        }
    }

    @Test
    public void startThrowsErrorWithoutAccessKey() {
        sauce.setSauceAccessKey(null);

        try {
            sauce.start();
            fail("Expected a SauceEnvironmentVariablesNotSetException to be thrown");
        } catch (SauceEnvironmentVariablesNotSetException exception) {
            assertEquals("Sauce Access Key was not provided", exception.getMessage());
        }
    }

    @Test
    public void stop_noParams_callsDriverQuit() {
        sauce.start();
        sauce.stop();

        verify(dummyRemoteDriver).quit();
    }
}
