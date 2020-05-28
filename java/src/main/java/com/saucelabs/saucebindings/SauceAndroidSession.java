package com.saucelabs.saucebindings;

import io.appium.java_client.android.AndroidDriver;
import lombok.Getter;
import org.openqa.selenium.MutableCapabilities;

import java.net.URL;

public class SauceAndroidSession extends SauceSession<AndroidDriver>  {
    @Getter private AndroidDriver driver;

    public SauceAndroidSession() {
        this(SauceOptions.android());
    }

    public SauceAndroidSession(SauceOptions options) {
        setSauceOptions(options);
    }

    protected AndroidDriver createDriver(URL url, MutableCapabilities capabilities) {
        this.driver = new AndroidDriver(url, capabilities);
        return driver;
    }
}
