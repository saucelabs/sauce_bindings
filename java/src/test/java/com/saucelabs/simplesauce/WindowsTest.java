package com.saucelabs.simplesauce;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WindowsTest extends BaseTestConfiguration {
    @Test
    public void withWindows10_setsWindows10Platform() {
        sauceOptions.withWindows10();

        String actualOsSetInConfig = sauceOptions.getPlatformName();
        assertEquals("Windows 10", actualOsSetInConfig);
    }
    @Test
    public void withWindows8_1_setsPlatformToWindows8_1() {
        sauceOptions.withWindows8_1();

        String actualOsSetInConfig = sauceOptions.getPlatformName();
        assertEquals("Windows 8.1", actualOsSetInConfig);
    }
    @Test
    public void withWindows8_setsPlatformToWindows8() {
        sauceOptions.withWindows8();

        String actualOsSetInConfig = sauceOptions.getPlatformName();
        assertEquals("Windows 8", actualOsSetInConfig);
    }
    @Test
    public void withWindows7_setsPlatformToWindows7() {
        sauceOptions.withWindows7();

        String actualOsSetInConfig = sauceOptions.getPlatformName();
        assertEquals("Windows 7", actualOsSetInConfig);
    }
}
