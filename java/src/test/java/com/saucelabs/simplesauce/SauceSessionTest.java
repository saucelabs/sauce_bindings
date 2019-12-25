package com.saucelabs.simplesauce;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import org.openqa.selenium.remote.BrowserType;
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
//        PowerMockito.when(System.getenv(eq("SAUCE_USERNAME"))).thenReturn("test-name");
//        PowerMockito.when(System.getenv(eq("SAUCE_ACCESS_KEY"))).thenReturn("accessKey");
        sauce = new SauceSession();
//
//        sauce = Mockito.spy(new SauceSession(options));
//        Mockito.doReturn(dummyRemoteDriver).when(sauce).createRemoteWebDriver();
    }

    @Test
    public void sauceSession_defaultSauceOptions_returnsChromeBrowser() {
        String actualBrowser = sauce.getSauceOptions().getBrowserName();
        assertEquals(BrowserType.CHROME, actualBrowser);
    }

    @Test
    public void startSession_defaultConfig_usWestDataCenter() {
        DataCenter expectedDataCenterEndpoint = DataCenter.US_WEST;
        assertEquals(expectedDataCenterEndpoint, sauce.getDataCenter());
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
        PowerMockito.when(System.getenv(Mockito.eq("SAUCE_USERNAME"))).thenReturn("test-name");

        String actualUserName = sauce.getSauceUsername();
        assertNotEquals("test-name", actualUserName);
    }

    @Test
    public void getAccessKey_keySetInEnvironmentVariable_returnsValue() {
        PowerMockito.when(System.getenv(Mockito.eq("SAUCE_ACCESS_KEY"))).thenReturn("accessKey");

        String actualAccessKey = sauce.getSauceAccessKey();
        assertNotEquals("accessKey", actualAccessKey);
    }

    @Test
    public void defaultIsChrome() {
        String actualBrowser = sauce.getSauceOptions().getBrowserName();
        assertEquals("chrome", actualBrowser);
    }

    @Test
    public void defaultIsWindows10() {
        String actualOs = sauce.getSauceOptions().getPlatformName();
        assertEquals("Windows 10", actualOs);
    }

    @Test
    public void sauceOptions_startWithFirefox_startsFirefox() {
        options = new SauceOptions();
        options.withFirefox();

        sauce = Mockito.spy(new SauceSession(options));
        Mockito.doReturn(dummyRemoteDriver).when(sauce).createRemoteWebDriver();
        sauce.start();

        String actualBrowser = sauce.getSauceOptions().getBrowserName();
        assertEquals("firefox", actualBrowser);
    }

    @Test
    public void startThrowsErrorWithoutUsername() {
        sauce.setSauceUsername(null);

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
        sauce = Mockito.spy(new SauceSession(options));
        Mockito.doReturn(dummyRemoteDriver).when(sauce).createRemoteWebDriver();

        sauce.start();
        sauce.stop();

        verify(dummyRemoteDriver).quit();
    }
}
