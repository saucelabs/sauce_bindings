package com.saucelabs.simplesauce.unit;

import org.junit.Test;

import java.net.MalformedURLException;

import static org.junit.Assert.assertEquals;

public class WindowsTests extends BaseConfigurationTest {
    @Test
    public void withWindows10_setsWindows10Platform() {
        fakeSauceSession.withWindows10();
        fakeSauceSession.start();
        String actualOsSetInConfig = fakeSauceSession.sauceSessionCapabilities.getPlatform().toString();
        assertEquals("WIN10", actualOsSetInConfig);
    }
    @Test
    public void withWindows8_1_setsPlatformToWindows8_1() {
        fakeSauceSession.withWindows8_1();
        fakeSauceSession.start();
        String actualOsSetInConfig = fakeSauceSession.sauceSessionCapabilities.getPlatform().toString();
        assertEquals("WIN8_1", actualOsSetInConfig);
    }
    @Test
    public void withWindows8_setsPlatformToWindows8() {
        fakeSauceSession.withWindows8();
        fakeSauceSession.start();
        String actualOsSetInConfig = fakeSauceSession.sauceSessionCapabilities.getPlatform().toString();
        assertEquals("WIN8", actualOsSetInConfig);
    }
    @Test
    public void withWindows7_setsPlatformToWindows7() {
        fakeSauceSession.withWindows7();
        fakeSauceSession.start();
        String actualOsSetInConfig = fakeSauceSession.sauceSessionCapabilities.getPlatform().toString();
        assertEquals("VISTA", actualOsSetInConfig);
    }
}
