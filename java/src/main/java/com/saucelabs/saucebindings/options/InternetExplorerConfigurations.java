package com.saucelabs.saucebindings.options;

import org.openqa.selenium.ie.InternetExplorerOptions;

public class InternetExplorerConfigurations extends VDCConfigurations<InternetExplorerConfigurations> {
    InternetExplorerConfigurations(InternetExplorerOptions internetExplorerOptions) {
        sauceOptions = new SauceOptions(internetExplorerOptions);
    }

    public InternetExplorerConfigurations setSeleniumVersion(String version) {
        sauceOptions.sauce().setSeleniumVersion(version);
        return this;
    }

    public InternetExplorerConfigurations setIedriverVersion(String version) {
        sauceOptions.sauce().setIedriverVersion(version);
        return this;
    }

    public InternetExplorerConfigurations setAvoidProxy() {
        sauceOptions.sauce().setAvoidProxy(true);
        return this;
    }
}
