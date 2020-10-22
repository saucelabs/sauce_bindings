package com.saucelabs.saucebindings;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public interface APIStrategy {
    RemoteWebDriver createRemoteWebDriver();
    URL getSauceUrl();

    String setSauceUrl(URL url);
}
