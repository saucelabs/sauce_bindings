package com.saucelabs.simplesauce;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class SauceDriverImpl implements SauceRemoteDriver {
    private RemoteWebDriver remoteDriver;

    public RemoteWebDriver createRemoteWebDriver(URL seleniumServer, MutableCapabilities capabilities) {
        remoteDriver = new RemoteWebDriver(seleniumServer, capabilities);
        return remoteDriver;
    }

    @Override
    public void quit() {
        remoteDriver.quit();
    }
}
