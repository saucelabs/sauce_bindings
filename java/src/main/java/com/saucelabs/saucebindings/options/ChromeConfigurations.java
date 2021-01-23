package com.saucelabs.saucebindings.options;

import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeConfigurations extends VDCConfigurations<ChromeConfigurations> {
    ChromeConfigurations(ChromeOptions chromeOptions) {
        sauceOptions = new SauceOptions(chromeOptions);
    }

    public ChromeConfigurations setCapturePerformance() {
        sauceOptions.sauce().setExtendedDebugging(true);
        sauceOptions.sauce().setCapturePerformance(true);
        if (sauceOptions.sauce().getName() == null) {
            throw new InvalidSauceOptionsArgumentException("Need to call `setName()` before `setCapturePerformance`");
        }
        return this;
    }

    // Use case is a different point release than the driver provided by default
    // TODO - consider ensuring this matches browser Version
    public ChromeConfigurations setChromedriverVersion(String version) {
        sauceOptions.sauce().setChromedriverVersion(version);
        return this;
    }

    public ChromeConfigurations setExtendedDebugging() {
        sauceOptions.sauce().setExtendedDebugging(true);
        return this;
    }
}
