package com.saucelabs.simplesauce;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SauceSessionTest {
    private SauceSession sauce;
    private RemoteWebDriver dummyRemoteDriver = mock(RemoteWebDriver.class);
    private SauceOptions options = new SauceOptions();
    private JavascriptExecutor dummyJSExecutor = mock(JavascriptExecutor.class);

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        sauce = spy(new SauceSession());
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
        DataCenter expectedDataCenter = DataCenter.US_WEST;
        assertEquals(expectedDataCenter.getEndpoint(), sauce.getDataCenter().getEndpoint());
    }

    @Test
    public void allowsChangingDefaultDataCenter() {
        DataCenter expectedDataCenter = DataCenter.US_EAST;
        sauce.setDataCenter(DataCenter.US_EAST);
        assertEquals(expectedDataCenter.getEndpoint(), sauce.getDataCenter().getEndpoint());
    }

    @Test
    public void defaultSauceURLUsesENVForUsernameAccessKey() {
        doReturn("test-name").when(sauce).getEnvironmentVariable("SAUCE_USERNAME");
        doReturn("accesskey").when(sauce).getEnvironmentVariable("SAUCE_ACCESS_KEY");

        String expetedSauceUrl = "https://test-name:accesskey@ondemand.us-west-1.saucelabs.com/wd/hub";
        assertEquals(expetedSauceUrl, sauce.getSauceUrl().toString());
    }

    @Test
    public void setsSauceURLDirectly() throws MalformedURLException {
        sauce.setSauceUrl(new URL("http://example.com"));
        String expetedSauceUrl = "http://example.com";
        assertEquals(expetedSauceUrl, sauce.getSauceUrl().toString());
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

        sauce = spy(new SauceSession(options));
        doReturn(dummyRemoteDriver).when(sauce).createRemoteWebDriver();
        sauce.start();

        String actualBrowser = sauce.getCurrentSessionCapabilities().getBrowserName();
        assertEquals("chrome", actualBrowser);
    }

    @Test(expected = SauceEnvironmentVariablesNotSetException.class)
    public void startThrowsErrorWithoutUsername() {
        doReturn(null).when(sauce).getEnvironmentVariable("SAUCE_USERNAME");
        sauce.start();
    }

    @Test(expected = SauceEnvironmentVariablesNotSetException.class)
    public void startThrowsErrorWithoutAccessKey() {
        doReturn(null).when(sauce).getEnvironmentVariable("SAUCE_ACCESS_KEY");
        sauce.start();
    }

    @Test
    public void stopWithBooleanTrue() {
        doReturn(dummyJSExecutor).when(sauce).getJSExecutor();
        sauce.start();
        sauce.stop(true);
        verify(dummyJSExecutor).executeScript("sauce:job-result=passed");
    }

    @Test
    public void stopWithBooleanFalse() {
        doReturn(dummyJSExecutor).when(sauce).getJSExecutor();
        sauce.start();
        sauce.stop(false);
        verify(dummyJSExecutor).executeScript("sauce:job-result=failed");
    }

    @Test
    public void stopWithStringPassed() {
        doReturn(dummyJSExecutor).when(sauce).getJSExecutor();
        sauce.start();
        sauce.stop("passed");
        verify(dummyJSExecutor).executeScript("sauce:job-result=passed");
    }

    @Test
    public void stopWithStringFailed() {
        doReturn(dummyJSExecutor).when(sauce).getJSExecutor();
        sauce.start();
        sauce.stop("failed");
        verify(dummyJSExecutor).executeScript("sauce:job-result=failed");
    }

    @Test
    public void stopWithNoParameters() {
        doReturn(dummyJSExecutor).when(sauce).getJSExecutor();
        sauce.start();
        sauce.stop();

        verify(dummyJSExecutor, never()).executeScript(anyString());
    }
}
