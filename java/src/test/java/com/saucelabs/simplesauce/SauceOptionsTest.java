package com.saucelabs.simplesauce;

import com.saucelabs.simplesauce.interfaces.EnvironmentManager;
import com.saucelabs.simplesauce.interfaces.SauceRemoteDriver;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SauceOptionsTest {
    private SauceSession sauceSession;
    private SauceOptions options;

    @Before
    public void setUp()
    {
        options = new SauceOptions();
    }
    @Test
    public void sauceSession_takesSauceOptions() {
        sauceSession = new SauceSession(options);
        assertNotNull(sauceSession);
    }
    @Test
    public void sauceSession_defaultSauceOptions_returnsChromeBrowser() {
        SauceRemoteDriver fakeRemoteDriver = mock(SauceRemoteDriver.class);
        EnvironmentManager fakeEnvironmentManager = mock(EnvironmentManager.class);
        when(fakeEnvironmentManager.getEnvironmentVariable("SAUCE_USERNAME")).thenReturn("test-name");
        when(fakeEnvironmentManager.getEnvironmentVariable("SAUCE_ACCESS_KEY")).thenReturn("accessKey");

        sauceSession = new SauceSession(options,fakeRemoteDriver, fakeEnvironmentManager);
        sauceSession.start();
        String actualBrowser = sauceSession.currentSessionCapabilities.getCapability("browserName").toString();
        assertEquals("Chrome", actualBrowser);
    }

    @Test
    public void sauceOptions_defaultBrowser_setToChrome() {
        assertEquals("Chrome", options.browser);
    }
    @Test
    public void sauceOptions_defaultBrowserVersion_setToLatest() {
        assertEquals("latest", options.browserVersion);
    }
    @Test
    public void sauceOptions_defaultOS_setToWindows() {
        assertEquals("Windows 10", options.operatingSystem);
    }
    @Test
    public void withChrome_browser_setToChrome() {
        options.withChrome();
        assertEquals("Chrome", options.browser);
        assertNotNull(options.chromeOptions);
    }
}
