package com.saucelabs.saucebindings;

import io.appium.java_client.android.AndroidDriver;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.openqa.selenium.MutableCapabilities;

import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class SauceAndroidSessionTest {
    private SauceOptions sauceOptions = spy(SauceOptions.android());
    private SauceAndroidSession sauceSession = spy(new SauceAndroidSession());
    private AndroidDriver dummyAndroidDriver = mock(AndroidDriver.class);

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        doReturn(dummyAndroidDriver).when(sauceSession).createDriver(any(URL.class), any(MutableCapabilities.class));
    }

    @Test
    public void sauceAndroidSessionUsesDefaultAndroidSettings() {
        SauceOptions sauceOptions = sauceSession.getSauceOptions();

        assertEquals(Browser.CHROME, sauceOptions.getBrowserName());
        assertEquals(SaucePlatform.ANDROID, sauceOptions.getPlatformName());
        assertEquals("Android GoogleAPI Emulator", sauceOptions.getDeviceName());
        assertEquals("8.1", sauceOptions.getPlatformVersion());
    }

    @Test
    public void sauceAndroidSessionUsesProvidedSauceOptions() {
        SauceAndroidSession sauceOptsSession = spy(new SauceAndroidSession(sauceOptions));
        MutableCapabilities dummyMutableCapabilities = mock(MutableCapabilities.class);

        doReturn(dummyMutableCapabilities).when(sauceOptions).toCapabilities();
        doReturn(dummyAndroidDriver).when(sauceOptsSession).createDriver(any(URL.class), eq(dummyMutableCapabilities));

        sauceOptsSession.start();

        verify(sauceOptions).toCapabilities();
    }

    @Test
    public void staticAndConstructorCreateSameOptions() {
        System.setProperty("BUILD_NAME", "Static Build Name");
        SauceOptions sauceOptions = SauceOptions.android();

        SauceAndroidSession sauceOptsSession = new SauceAndroidSession(sauceOptions);
        SauceAndroidSession sauceAndroidSession = new SauceAndroidSession();

        assertEquals(sauceAndroidSession.getSauceOptions().toCapabilities(),
                sauceOptsSession.getSauceOptions().toCapabilities());
    }
}