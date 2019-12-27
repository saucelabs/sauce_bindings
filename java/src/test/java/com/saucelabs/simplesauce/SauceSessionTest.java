package com.saucelabs.simplesauce;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SauceSessionTest {
    private SauceOptions sauceOptions = spy(new SauceOptions());
    private SauceSession sauceSession = spy(new SauceSession());
    private RemoteWebDriver dummyRemoteDriver = mock(RemoteWebDriver.class);
    private JavascriptExecutor dummyJSExecutor = mock(JavascriptExecutor.class);
    private MutableCapabilities dummyMutableCapabilities = mock(MutableCapabilities.class);

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        doReturn(dummyRemoteDriver).when(sauceSession).createRemoteWebDriver();
    }

    @Test
    public void sauceSessionDefaultsToLatestChromeOnWindows() {
        Options.Browser actualBrowser = sauceSession.getSauceOptions().getBrowserName();
        String actualBrowserVersion = sauceSession.getSauceOptions().getBrowserVersion();
        Options.Platform actualPlatformName = sauceSession.getSauceOptions().getPlatformName();

        assertEquals(Options.Browser.CHROME, actualBrowser);
        assertEquals(Options.Platform.WINDOWS_10, actualPlatformName);
        assertEquals("latest", actualBrowserVersion);
    }

    @Test
    public void sauceSessionUsesProvidedSauceOptions() {
        sauceSession = spy(new SauceSession(sauceOptions));
        doReturn(dummyRemoteDriver).when(sauceSession).createRemoteWebDriver();
        doReturn(dummyMutableCapabilities).when(sauceOptions).toCapabilities();

        sauceSession.start();

        verify(sauceOptions).toCapabilities();
    }

    @Test
    public void defaultsToUSWestDataCenter() {
        String expectedDataCenterEndpoint = DataCenter.US_WEST.getValue();
        assertEquals(expectedDataCenterEndpoint, sauceSession.getDataCenter().getValue());
    }

    @Test
    public void setsDataCenter() {
        String expectedDataCenterEndpoint = DataCenter.US_EAST.getValue();
        sauceSession.setDataCenter(DataCenter.US_EAST);
        assertEquals(expectedDataCenterEndpoint, sauceSession.getDataCenter().getValue());
    }

    @Test
    public void defaultSauceURLUsesENVForUsernameAccessKey() {
        doReturn("test-name").when(sauceSession).getEnvironmentVariable("SAUCE_USERNAME");
        doReturn("accesskey").when(sauceSession).getEnvironmentVariable("SAUCE_ACCESS_KEY");

        String expetedSauceUrl = "https://test-name:accesskey@ondemand.us-west-1.saucelabs.com/wd/hub";
        assertEquals(expetedSauceUrl, sauceSession.getSauceUrl().toString());
    }

    @Test
    public void setUserNameAndAccessKey() {
        sauceSession.setUsername("test-username");
        sauceSession.setAccessKey("test-accesskey");

        String expetedSauceUrl = "https://test-username:test-accesskey@ondemand.us-west-1.saucelabs.com/wd/hub";
        assertEquals(expetedSauceUrl, sauceSession.getSauceUrl().toString());
    }

    @Test
    public void setsSauceURLDirectly() throws MalformedURLException {
        sauceSession.setSauceUrl(new URL("http://example.com"));
        String expetedSauceUrl = "http://example.com";
        assertEquals(expetedSauceUrl, sauceSession.getSauceUrl().toString());
    }

    @Test(expected = SauceEnvironmentVariablesNotSetException.class)
    public void startThrowsErrorWithoutUsername() {
        doReturn(null).when(sauceSession).getEnvironmentVariable("SAUCE_USERNAME");
        sauceSession.start();
    }

    @Test(expected = SauceEnvironmentVariablesNotSetException.class)
    public void startThrowsErrorWithoutAccessKey() {
        doReturn(null).when(sauceSession).getEnvironmentVariable("SAUCE_ACCESS_KEY");
        sauceSession.start();
    }

    @Test
    public void stopCallsDriverQuitPassing() {
        sauceSession.start();
        sauceSession.stop();

        verify(dummyRemoteDriver).quit();
    }

    @Test
    public void stopWithBooleanTrue() {
        doReturn(dummyJSExecutor).when(sauceSession).getJSExecutor();
        sauceSession.start();
        sauceSession.stop(true);
        verify(dummyJSExecutor).executeScript("sauce:job-result=passed");
    }

    @Test
    public void stopWithBooleanFalse() {
        doReturn(dummyJSExecutor).when(sauceSession).getJSExecutor();
        sauceSession.start();
        sauceSession.stop(false);
        verify(dummyJSExecutor).executeScript("sauce:job-result=failed");
    }

    @Test
    public void stopWithStringPassed() {
        doReturn(dummyJSExecutor).when(sauceSession).getJSExecutor();
        sauceSession.start();
        sauceSession.stop("passed");
        verify(dummyJSExecutor).executeScript("sauce:job-result=passed");
    }

    @Test
    public void stopWithStringFailed() {
        doReturn(dummyJSExecutor).when(sauceSession).getJSExecutor();
        sauceSession.start();
        sauceSession.stop("failed");
        verify(dummyJSExecutor).executeScript("sauce:job-result=failed");
    }

    @Test
    public void stopWithNoParameters() {
        doReturn(dummyJSExecutor).when(sauceSession).getJSExecutor();
        sauceSession.start();
        sauceSession.stop();

        verify(dummyJSExecutor, never()).executeScript(anyString());
    }
}
