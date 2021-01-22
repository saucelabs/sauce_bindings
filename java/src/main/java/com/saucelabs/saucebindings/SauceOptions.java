package com.saucelabs.saucebindings;

import com.saucelabs.saucebindings.options.CapabilityManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariOptions;

public class SauceOptions extends com.saucelabs.saucebindings.options.SauceOptions {

    /**
     * @deprecated Use com.saucelabs.saucebindings.options.SauceOptions() instead
     */
    @Deprecated
    public SauceOptions() {
        this(new MutableCapabilities());
    }

    /**
     * @deprecated Use com.saucelabs.saucebindings.options.SauceOptions(ChromeOptions options) instead
     */
    @Deprecated
    public SauceOptions(ChromeOptions options) {
        this(new MutableCapabilities(options));
    }

    /**
     * @deprecated Use com.saucelabs.saucebindings.options.SauceOptions(EdgeOptions options) instead
     */
    @Deprecated
    public SauceOptions(EdgeOptions options) {
        this(new MutableCapabilities(options));
    }

    /**
     * @deprecated Use com.saucelabs.saucebindings.options.SauceOptions(FirefoxOptions options) instead
     */
    @Deprecated
    public SauceOptions(FirefoxOptions options) {
        this(new MutableCapabilities(options));
    }

    /**
     * @deprecated Use com.saucelabs.saucebindings.options.SauceOptions(InternetExplorerOptions options) instead
     */
    @Deprecated
    public SauceOptions(InternetExplorerOptions options) {
        this(new MutableCapabilities(options));
    }

    /**
     * @deprecated Use com.saucelabs.saucebindings.options.SauceOptions(SafariOptions options) instead
     */
    @Deprecated
    public SauceOptions(SafariOptions options) {
        this(new MutableCapabilities(options));
    }

    private SauceOptions(MutableCapabilities options) {
        capabilities = new MutableCapabilities(options.asMap());
        capabilityManager = new CapabilityManager(this);
        if (options.getCapability("browserName") != null) {
            setCapability("browserName", options.getCapability("browserName"));
        }
    }

    /**
     * @deprecated Use getCapabilities() instead
     * @return instance capabilities that will get sent to the RemoteWebDriver
     */
    @Deprecated
    public MutableCapabilities getSeleniumCapabilities() {
        return capabilities;
    }
}
