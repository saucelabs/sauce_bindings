package com.saucelabs.saucebindings.options;

import org.openqa.selenium.edge.EdgeOptions;

public class EdgeConfigurations extends VDCConfigurations<EdgeConfigurations> {
    EdgeConfigurations(EdgeOptions edgeOptions) {
        sauceOptions = new SauceOptions(edgeOptions);
    }

    public EdgeConfigurations setSeleniumVersion(String version) {
        sauceOptions.sauce().setSeleniumVersion(version);
        return this;
    }
}
