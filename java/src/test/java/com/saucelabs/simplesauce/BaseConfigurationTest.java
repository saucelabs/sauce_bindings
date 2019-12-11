package com.saucelabs.simplesauce;

import org.junit.Before;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BaseConfigurationTest {
    protected SauceSession sauce;
    protected SauceOptions sauceOptions;
    private SauceRemoteDriver dummyRemoteDriver;
    private EnvironmentManager dummyEnvironmentManager;

    @Before
    public void setUp() {
        dummyRemoteDriver = mock(SauceRemoteDriver.class);
        dummyEnvironmentManager = mock(EnvironmentManager.class);
        sauceOptions = new SauceOptions();

        sauce = new SauceSession(dummyRemoteDriver, dummyEnvironmentManager);
        when(dummyEnvironmentManager.getEnvironmentVariable("SAUCE_USERNAME")).thenReturn("test-name");
        when(dummyEnvironmentManager.getEnvironmentVariable("SAUCE_ACCESS_KEY")).thenReturn("accessKey");
    }

    SauceSession instantiateSauceSession() {
        return new SauceSession(sauceOptions, dummyRemoteDriver, dummyEnvironmentManager);
    }
}
