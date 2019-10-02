package com.saucelabs.simplesauce.unit;

import com.saucelabs.simplesauce.SauceOptions;
import com.saucelabs.simplesauce.SauceSession;
import com.saucelabs.simplesauce.interfaces.EnvironmentManager;
import com.saucelabs.simplesauce.interfaces.RemoteDriverInterface;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SauceOptionsTest {
    private SauceSession sauceSession;
    private EnvironmentManager fakeEnvironmentManager;
    private SauceOptions options;

    @Before
    public void setup()
    {
        options = new SauceOptions();
    }
    @Test
    public void sauceSession_takesSauceOptions() {
        SauceSession session = new SauceSession(options);
        assertNotNull(session);
    }
    @Test
    public void sauceSession_defaultSauceOptions_returnsChromeBrowser() {
        RemoteDriverInterface fakeRemoteDriver = mock(RemoteDriverInterface.class);
        fakeEnvironmentManager = mock(EnvironmentManager.class);
        when(fakeEnvironmentManager.getEnvironmentVariable("SAUCE_USERNAME")).thenReturn("test-name");
        when(fakeEnvironmentManager.getEnvironmentVariable("SAUCE_ACCESS_KEY")).thenReturn("accessKey");

        sauceSession = new SauceSession(options,fakeRemoteDriver, fakeEnvironmentManager);
        sauceSession.start();
        String actualBrowser = sauceSession.sauceSessionCapabilities.getCapability("browserName").toString();
        assertEquals("Chrome", actualBrowser);
    }

    @Test
    public void sauceOptions_defaultBrowser_setToChrome() {
        assertEquals("Chrome", options.browser);
    }
}
