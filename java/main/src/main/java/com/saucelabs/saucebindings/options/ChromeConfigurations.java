package com.saucelabs.saucebindings.options;

import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeConfigurations extends VDCConfigurations<ChromeConfigurations> {
    ChromeConfigurations(ChromeOptions chromeOptions) {
        sauceOptions = new SauceOptions(chromeOptions);
    }

    /**
     * Enables Performance Capture feature
     *
     * @return instance of configuration
     */
    public ChromeConfigurations setCapturePerformance() {
        sauceOptions.sauce().setExtendedDebugging(true);
        sauceOptions.sauce().setCapturePerformance(true);
        return this;
    }

    /**
     * Chrome requires the major browser version to match the major driver version
     * This setting might be useful if there is a bug in the minor or point version of the default chromedriver
     * @param version the specific version of driver to use for the test
     * @return instance of configuration
     */
    // Use case is a different point release than the driver provided by default
    // TODO - consider ensuring this matches browser major version number
    public ChromeConfigurations setChromedriverVersion(String version) {
        sauceOptions.sauce().setChromedriverVersion(version);
        return this;
    }

    /**
     * Toggles on Extended Debugging for the job
     * @return instance of configuration
     * @see <a href="https://docs.saucelabs.com/insights/debug/index.html">Using Extended Debugging</a>
     */
    public ChromeConfigurations setExtendedDebugging() {
        sauceOptions.sauce().setExtendedDebugging(true);
        return this;
    }

    @Override
    public SauceOptions build() {
        if (sauceOptions.sauce().getCapturePerformance() && sauceOptions.sauce().getName() == null) {
            throw new InvalidSauceOptionsArgumentException("Unable to setCapturePerformance() without also doing setName()");
        }
        return super.build();
    }
}
