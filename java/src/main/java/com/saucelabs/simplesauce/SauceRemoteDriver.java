package com.saucelabs.simplesauce;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public interface SauceRemoteDriver {
    RemoteWebDriver createRemoteWebDriver(URL seleniumServer, MutableCapabilities capabilities);
    void quit();
}
