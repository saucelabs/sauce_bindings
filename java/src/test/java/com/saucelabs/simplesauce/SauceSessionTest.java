package com.saucelabs.simplesauce;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.openqa.selenium.WebDriver;
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
    private SauceRemoteDriver dummyRemoteDriver;
    private SauceOptions options;

    @Before
    public void setUp() {
        dummyRemoteDriver = mock(SauceRemoteDriver.class);
        PowerMockito.mockStatic(System.class);
        PowerMockito.when(System.getenv(eq("SAUCE_USERNAME"))).thenReturn("test-name");
        PowerMockito.when(System.getenv(eq("SAUCE_ACCESS_KEY"))).thenReturn("accessKey");
        sauce = new SauceSession(dummyRemoteDriver);

        sauce.start();
    }

    @Test
    public void sauceSession_defaultSauceOptions_returnsChromeBrowser() {
        options = new SauceOptions();
        dummyRemoteDriver = mock(SauceRemoteDriver.class);

        sauce = new SauceSession(options, dummyRemoteDriver);
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
    public void defaultConstructor_instantiated_setsConcreteDriverManager() {
        SauceSession concreteSauceSession = new SauceSession();
        assertTrue(concreteSauceSession.getSauceDriver() instanceof SauceDriverImpl);
    }

    @Test
    public void startSession_setsBrowserKey() {
        String expectedBrowserCapabilityKey = "browserName";
        String actualBrowser = sauce.getCurrentSessionCapabilities().getCapability(expectedBrowserCapabilityKey).toString();
        assertNotEquals("", actualBrowser);
    }

    @Test
    public void start_setsPlatformNameKey() {
        String correctPlatformKey = "platformName";
        String browserSetInSauceSession = sauce.getCurrentSessionCapabilities().getCapability(correctPlatformKey).toString();
        assertEquals("Windows 10", browserSetInSauceSession);
    }

    @Test
    public void defaultBrowserIsLatest() {
        String correctKey = "browserVersion";
        String browserSetThroughSauceSession = sauce.getCurrentSessionCapabilities().getCapability(correctKey).toString();
        assertEquals("latest", browserSetThroughSauceSession);
    }

    @Test
    public void defaultIsChrome() {
        String actualBrowser = sauce.getCurrentSessionCapabilities().getBrowserName();
        assertEquals("chrome", actualBrowser);
    }

    @Test
    public void defaultIsWindows10() {
        String actualOs = sauce.getCurrentSessionCapabilities().getPlatform().name();
        assertEquals("WIN10", actualOs);
    }

    @Test
    public void sauceOptions_startWithChrome_startsChrome() {
        dummyRemoteDriver = mock(SauceRemoteDriver.class);
        options = new SauceOptions();
        options.withChrome();

        sauce = new SauceSession(options, dummyRemoteDriver);
        sauce.start();

        String actualBrowser = sauce.getCurrentSessionCapabilities().getBrowserName();
        assertEquals("chrome", actualBrowser);
    }

    @Test(expected = NullPointerException.class)
    public void stop_newWebDriverInstanceSetByStart_stopsSession() {
        sauce = new SauceSession(dummyRemoteDriver);

        WebDriver driver = sauce.start();
        sauce.stop();

        driver.quit();
    }

    @Test
    public void startThrowsErrorWithoutUsername() {
        sauce = new SauceSession(dummyRemoteDriver);
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
        sauce = new SauceSession(dummyRemoteDriver);
        sauce.setSauceAccessKey(null);

        try {
            sauce.start();
            fail("Expected a SauceEnvironmentVariablesNotSetException to be thrown");
        } catch (SauceEnvironmentVariablesNotSetException exception) {
            assertEquals("Sauce Access Key was not provided", exception.getMessage());
        }
    }

    @Test
    @Ignore("Not sure how to make this work with Mockito. To make sure that the .quit() is actually called on the webDriver")
    public void stop_noParams_callsDriverQuit() {
        WebDriver mockDriver = mock(WebDriver.class);

        sauce.start();
        sauce.stop();

        verify(mockDriver).quit();
    }
}
