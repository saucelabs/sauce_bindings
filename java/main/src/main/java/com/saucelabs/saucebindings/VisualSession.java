package com.saucelabs.saucebindings;

import com.saucelabs.saucebindings.options.SauceOptions;
import com.saucelabs.saucebindings.options.VisualOptions;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class VisualSession extends SauceSession {
    private VisualResults visualResults;
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
        validateVisualStatus();
        driver.executeScript("/*@visual.init*/", testName);
    }

    public void takeSnapshot(String name) {
        validateVisualStatus();
        driver.executeScript("/*@visual.snapshot*/", name);
    }

    public VisualResults getVisualResults() {
        if (visualResults == null) {
            this.visualResults = VisualResults.generate(driver);
        }
        return visualResults;
    }

    @Override
    public void stop(Boolean passed) {
        String result = passed ? "passed" : "failed";
        stop(result);
    }

    @Override
    public void stop(String result) {
        if (driver != null) {
            System.out.println(getVisualResults().getSummary());
            super.stop(result);
        }
    }

    public void stop() {
        if (driver != null) {
            VisualResults results = getVisualResults();
            System.out.println(results.getSummary());
            String result = results.getPassed() ? "passed" : "failed";
            super.stop(result);
        }
    }

    private void validateVisualStatus() {
        if (this.visualResults != null) {
            throw new SauceVisualException("Can not execute Visual Commands after calling getVisualResults()");
        }
    }
}
