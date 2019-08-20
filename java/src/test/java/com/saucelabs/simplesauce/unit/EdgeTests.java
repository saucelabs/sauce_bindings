package com.saucelabs.simplesauce.unit;

import com.saucelabs.simplesauce.SauceSession;
import com.saucelabs.simplesauce.interfaces.EnvironmentManager;
import com.saucelabs.simplesauce.interfaces.RemoteDriverInterface;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EdgeTests {

    //TODO duplication in multiple classes, excluding DataCenterTest
    private SauceSession fakeSauceSession;

    //TODO definite duplciation here and the MacOSTests
    //Potential duplication with SauceSessionTest as well
    @Before
    public void setUp()
    {
        RemoteDriverInterface fakeRemoteDriver = mock(RemoteDriverInterface.class);
        EnvironmentManager fakeEnvironmentManager = mock(EnvironmentManager.class);
        fakeSauceSession = new SauceSession(fakeRemoteDriver, fakeEnvironmentManager);
        when(fakeEnvironmentManager.getEnvironmentVariable("SAUCE_USERNAME")).thenReturn("test-name");
        when(fakeEnvironmentManager.getEnvironmentVariable("SAUCE_ACCESS_KEY")).thenReturn("accessKey");
    }
    @Test
    public void withEdge18_17763_returnsBrowserVersion18_17763() throws MalformedURLException {
        fakeSauceSession.withEdge18_17763();
        fakeSauceSession.start();
        String actualBrowserSetInConfig = fakeSauceSession.sauceSessionCapabilities.getVersion();
        assertEquals("18.17763", actualBrowserSetInConfig);
    }
    @Test
    public void withEdge17_17134_returnsBrowserVersion17_17134() throws MalformedURLException {
        fakeSauceSession.withEdge17_17134();
        fakeSauceSession.start();
        String actualBrowserSetInConfig = fakeSauceSession.sauceSessionCapabilities.getVersion();
        assertEquals("17.17134", actualBrowserSetInConfig);
    }
    @Test
    public void withEdge16_16299_returnsBrowserVersion16_16299() throws MalformedURLException {
        fakeSauceSession.withEdge16_16299();
        fakeSauceSession.start();
        String actualBrowserSetInConfig = fakeSauceSession.sauceSessionCapabilities.getVersion();
        assertEquals("16.16299", actualBrowserSetInConfig);
    }
    @Test
    public void withEdge15_15063_returnsBrowserVersion15_15063() throws MalformedURLException {
        fakeSauceSession.withEdge15_15063();
        fakeSauceSession.start();
        String actualBrowserSetInConfig = fakeSauceSession.sauceSessionCapabilities.getVersion();
        assertEquals("15.15063", actualBrowserSetInConfig);
    }
    @Test
    public void withEdge14_14393_returnsBrowserVersion14_14393() throws MalformedURLException {
        fakeSauceSession.withEdge14_14393();
        fakeSauceSession.start();
        String actualBrowserSetInConfig = fakeSauceSession.sauceSessionCapabilities.getVersion();
        assertEquals("14.14393", actualBrowserSetInConfig);
    }
    @Test
    public void withEdge13_10586_returnsBrowserVersion13_10586() throws MalformedURLException {
        fakeSauceSession.withEdge13_10586();
        fakeSauceSession.start();
        String actualBrowserSetInConfig = fakeSauceSession.sauceSessionCapabilities.getVersion();
        assertEquals("13.10586", actualBrowserSetInConfig);
    }
}
