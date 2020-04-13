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
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class SauceMobileSessionTest {
    private SauceMobileSession sauceMobileSession = spy(new SauceMobileSession());
    private SauceMobileOptions sauceMobileOptions = spy(new SauceMobileOptions());
    private SauceMobileSession sauceMobileOptsSession = spy(new SauceMobileSession(sauceMobileOptions));
    private AppiumDriver dummyAppiumDriver = mock(AppiumDriver.class);
    private MutableCapabilities dummyMutableCapabilities = spy(new MutableCapabilities());

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        doReturn(dummyAppiumDriver).when(sauceMobileSession).createAppiumDriver(any(URL.class), any(MutableCapabilities.class));
        doReturn(dummyAppiumDriver).when(sauceMobileOptsSession).createAppiumDriver(any(URL.class), any(MutableCapabilities.class));
    }

    @Test
    public void createsDefaultOptions() {
        SauceMobileOptions sauceOptions = sauceMobileSession.getSauceOptions();

        assertEquals(Browser.CHROME, sauceOptions.getBrowserName());
        assertEquals(SaucePlatform.ANDROID, sauceOptions.getPlatformName());
        assertEquals("1.15.0", sauceOptions.getAppiumVersion());
        assertEquals("Android GoogleAPI Emulator", sauceOptions.getDeviceName());
        assertEquals("portrait", sauceOptions.getDeviceOrientation());
        assertEquals("8.1", sauceOptions.getPlatformVersion());
    }

    @Test
    public void usesProvidedSauceOptions() {
        doReturn(dummyMutableCapabilities).when(sauceMobileOptions).toCapabilities(sauceMobileOptsSession.getDataCenter());

        sauceMobileOptsSession.start();

        verify(sauceMobileOptions).toCapabilities(sauceMobileOptsSession.getDataCenter());
        verify(sauceMobileOptsSession).getSauceUrl();
        verify(sauceMobileOptsSession).createAppiumDriver(any(URL.class), eq(dummyMutableCapabilities));
    }

    @Test
    public void supportsJWPEndpoints() {
        sauceMobileOptsSession.setDataCenter(DataCenter.US_WEST);
        dummyMutableCapabilities.setCapability("username", "username");
        dummyMutableCapabilities.setCapability("accessKey", "accessKey");
        doReturn(dummyMutableCapabilities).when(sauceMobileOptions).toCapabilities(sauceMobileOptsSession.getDataCenter());

        sauceMobileOptsSession.start();

        verify(sauceMobileOptions).toCapabilities(sauceMobileOptsSession.getDataCenter());
        verify(sauceMobileOptsSession).getSauceUrl(eq("username"), eq("accessKey"));
    }
}
