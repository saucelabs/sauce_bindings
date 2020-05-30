package com.saucelabs.saucebindings;

import io.appium.java_client.ios.IOSDriver;
import lombok.Getter;
import org.openqa.selenium.MutableCapabilities;

import java.net.URL;

public class SauceIOSSession extends SauceSession<IOSDriver>  {
    @Getter private IOSDriver driver;

    public SauceIOSSession() {
        this(SauceOptions.ios());
    }

    public SauceIOSSession(SauceOptions options) {
        setSauceOptions(options);
    }

    protected IOSDriver createDriver(URL url, MutableCapabilities capabilities) {
        this.driver = new IOSDriver(url, capabilities);
        return driver;
    }
}
