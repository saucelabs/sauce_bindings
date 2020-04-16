package com.saucelabs.saucebindings;

import org.junit.Assert;
import org.junit.Test;

public class SauceIOSOptionsTest {

    @Test
    public void defaultConstructor() {
        SauceIOSOptions options = new SauceIOSOptions("iPhone X Simulator", "13.0", DeviceOrientation.PORTRAIT);

        Assert.assertEquals(SaucePlatform.IOS, options.getPlatformName());
        Assert.assertEquals("13.0", options.getPlatformVersion());
        Assert.assertEquals("iPhone X Simulator", options.getDeviceName());
        Assert.assertEquals(Browser.NONE, options.getBrowserName());
        Assert.assertEquals("", options.getBrowserVersion());

    }
}
