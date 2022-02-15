package com.saucelabs.saucebindings;

import com.saucelabs.saucebindings.options.SauceOptions;
import com.saucelabs.saucebindings.options.VisualOptions;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class VisualSession extends SauceSession {
    private VisualOptions visualOptions;

    public VisualSession(String testName) {
        this(new VisualOptions(SauceOptions.chrome().setName(testName).build()));
    }

    public VisualSession(VisualOptions options) {
        super(options.getSauceOptions());
        this.visualOptions = options;
    }

    @Override
    public URL getSauceUrl() {
        try {
            return new URL("https://hub.screener.io/wd/hub");
        } catch (MalformedURLException e) {
            throw new InvalidArgumentException("Invalid URL", e);
        }
    }

    public RemoteWebDriver start() {
        MutableCapabilities capabilities = visualOptions.toCapabilities();

        this.driver = createRemoteWebDriver(getSauceUrl(), capabilities);
        driver.executeScript("/*@visual.init*/", getSauceOptions().sauce().getName());

        return driver;
    }

    public void takeSnapshot(String name) {
        driver.executeScript("/*@visual.snapshot*/", name);
    }

    public Map<String, Object> getResults() {
        return (Map<String, Object>) driver.executeScript("/*@visual.end*/");
    }

    public void stop() {
        System.out.println("\n Visual Results: " + getResults());
        super.quit();
    }
}
