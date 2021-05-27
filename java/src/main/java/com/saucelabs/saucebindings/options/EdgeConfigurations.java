package com.saucelabs.saucebindings.options;

import org.openqa.selenium.edge.EdgeOptions;

public class EdgeConfigurations extends VDCConfigurations<EdgeConfigurations> {
    EdgeConfigurations(EdgeOptions edgeOptions) {
        sauceOptions = new SauceOptions(edgeOptions);
    }

    /**
     * You should almost always use the latest version of Selenium
     * @param version the version of Selenium Server to use for the test
     * @return instance of configuration
     */
    public EdgeConfigurations setSeleniumVersion(String version) {
        sauceOptions.sauce().setSeleniumVersion(version);
        return this;
    }
}
