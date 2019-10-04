package com.saucelabs.simplesauce.unit;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WindowsTests extends BaseConfigurationTest {
    @Test
    public void withWindows10_setsWindows10Platform() {
        mockSauceSession.withWindows10();
        mockSauceSession.start();
        String actualOsSetInConfig = mockSauceSession.sauceSessionCapabilities.getPlatform().toString();
        assertEquals("WIN10", actualOsSetInConfig);
    }
    @Test
    public void withWindows8_1_setsPlatformToWindows8_1() {
        mockSauceSession.withWindows8_1();
        mockSauceSession.start();
        String actualOsSetInConfig = mockSauceSession.sauceSessionCapabilities.getPlatform().toString();
        assertEquals("WIN8_1", actualOsSetInConfig);
    }
    @Test
    public void withWindows8_setsPlatformToWindows8() {
        mockSauceSession.withWindows8();
        mockSauceSession.start();
        String actualOsSetInConfig = mockSauceSession.sauceSessionCapabilities.getPlatform().toString();
        assertEquals("WIN8", actualOsSetInConfig);
    }
    @Test
    public void withWindows7_setsPlatformToWindows7() {
        mockSauceSession.withWindows7();
        mockSauceSession.start();
        String actualOsSetInConfig = mockSauceSession.sauceSessionCapabilities.getPlatform().toString();
        assertEquals("VISTA", actualOsSetInConfig);
    }
}
