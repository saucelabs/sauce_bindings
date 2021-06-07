package com.saucelabs.saucebindings.options;

import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxConfigurations extends VDCConfigurations<FirefoxConfigurations> {
    FirefoxConfigurations(FirefoxOptions firefoxOptions) {
        sauceOptions = new SauceOptions(firefoxOptions);
    }

    /**
     * You should almost always use the latest version of Selenium
     * @param version the version of Selenium Server to use for the test
     * @return instance of configuration
     */
    public FirefoxConfigurations setSeleniumVersion(String version) {
        sauceOptions.sauce().setSeleniumVersion(version);
        return this;
    }

    /**
     * @param version the specific version of Driver to use with the job
     * @return instance of configuration
     */
    public FirefoxConfigurations setGeckodriverVersion(String version) {
        sauceOptions.sauce().setGeckodriverVersion(version);
        return this;
    }

    /**
     * Toggles on Extended Debugging for the job
     * @return instance of configuration
     * @see <a href="https://docs.saucelabs.com/insights/debug/index.html">Using Extended Debugging</a>
     */
    public FirefoxConfigurations setExtendedDebugging() {
        sauceOptions.sauce().setExtendedDebugging(true);
        return this;
    }
}
