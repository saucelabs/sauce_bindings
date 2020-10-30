package com.saucelabs.saucebindings;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertEquals;

public class SauceOptionsConstrTest {
    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @Test
    public void constructsSauceOptionsVisual() {
        SauceOptions sauceOptions = SauceOptions.visual("projectName");

        assertEquals(Browser.CHROME, sauceOptions.getBrowserName());
        assertEquals(SaucePlatform.WINDOWS_10, sauceOptions.getPlatformName());
        assertEquals("latest", sauceOptions.getBrowserVersion());
        assertEquals("projectName", sauceOptions.getVisualProjectName());
    }
}
