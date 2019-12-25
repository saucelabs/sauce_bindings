package com.saucelabs.simplesauce;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest({System.class})
class BaseTestConfiguration {
    protected SauceSession sauce;
    protected SauceOptions sauceOptions;
    private SauceRemoteDriver dummyRemoteDriver;

    @Before
    public void setUp() {
        dummyRemoteDriver = mock(SauceRemoteDriver.class);
        sauceOptions = new SauceOptions();

        sauce = new SauceSession(dummyRemoteDriver);
        PowerMockito.mockStatic(System.class);
        PowerMockito.when(System.getenv(eq("SAUCE_USERNAME"))).thenReturn("test-name");
        PowerMockito.when(System.getenv(eq("SAUCE_ACCESS_KEY"))).thenReturn("accessKey");
        PowerMockito.when(System.getenv(any())).thenCallRealMethod();

        sauceOptions.getBrowserName();
    }

    public SauceSession instantiateSauceSession() {
        return new SauceSession(sauceOptions, dummyRemoteDriver);
    }
}
