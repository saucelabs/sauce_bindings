package com.saucelabs.simplesauce;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EdgeTest extends BaseConfigurationTest {
    @Test
    public void withEdge_default() {
        sauceOptions.withEdge();

        String actualBrowserSetInConfig = sauceOptions.getBrowserVersion();
        assertEquals("18.17763", actualBrowserSetInConfig);
    }

    @Test
    public void withEdge18_returnsBrowserVersion18_17763() {
        sauceOptions.withEdge18();

        String actualBrowserSetInConfig = sauceOptions.getBrowserVersion();
        assertEquals("18.17763", actualBrowserSetInConfig);
    }

    @Test
    public void withEdge17_returnsBrowserVersion17_17134() {
        sauceOptions.withEdge17();

        String actualBrowserSetInConfig = sauceOptions.getBrowserVersion();
        assertEquals("17.17134", actualBrowserSetInConfig);
    }

    @Test
    public void withEdge16_returnsBrowserVersion16_16299() {
        sauceOptions.withEdge16();

        String actualBrowserSetInConfig = sauceOptions.getBrowserVersion();
        assertEquals("16.16299", actualBrowserSetInConfig);
    }

    @Test
    public void withEdge15_returnsBrowserVersion15_15063() {
        sauceOptions.withEdge15();

        String actualBrowserSetInConfig = sauceOptions.getBrowserVersion();
        assertEquals("15.15063", actualBrowserSetInConfig);
    }

    @Test
    public void withEdge14_returnsBrowserVersion14_14393() {
        sauceOptions.withEdge14();

        String actualBrowserSetInConfig = sauceOptions.getBrowserVersion();
        assertEquals("14.14393", actualBrowserSetInConfig);
    }

    @Test
    public void withEdge13_returnsBrowserVersion13_10586() {
        sauceOptions.withEdge13();

        String actualBrowserSetInConfig = sauceOptions.getBrowserVersion();
        assertEquals("13.10586", actualBrowserSetInConfig);
    }
}
