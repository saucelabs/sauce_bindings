package com.saucelabs.saucebindings;

import io.appium.java_client.AppiumDriver;
import lombok.Getter;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.MutableCapabilities;

import java.net.MalformedURLException;
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
        MutableCapabilities capabilities = sauceOptions.toCapabilities(getDataCenter());

        URL url;
        if (dataCenter.supportsW3C() || dataCenter.isTestObject()) {
            url = getSauceUrl();
        } else {
            String username = (String) capabilities.getCapability("username");
            String key = (String) capabilities.getCapability("accessKey");
            url = getSauceUrl(username, key);
        }

        driver = createAppiumDriver(url, capabilities);
        return driver;
    }

    public AppiumDriver createAppiumDriver(URL url, Capabilities caps) {
        return new AppiumDriver<>(url, caps);
    }

    public URL getSauceUrl(String username, String key) {
        String url = dataCenter.getValue().replace("://", "://" + username + ":" + key + "@");
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new InvalidArgumentException("Invalid URL");
        }
    }
}
