package com.saucelabs.saucebindings.configs;

import com.saucelabs.saucebindings.SauceOptions;
import org.openqa.selenium.chrome.ChromeOptions;

public class SauceConfigsChrome extends SauceConfigsBrowser<SauceConfigsChrome> {
    /**
     * @deprecated Not for public use
     *      Use: {@link SauceOptions#chrome()}} or {@link SauceOptions#chrome(ChromeOptions)}}
     */
    @Deprecated
    public SauceConfigsChrome(ChromeOptions chromeOptions) {
        sauceOptions = new SauceOptions(chromeOptions);
    }

    public SauceConfigsChrome setCapturePerformance(Boolean bool) {
        if (bool) {
            sauceOptions.setExtendedDebugging(true);
        }
        sauceOptions.setCapturePerformance(bool);
        return this;
    }

    // TODO - consider ensuring this matches browser Version
    public SauceConfigsChrome setChromedriverVersion(String version) {
        sauceOptions.setChromedriverVersion(version);
        return this;
    }

    public SauceConfigsChrome setExtendedDebugging(Boolean bool) {
        sauceOptions.setExtendedDebugging(bool);
        return this;
    }
}
