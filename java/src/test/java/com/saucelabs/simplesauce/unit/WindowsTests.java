package com.saucelabs.simplesauce.unit;

import com.saucelabs.simplesauce.SauceSession;
import com.saucelabs.simplesauce.interfaces.EnvironmentManager;
import com.saucelabs.simplesauce.interfaces.RemoteDriverInterface;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WindowsTests {

    //TODO duplication in multiple classes, excluding DataCenterTest
    private SauceSession fakeSauceSession;

    //TODO definite duplciation here and the MacOSTests
    //Potential duplication with SauceSessionTest as well
    @Before
    public void setUp()
    {
        RemoteDriverInterface fakeRemoteDriver = mock(RemoteDriverInterface.class);
        EnvironmentManager fakeEnvironmentManager = mock(EnvironmentManager.class);
        fakeSauceSession = new SauceSession(fakeRemoteDriver, fakeEnvironmentManager);
        when(fakeEnvironmentManager.getEnvironmentVariable("SAUCE_USERNAME")).thenReturn("test-name");
        when(fakeEnvironmentManager.getEnvironmentVariable("SAUCE_ACCESS_KEY")).thenReturn("accessKey");
    }
    @Test
    public void withWindows10_setsWindows10Platform() throws MalformedURLException {
        fakeSauceSession.withWindows10();
        fakeSauceSession.start();
        String actualOsSetInConfig = fakeSauceSession.sauceSessionCapabilities.getPlatform().toString();
        assertEquals("WIN10", actualOsSetInConfig);
    }
    @Test
    public void withWindows8_1_setsPlatformToWindows8_1() throws MalformedURLException {
        fakeSauceSession.withWindows8_1();
        fakeSauceSession.start();
        String actualOsSetInConfig = fakeSauceSession.sauceSessionCapabilities.getPlatform().toString();
        assertEquals("WIN8_1", actualOsSetInConfig);
    }
    @Test
    public void withWindows8_setsPlatformToWindows8() throws MalformedURLException {
        fakeSauceSession.withWindows8();
        fakeSauceSession.start();
        String actualOsSetInConfig = fakeSauceSession.sauceSessionCapabilities.getPlatform().toString();
        assertEquals("WIN8", actualOsSetInConfig);
    }
    @Test
    public void withWindows7_setsPlatformToWindows7() throws MalformedURLException {
        fakeSauceSession.withWindows7();
        fakeSauceSession.start();
        String actualOsSetInConfig = fakeSauceSession.sauceSessionCapabilities.getPlatform().toString();
        assertEquals("VISTA", actualOsSetInConfig);
    }
}
