package com.saucelabs.saucebindings.options;

import org.openqa.selenium.chrome.ChromeOptions;

/** Capabilities that apply only to Chrome sessions. */
public class ChromeConfigurations extends VDCConfigurations<ChromeConfigurations> {
    ChromeConfigurations(ChromeOptions chromeOptions) {
        validatePrefix("goog", chromeOptions.asMap());
        validateBrowserName("chrome", chromeOptions.getBrowserName());
        sauceOptions = new SauceOptions(chromeOptions);
    }

    /**
     * Enables Performance Capture feature.
     *
     * @return instance of configuration
     */
    public ChromeConfigurations setCapturePerformance() {
        sauceOptions.sauce().setExtendedDebugging(true);
        sauceOptions.sauce().setCapturePerformance(true);
        if (sauceOptions.sauce().getName() == null) {
            throw new InvalidSauceOptionsArgumentException("Need to call `setName()` before `setCapturePerformance`");
        }
        return this;
    }

    /**
     * Chrome requires the major browser version to match the major driver version.
     * This setting might be useful if there is a bug in the minor or point version of the default chromedriver.
     * TODO: Consider ensuring this matches the browser's major version number
     *
     * @param version the specific version of driver to use for the test
     * @return instance of configuration
     */
    public ChromeConfigurations setChromedriverVersion(String version) {
        sauceOptions.sauce().setChromedriverVersion(version);
        return this;
    }

    /**
     * Toggles on Extended Debugging for the job.
     *
     * @return instance of configuration
     * @see <a href="https://docs.saucelabs.com/insights/debug/index.html">Using Extended Debugging</a>
     */
    public ChromeConfigurations setExtendedDebugging() {
        sauceOptions.sauce().setExtendedDebugging(true);
        return this;
    }
}
