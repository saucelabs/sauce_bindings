package com.saucelabs.simplesauce;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class EdgeTest extends BaseConfigurationTest {
    @Test
    public void withEdge_default() {
        sauceOptions.withEdge();
        startSauceSession();

        String actualBrowserSetInConfig = sauce.getCurrentSessionCapabilities().getVersion();
        assertEquals("18.17763", actualBrowserSetInConfig);
    }

    @Test
    public void withEdge18_returnsBrowserVersion18_17763() {
        sauceOptions.withEdge18();

        startSauceSession();
        String actualBrowserSetInConfig = sauce.getCurrentSessionCapabilities().getVersion();
        assertEquals("18.17763", actualBrowserSetInConfig);
    }

    @Test
    public void withEdge17_returnsBrowserVersion17_17134() {
        sauceOptions.withEdge17();
        startSauceSession();

        String actualBrowserSetInConfig = sauce.getCurrentSessionCapabilities().getVersion();
        assertEquals("17.17134", actualBrowserSetInConfig);
    }

    @Test
    public void withEdge16_returnsBrowserVersion16_16299() {
        sauceOptions.withEdge16();
        startSauceSession();

        String actualBrowserSetInConfig = sauce.getCurrentSessionCapabilities().getVersion();
        assertEquals("16.16299", actualBrowserSetInConfig);
    }

    @Test
    public void withEdge15_returnsBrowserVersion15_15063() {
        sauceOptions.withEdge15();
        startSauceSession();

        String actualBrowserSetInConfig = sauce.getCurrentSessionCapabilities().getVersion();
        assertEquals("15.15063", actualBrowserSetInConfig);
    }

    @Test
    public void withEdge14_returnsBrowserVersion14_14393() {
        sauceOptions.withEdge14();
        startSauceSession();

        String actualBrowserSetInConfig = sauce.getCurrentSessionCapabilities().getVersion();
        assertEquals("14.14393", actualBrowserSetInConfig);
    }

    @Test
    public void withEdge13_returnsBrowserVersion13_10586() {
        sauceOptions.withEdge13();
        startSauceSession();
        Mockito.doReturn(dummyRemoteDriver).when(sauce).createRemoteWebDriver();

        String actualBrowserSetInConfig = sauce.getCurrentSessionCapabilities().getVersion();
        assertEquals("13.10586", actualBrowserSetInConfig);
    }
}
