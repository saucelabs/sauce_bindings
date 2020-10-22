package com.saucelabs.saucebindings;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserTestingStrategy implements APIStrategy {
    private final SauceOptions sauceOptions;
    @Getter
    @Setter private DataCenter dataCenter = DataCenter.US_WEST;
    private URL sauceUrl;

    public BrowserTestingStrategy(SauceOptions sauceOptions) {
        this.sauceOptions = sauceOptions;
    }
    @Override
    public URL getSauceUrl() {
        if (sauceUrl != null) {
            return sauceUrl;
        } else {
            try {
                return new URL(dataCenter.getValue());
            } catch (MalformedURLException e) {
                throw new InvalidArgumentException("Invalid URL");
            }
        }
    }

    @Override
    public String setSauceUrl(URL url) {
        sauceUrl = url;
    }

    @Override
    public RemoteWebDriver createRemoteWebDriver() {
        return new RemoteWebDriver(getSauceUrl(), sauceOptions.toCapabilities());
    }
}
