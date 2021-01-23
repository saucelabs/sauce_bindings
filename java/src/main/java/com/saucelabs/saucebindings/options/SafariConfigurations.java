package com.saucelabs.saucebindings.options;

import com.saucelabs.saucebindings.SaucePlatform;
import org.openqa.selenium.safari.SafariOptions;

public class SafariConfigurations extends VDCConfigurations<SafariConfigurations> {
    SafariConfigurations(SafariOptions safariOptions) {
        sauceOptions = new SauceOptions(safariOptions);
        sauceOptions.setPlatformName(SaucePlatform.MAC_CATALINA);
    }

    public SafariConfigurations setAvoidProxy() {
        sauceOptions.sauce().setAvoidProxy(true);
        return this;
    }

    public SafariConfigurations setSeleniumVersion(String version) {
        sauceOptions.sauce().setSeleniumVersion(version);
        return this;
    }
}
