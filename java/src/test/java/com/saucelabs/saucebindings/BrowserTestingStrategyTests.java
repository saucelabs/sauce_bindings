package com.saucelabs.saucebindings;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.mockito.Mockito.*;

public class BrowserTestingStrategyTests {
    private final SauceOptions sauceOptions = spy(new SauceOptions());
    JavascriptExecutor dummyJSDriver = mock(RemoteWebDriver.class);
    WebDriver dummyDriver = mock(RemoteWebDriver.class);

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @Test
    public void createRemoteWebDriverSetsDriver() {
        BrowserTestingStrategy browserTest = spy(new BrowserTestingStrategy(sauceOptions));

        doReturn(dummyDriver).when(browserTest).createRemoteWebDriver();
        browserTest.createRemoteWebDriver();

        Assert.assertNotNull(browserTest.getDriver());
    }
}
