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

    private SauceSession concreteSauceSession;
    private SauceSession fakeSauceSession;
    private EnvironmentManager fakeEnvironmentManager;

    @Before
    public void setUp()
    {
        concreteSauceSession = new SauceSession();
        RemoteDriverInterface fakeRemoteDriver = mock(RemoteDriverInterface.class);
        fakeEnvironmentManager = mock(EnvironmentManager.class);
        fakeSauceSession = new SauceSession(fakeRemoteDriver, fakeEnvironmentManager);
        when(fakeEnvironmentManager.getEnvironmentVariable("SAUCE_USERNAME")).thenReturn("test-name");
        when(fakeEnvironmentManager.getEnvironmentVariable("SAUCE_ACCESS_KEY")).thenReturn("accessKey");
    }

    @Test
    public void withMacOsMojave_returnsMacPlatformStringFromSelenium()
    {
        fakeSauceSession.withMacOsMojave();
        String actualOsThatWasSet = fakeSauceSession.setSauceOptions().getPlatform().toString();
        assertEquals("macOS 10.14", actualOsThatWasSet);
    }
}
