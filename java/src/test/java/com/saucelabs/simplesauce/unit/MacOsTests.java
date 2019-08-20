package com.saucelabs.simplesauce.unit;

import com.saucelabs.simplesauce.Platforms;
import com.saucelabs.simplesauce.SauceSession;
import com.saucelabs.simplesauce.interfaces.EnvironmentManager;
import com.saucelabs.simplesauce.interfaces.RemoteDriverInterface;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MacOsTests {

    //TODO duplication in 3 classes, excluding DataCenterTest
    private SauceSession fakeSauceSession;

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
    public void withMacOsMojave_returnsMacOs1014() throws MalformedURLException {
        fakeSauceSession.withMacOsMojave();
        fakeSauceSession.start();
        String actualOsThatWasSet = fakeSauceSession.sauceSessionCapabilities.getPlatform().toString();
        assertEquals("macOS 10.14", actualOsThatWasSet);
    }
    @Test
    public void withMacOsHighSierra_returnsMacOs1013() throws MalformedURLException {
        fakeSauceSession.withMacOsHighSierra();
        fakeSauceSession.start();
        String actualOsThatWasSet = fakeSauceSession.sauceSessionCapabilities.getPlatform().toString();
        assertEquals("macOS 10.13", actualOsThatWasSet);
    }
    @Test
    public void withMacOsSierra_returnsMacOs1012() throws MalformedURLException {
        fakeSauceSession.withMacOsSierra();
        fakeSauceSession.start();
        String actualOsThatWasSet = fakeSauceSession.sauceSessionCapabilities.getPlatform().toString();
        assertEquals("macOS 10.12", actualOsThatWasSet);
    }
    @Test
    public void withMacOsElCapitan_returnsMacOs1011() throws MalformedURLException {
        fakeSauceSession.withMacOsXElCapitan();
        fakeSauceSession.start();
        String actualOsThatWasSet = fakeSauceSession.sauceSessionCapabilities.getPlatform().toString();
        assertEquals("OS X 10.11", actualOsThatWasSet);
    }
    @Test
    public void withMacOsYosemite_returnsMacOsX1010() throws MalformedURLException {
        fakeSauceSession.withMacOsXYosemite();
        fakeSauceSession.start();
        String actualOsThatWasSet = fakeSauceSession.sauceSessionCapabilities.getPlatform().toString();
        assertEquals("OS X 10.10", actualOsThatWasSet);
    }
    @Test
    public void defaultSafari_browserVersionIs12_0() throws MalformedURLException {
        fakeSauceSession.withSafari();
        fakeSauceSession.start();

        String safariVersionSetThroughSauceSession = fakeSauceSession.sauceSessionCapabilities.getVersion();
        assertEquals("12.0", safariVersionSetThroughSauceSession);
    }
    @Test
    public void defaultSafari_macOsVersionIsMojave() throws MalformedURLException {
        fakeSauceSession.withSafari();
        fakeSauceSession.start();

        String safariVersionSetThroughSauceSession = fakeSauceSession.sauceSessionCapabilities.getPlatform().toString();
        assertThat(Platforms.MAC_OS_MOJAVE, equalToIgnoringCase(safariVersionSetThroughSauceSession));
    }
}
