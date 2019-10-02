package com.saucelabs.simplesauce.unit;

import com.saucelabs.simplesauce.SauceOptions;
import com.saucelabs.simplesauce.SauceSession;
import com.saucelabs.simplesauce.interfaces.EnvironmentManager;
import com.saucelabs.simplesauce.interfaces.RemoteDriverInterface;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SauceOptionsTest {

    //TODO duplication in 3 classes, excluding DataCenterTest
    private SauceSession sauceSession;
    private EnvironmentManager fakeEnvironmentManager;

    @Test
    public void sauceSession_takesSauceOptions() {
        SauceOptions options = new SauceOptions();
        SauceSession session = new SauceSession(options);
        assertNotNull(session);
    }
    @Test
    public void sauceSession_defaultSauceOptions_returnsChromeBrowser() {
        SauceOptions options = new SauceOptions();
        RemoteDriverInterface fakeRemoteDriver = mock(RemoteDriverInterface.class);
        fakeEnvironmentManager = mock(EnvironmentManager.class);
        when(fakeEnvironmentManager.getEnvironmentVariable("SAUCE_USERNAME")).thenReturn("test-name");
        when(fakeEnvironmentManager.getEnvironmentVariable("SAUCE_ACCESS_KEY")).thenReturn("accessKey");

        sauceSession = new SauceSession(options,fakeRemoteDriver, fakeEnvironmentManager);
        sauceSession.start();
        String actualBrowser = sauceSession.sauceSessionCapabilities.getCapability("browserName").toString();
        assertEquals("Chrome", actualBrowser);
    }
}
