package com.saucelabs.simplesauce;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

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
        doReturn(dummyRemoteDriver).when(sauceSession).createRemoteWebDriver(any(URL.class), any(MutableCapabilities.class));
    }

    @Test
    public void sauceSessionDefaultsToLatestChromeOnWindows() {
        Browser actualBrowser = sauceSession.getSauceOptions().getBrowserName();
        String actualBrowserVersion = sauceSession.getSauceOptions().getBrowserVersion();
        SaucePlatform actualPlatformName = sauceSession.getSauceOptions().getPlatformName();

        assertEquals(Browser.CHROME, actualBrowser);
        assertEquals(SaucePlatform.WINDOWS_10, actualPlatformName);
        assertEquals("latest", actualBrowserVersion);
    }

    @Test
    public void sauceSessionUsesProvidedSauceOptions() {
        sauceSession = spy(new SauceSession(sauceOptions));
        doReturn(dummyMutableCapabilities).when(sauceOptions).toCapabilities();
        doReturn(dummyRemoteDriver).when(sauceSession).createRemoteWebDriver(any(URL.class), eq(dummyMutableCapabilities));

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
        Whitebox.setInternalState(sauceSession, "username", "test-name");
        Whitebox.setInternalState(sauceSession, "accessKey", "accesskey");

        String expetedSauceUrl = "https://test-name:accesskey@ondemand.us-west-1.saucelabs.com/wd/hub";
        assertEquals(expetedSauceUrl, sauceSession.getSauceUrl().toString());
    }

    @Test
    public void sauceURLUsersSystemPropertiesForUsernameAccessKey() {
        SauceSession sauceSession = new SauceSession();
        Whitebox.setInternalState(sauceSession, "username", null);
        Whitebox.setInternalState(sauceSession, "accessKey", null);

        System.setProperty("SAUCE_USERNAME", "test-name");
        System.setProperty("SAUCE_ACCESS_KEY", "accesskey");

        String expetedSauceUrl = "https://test-name:accesskey@ondemand.us-west-1.saucelabs.com/wd/hub";
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
        SauceSession sauceSession = new SauceSession();
        System.clearProperty("SAUCE_USERNAME");
        System.clearProperty("SAUCE_ACCESS_KEY");

        Whitebox.setInternalState(sauceSession, "username", null);
        sauceSession.start();
    }

    @Test(expected = SauceEnvironmentVariablesNotSetException.class)
    public void startThrowsErrorWithoutAccessKey() {
        SauceSession sauceSession = new SauceSession();
        System.clearProperty("SAUCE_USERNAME");
        System.clearProperty("SAUCE_ACCESS_KEY");

        Whitebox.setInternalState(sauceSession, "accessKey", null);
        sauceSession.start();
    }

    @Test
    public void stopCallsDriverQuitPassing() {
        sauceSession.start();
        sauceSession.stop(true);

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
}
