package com.saucelabs.saucebindings.options;

import com.saucelabs.saucebindings.SaucePlatform;
import org.openqa.selenium.safari.SafariOptions;

public class SafariConfigurations extends VDCConfigurations<SafariConfigurations> {
    SafariConfigurations(SafariOptions safariOptions) {
        validateBrowserName("safari", safariOptions.getBrowserName());
        sauceOptions = new SauceOptions(safariOptions);
        sauceOptions.setPlatformName(SaucePlatform.MAC_CATALINA);
    }

    /**
     * Sauce routes traffic through a proxy server so that HTTPS connections with self-signed certificates work everywhere.
     * This method allows bypassing the proxy so browsers communicate directly the server.
     * Toggles avoid Proxy to True
     * @return instance of configuration
     */
    public SafariConfigurations setAvoidProxy() {
        sauceOptions.sauce().setAvoidProxy(true);
        return this;
    }

    /**
     * You should almost always use the latest version of Selenium
     * @param version the version of Selenium Server to use for the test
     * @return instance of configuration
     */
    public SafariConfigurations setSeleniumVersion(String version) {
        sauceOptions.sauce().setSeleniumVersion(version);
        return this;
    }
}
