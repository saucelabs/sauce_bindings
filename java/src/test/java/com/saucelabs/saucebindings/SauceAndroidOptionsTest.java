package com.saucelabs.saucebindings;

import org.junit.Assert;
import org.junit.Test;

public class SauceAndroidOptionsTest {

    @Test
    public void defaultConstructor() {
        SauceAndroidOptions options = new SauceAndroidOptions("Android Emulator", "9.0", DeviceOrientation.PORTRAIT);

        Assert.assertEquals(SaucePlatform.ANDROID, options.getPlatformName());
        Assert.assertEquals("9.0", options.getPlatformVersion());
        Assert.assertEquals("Android Emulator", options.getDeviceName());
        Assert.assertEquals(Browser.NONE, options.getBrowserName());
        Assert.assertEquals("", options.getBrowserVersion());
    }
}
