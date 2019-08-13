package com.saucelabs.simplesauce.unit;

import com.saucelabs.simplesauce.SauceSession;
import com.saucelabs.simplesauce.interfaces.EnvironmentManager;
import com.saucelabs.simplesauce.interfaces.RemoteDriverInterface;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MacOsTests {

    private SauceSession fakeSauceSession;
    private EnvironmentManager fakeEnvironmentManager;

    @Before
    public void setUp()
    {
        RemoteDriverInterface fakeRemoteDriver = mock(RemoteDriverInterface.class);
        fakeEnvironmentManager = mock(EnvironmentManager.class);
        fakeSauceSession = new SauceSession(fakeRemoteDriver, fakeEnvironmentManager);
        when(fakeEnvironmentManager.getEnvironmentVariable("SAUCE_USERNAME")).thenReturn("test-name");
        when(fakeEnvironmentManager.getEnvironmentVariable("SAUCE_ACCESS_KEY")).thenReturn("accessKey");
    }

    @Test
    public void withMacOsMojave_returnsMacOs1014()
    {
        fakeSauceSession.withMacOsMojave();
        String actualOsThatWasSet = fakeSauceSession.setSauceOptions().getPlatform().toString();
        assertEquals("macOS 10.14", actualOsThatWasSet);
    }
    @Test
    public void withMacOsHighSierra_returnsMacOs1013()
    {
        fakeSauceSession.withMacOsHighSierra();
        String actualOsThatWasSet = fakeSauceSession.setSauceOptions().getPlatform().toString();
        assertEquals("macOS 10.13", actualOsThatWasSet);
    }
    @Test
    public void withMacOsSierra_returnsMacOs1012()
    {
        fakeSauceSession.withMacOsSierra();
        String actualOsThatWasSet = fakeSauceSession.setSauceOptions().getPlatform().toString();
        assertEquals("macOS 10.12", actualOsThatWasSet);
    }
    @Test
    public void withMacOsElCapitan_returnsMacOs1011()
    {
        fakeSauceSession.withMacOsXElCapitan();
        String actualOsThatWasSet = fakeSauceSession.setSauceOptions().getPlatform().toString();
        assertEquals("OS X 10.11", actualOsThatWasSet);
    }
    @Test
    public void withMacOsYosemite_returnsMacOsX1010()
    {
        fakeSauceSession.withMacOsXYosemite();
        String actualOsThatWasSet = fakeSauceSession.setSauceOptions().getPlatform().toString();
        assertEquals("OS X 10.10", actualOsThatWasSet);
    }
}
