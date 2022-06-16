package com.saucelabs.saucebindings;

import com.saucelabs.saucebindings.options.SauceOptions;
import com.saucelabs.saucebindings.options.VisualOptions;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class VisualSession extends SauceSession {
    private final VisualOptions visualOptions;

    public VisualSession(String testName) {
        this(new VisualOptions(SauceOptions.chrome().setName(testName).build()));
    }

    public VisualSession(VisualOptions options) {
        super(options.getSauceOptions());
        this.visualOptions = options;
    }

    @Override
    public RemoteWebDriver start() {
        this.driver = createRemoteWebDriver(getSauceUrl(), visualOptions.toCapabilities());
        newVisualTest(getSauceOptions().sauce().getName());
        return driver;
    }

    @Override
    public URL getSauceUrl() {
        try {
            return new URL("https://hub.screener.io/wd/hub");
        } catch (MalformedURLException e) {
            throw new InvalidArgumentException("Invalid URL", e);
        }
    }

    public void newVisualTest(String testName) {
        driver.executeScript("/*@visual.init*/", testName);
    }

    public void takeSnapshot(String name) {
        driver.executeScript("/*@visual.snapshot*/", name);
    }

    @Override
    public void stop(Boolean passed) {
        super.stop(passed);
    }

    /**
     * @deprecated Do not use magic strings, pass in boolean for whether test has
     *             passed.
     */
    @Override
    @Deprecated()
    public void stop(String result) {
        if (driver != null) {
            super.stop(result);
        }
    }

    public void stop() {
        if (driver != null) {
            Map<String, Object> results = (Map<String, Object>) driver.executeScript("/*@visual.end*/");
            Boolean result = (Boolean) results.get("passed") ? true : false;
            super.stop(result);
        }
    }
}
