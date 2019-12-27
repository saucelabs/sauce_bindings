package com.saucelabs.simplesauce;

import org.junit.Rule;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

public class BaseConfigurationTest {
    protected SauceSession sauce;
    protected SauceOptions sauceOptions = new SauceOptions();
    protected RemoteWebDriver dummyRemoteDriver = mock(RemoteWebDriver.class);

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    protected SauceSession instantiateSauceSession() {
        sauce = spy(new SauceSession(sauceOptions));
        doReturn(dummyRemoteDriver).when(sauce).createRemoteWebDriver();
        return sauce;
    }

    protected void startSauceSession() {
        instantiateSauceSession();
        sauce.start();
    }
}
