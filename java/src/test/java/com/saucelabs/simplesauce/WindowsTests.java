package com.saucelabs.simplesauce;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WindowsTests extends BaseConfigurationTest {
    @Test
    public void withWindows10_setsWindows10Platform() {
        sauceOptions.withWindows10();
        sauce = instantiateSauceSession();

        sauce.start();
        String actualOsSetInConfig = sauce.currentSessionCapabilities.getPlatform().toString();
        assertEquals("WIN10", actualOsSetInConfig);
    }
    @Test
    public void withWindows8_1_setsPlatformToWindows8_1() {
        sauceOptions.withWindows8_1();
        sauce = instantiateSauceSession();

        sauce.start();
        String actualOsSetInConfig = sauce.currentSessionCapabilities.getPlatform().toString();
        assertEquals("WIN8_1", actualOsSetInConfig);
    }
    @Test
    public void withWindows8_setsPlatformToWindows8() {
        sauceOptions.withWindows8();
        sauce = instantiateSauceSession();

        sauce.start();
        String actualOsSetInConfig = sauce.currentSessionCapabilities.getPlatform().toString();
        assertEquals("WIN8", actualOsSetInConfig);
    }
    @Test
    public void withWindows7_setsPlatformToWindows7() {
        sauceOptions.withWindows7();
        sauce = instantiateSauceSession();

        sauce.start();
        String actualOsSetInConfig = sauce.currentSessionCapabilities.getPlatform().toString();
        assertEquals("VISTA", actualOsSetInConfig);
    }
}
