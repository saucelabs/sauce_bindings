package com.saucelabs.simplesauce.interfaces;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;

public interface SauceRemoteDriver {
    RemoteWebDriver createRemoteWebDriver(String seleniumServer, MutableCapabilities capabilities)
        throws MalformedURLException;
    void quit();
}
