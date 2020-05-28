package com.saucelabs.saucebindings;

import io.appium.java_client.ios.IOSDriver;
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

public class SauceIOSSessionTest {
    private SauceOptions sauceOptions = spy(SauceOptions.ios());
    private SauceIOSSession sauceSession = spy(new SauceIOSSession());
    private IOSDriver dummyIOSDriver = mock(IOSDriver.class);

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        doReturn(dummyIOSDriver).when(sauceSession).createDriver(any(URL.class), any(MutableCapabilities.class));
    }

    @Test
    public void SauceIOSSessionUsesDefaultAndroidSettings() {
        SauceOptions sauceOptions = sauceSession.getSauceOptions();

        assertEquals(Browser.SAFARI, sauceOptions.getBrowserName());
        assertEquals(SaucePlatform.IOS, sauceOptions.getPlatformName());
        assertEquals("iPhone Simulator", sauceOptions.getDeviceName());
        assertEquals("13.2", sauceOptions.getPlatformVersion());
    }

    @Test
    public void SauceIOSSessionUsesProvidedSauceOptions() {
        SauceIOSSession sauceOptsSession = spy(new SauceIOSSession(sauceOptions));
        MutableCapabilities dummyMutableCapabilities = mock(MutableCapabilities.class);

        doReturn(dummyMutableCapabilities).when(sauceOptions).toCapabilities();
        doReturn(dummyIOSDriver).when(sauceOptsSession).createDriver(any(URL.class), eq(dummyMutableCapabilities));

        sauceOptsSession.start();

        verify(sauceOptions).toCapabilities();
    }

    @Test
    public void staticAndConstructorCreateSameOptions() {
        System.setProperty("BUILD_NAME", "Static Build Name");
        SauceOptions sauceOptions = SauceOptions.ios();

        SauceIOSSession sauceOptsSession = new SauceIOSSession(sauceOptions);
        SauceIOSSession SauceIOSSession = new SauceIOSSession();

        assertEquals(SauceIOSSession.getSauceOptions().toCapabilities(),
                sauceOptsSession.getSauceOptions().toCapabilities());
    }
}
