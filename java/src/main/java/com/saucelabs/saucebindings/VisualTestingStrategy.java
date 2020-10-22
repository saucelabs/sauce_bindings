package com.saucelabs.saucebindings;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class VisualTestingStrategy implements APIStrategy {
    private final SauceOptions sauceOptions;

    public VisualTestingStrategy(SauceOptions options) {
        sauceOptions = options;
    }
    @Getter
    private RemoteWebDriver driver;
    @Setter
    public URL sauceUrl;
    private JavascriptExecutor getJavascriptExecutor(){
        return driver;
    }

    @Override
    public RemoteWebDriver createRemoteWebDriver() {
        driver = new RemoteWebDriver(getSauceUrl(), sauceOptions.toCapabilities());
        getJavascriptExecutor().executeScript(
                "/*@visual.init*/", sauceOptions.getName());
        return driver;
    }

    @Override
    public URL getSauceUrl() {
        try {
            setSauceUrl(new URL("https://hub.screener.io/wd/hub"));
            return sauceUrl;
        } catch (MalformedURLException e) {
            throw new InvalidArgumentException("Invalid URL");
        }
    }
}
