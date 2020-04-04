package com.saucelabs.saucebindings;

import io.appium.java_client.AppiumDriver;
import lombok.Getter;
import org.openqa.selenium.Capabilities;

import java.net.URL;

public class SauceMobileSession extends SauceSession {
    @Getter private final SauceMobileOptions sauceOptions;
    @Getter private AppiumDriver driver;

    public SauceMobileSession() {
        this(new SauceMobileOptions());
    }

    public SauceMobileSession(SauceMobileOptions options) {
        sauceOptions = options;
    }

    public AppiumDriver start() {
        AppiumDriver appiumDriver = createAppiumDriver(getSauceUrl(), sauceOptions.toCapabilities());
        this.driver = appiumDriver;
        return appiumDriver;
    }

    public AppiumDriver createAppiumDriver(URL url, Capabilities caps) {
        return new AppiumDriver<>(url, caps);
    }
}
