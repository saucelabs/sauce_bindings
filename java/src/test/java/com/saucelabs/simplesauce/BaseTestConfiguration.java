package com.saucelabs.simplesauce;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.openqa.selenium.remote.RemoteWebDriver;
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
    protected RemoteWebDriver dummyRemoteDriver;

    @Before
    public void setUp() {
        dummyRemoteDriver = mock(RemoteWebDriver.class);
        sauceOptions = new SauceOptions();

        PowerMockito.mockStatic(System.class);
        PowerMockito.when(System.getenv(eq("SAUCE_USERNAME"))).thenReturn("test-name");
        PowerMockito.when(System.getenv(eq("SAUCE_ACCESS_KEY"))).thenReturn("accessKey");
        PowerMockito.when(System.getenv(any())).thenCallRealMethod();
    }

    public void startSauceSession() {
        sauce = Mockito.spy(new SauceSession(sauceOptions));
        Mockito.doReturn(dummyRemoteDriver).when(sauce).createRemoteWebDriver();

        sauce.start();
    }
}
