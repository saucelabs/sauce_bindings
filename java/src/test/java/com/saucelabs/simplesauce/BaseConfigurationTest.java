package com.saucelabs.simplesauce;

import org.junit.Before;
import org.junit.Rule;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseConfigurationTest {
    protected SauceSession sauce;
    protected SauceOptions sauceOptions;
    protected RemoteWebDriver dummyRemoteDriver = mock(RemoteWebDriver.class);
    protected EnvironmentManager dummyEnvironmentManager;

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        dummyRemoteDriver = mock(RemoteWebDriver.class);
        dummyEnvironmentManager = mock(EnvironmentManager.class);
        sauceOptions = new SauceOptions();

        when(dummyEnvironmentManager.getEnvironmentVariable("SAUCE_USERNAME")).thenReturn("test-name");
        when(dummyEnvironmentManager.getEnvironmentVariable("SAUCE_ACCESS_KEY")).thenReturn("accessKey");
    }

    protected void startSauceSession() {
        sauce = Mockito.spy(new SauceSession(sauceOptions, dummyEnvironmentManager));
        Mockito.doReturn(dummyRemoteDriver).when(sauce).createRemoteWebDriver();
        sauce.start();
    }
}
