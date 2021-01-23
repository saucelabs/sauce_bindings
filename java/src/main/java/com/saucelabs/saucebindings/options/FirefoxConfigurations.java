package com.saucelabs.saucebindings.options;

import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxConfigurations extends VDCConfigurations<FirefoxConfigurations> {
    FirefoxConfigurations(FirefoxOptions firefoxOptions) {
        sauceOptions = new SauceOptions(firefoxOptions);
    }

    public FirefoxConfigurations setSeleniumVersion(String version) {
        sauceOptions.sauce().setSeleniumVersion(version);
        return this;
    }

    public FirefoxConfigurations setGeckodriverVersion(String version) {
        sauceOptions.sauce().setGeckodriverVersion(version);
        return this;
    }

    public FirefoxConfigurations setExtendedDebugging() {
        sauceOptions.sauce().setExtendedDebugging(true);
        return this;
    }
}
