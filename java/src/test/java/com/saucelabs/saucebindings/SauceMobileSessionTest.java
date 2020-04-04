package com.saucelabs.saucebindings;

import io.appium.java_client.AppiumDriver;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.openqa.selenium.MutableCapabilities;

import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class SauceMobileSessionTest {
    private SauceMobileSession sauceMobileSession = spy(new SauceMobileSession());
    private SauceMobileOptions sauceMobileOptions = spy(new SauceMobileOptions());
    private SauceMobileSession sauceMobileOptsSession = spy(new SauceMobileSession(sauceMobileOptions));
    private AppiumDriver dummyAppiumDriver = mock(AppiumDriver.class);
    private MutableCapabilities dummyMutableCapabilities = mock(MutableCapabilities.class);

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        doReturn(dummyAppiumDriver).when(sauceMobileSession).createAppiumDriver(any(URL.class), any(MutableCapabilities.class));
    }

    @Test
    public void sauceMObileSessionCreatesDefaultOptions() {
        SauceMobileOptions sauceOptions = sauceMobileSession.getSauceOptions();

        assertEquals(Browser.CHROME, sauceOptions.getBrowserName());
        assertEquals(SaucePlatform.ANDROID, sauceOptions.getPlatformName());
        assertEquals("1.15.0", sauceOptions.getAppiumVersion());
        assertEquals("Android GoogleAPI Emulator", sauceOptions.getDeviceName());
        assertEquals("portrait", sauceOptions.getDeviceOrientation());
        assertEquals("8.1", sauceOptions.getPlatformVersion());
    }

    @Test
    public void sauceMobileSessionUsesProvidedSauceOptions() {
        doReturn(dummyMutableCapabilities).when(sauceMobileOptions).toCapabilities();
        doReturn(dummyAppiumDriver).when(sauceMobileOptsSession).createAppiumDriver(any(URL.class), eq(dummyMutableCapabilities));

        sauceMobileOptsSession.start();

        verify(sauceMobileOptions).toCapabilities();
    }
}
