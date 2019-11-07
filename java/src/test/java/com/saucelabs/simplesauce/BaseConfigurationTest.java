package com.saucelabs.simplesauce;

import com.saucelabs.simplesauce.interfaces.EnvironmentManager;
import com.saucelabs.simplesauce.interfaces.RemoteDriverInterface;
import org.junit.Before;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BaseConfigurationTest {
    public SauceSession sauce;
    protected RemoteDriverInterface dummyRemoteDriver;
    protected EnvironmentManager dummyEnvironmentManager;
    protected SauceOptions sauceOptions;

    @Before
    public void setUp()
    {
        dummyRemoteDriver = mock(RemoteDriverInterface.class);
        dummyEnvironmentManager = mock(EnvironmentManager.class);
        sauceOptions = new SauceOptions();

        sauce = new SauceSession(dummyRemoteDriver, dummyEnvironmentManager);
        when(dummyEnvironmentManager.getEnvironmentVariable("SAUCE_USERNAME")).thenReturn("test-name");
        when(dummyEnvironmentManager.getEnvironmentVariable("SAUCE_ACCESS_KEY")).thenReturn("accessKey");
    }

    public SauceSession instantiateSauceSession() {
        return new SauceSession(sauceOptions, dummyRemoteDriver, dummyEnvironmentManager);
    }
}
