package com.saucelabs.saucebindings.options;

import org.openqa.selenium.edge.EdgeOptions;

public class EdgeConfigurations extends VDCConfigurations<EdgeConfigurations> {
    EdgeConfigurations(EdgeOptions edgeOptions) {
        sauceOptions = new SauceOptions(edgeOptions);
    }

    // TODO: Figure out if extendedDebugging or SeleniumVersion applies

    public EdgeConfigurations setSeleniumVersion(String version) {
        sauceOptions.sauce().setSeleniumVersion(version);
        return this;
    }

    public EdgeConfigurations setCapturePerformance() {
        sauceOptions.sauce().setExtendedDebugging(true);
        sauceOptions.sauce().setCapturePerformance(true);
        if (sauceOptions.sauce().getName() == null) {
            throw new InvalidSauceOptionsArgumentException("Need to call `setName()` before `setCapturePerformance`");
        }
        return this;
    }

    public EdgeConfigurations setExtendedDebugging() {
        sauceOptions.sauce().setExtendedDebugging(true);
        return this;
    }
}
