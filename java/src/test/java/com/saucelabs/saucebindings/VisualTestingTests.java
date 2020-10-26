package com.saucelabs.saucebindings;

import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.mockito.Mockito.*;

public class VisualTestingTests {
    private final SauceOptions sauceOptions = spy(new SauceOptions());
    JavascriptExecutor dummyJSDriver = mock(RemoteWebDriver.class);

    @Test
    public void updateResultExecutesScript() {
        VisualTestingStrategy visualTestingStrategy = spy(new VisualTestingStrategy(sauceOptions));
        when(visualTestingStrategy.getJavascriptExecutor()).thenReturn(dummyJSDriver);
        visualTestingStrategy.updateResult(anyString());

        verify(dummyJSDriver).executeScript("/*@visual.end*/");
    }
}
