package com.saucelabs.saucebindings.options.builders;

import com.saucelabs.saucebindings.*;
import com.saucelabs.saucebindings.options.BaseOptions;
import com.saucelabs.saucebindings.options.configs.SauceFirefoxConfigs;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Accessors(chain = true) @Setter @Getter
public class SauceFirefoxBuilder extends BaseOptions {
    public SauceOptions sauceOptions;

    private Browser browserName = Browser.FIREFOX;
    private String browserVersion = "latest";
    private SaucePlatform platformName = SaucePlatform.WINDOWS_10;
    private PageLoadStrategy pageLoadStrategy;
    private Boolean acceptInsecureCerts = null;
    private Proxy proxy;
    private Boolean setWindowRect = null;
    private Map<Timeouts, Integer> timeouts = new HashMap<>();
    private Boolean strictFileInteractability = null;
    private UnhandledPromptBehavior unhandledPromptBehavior;

    private final List<String> validOptions = Arrays.asList(
            "browserName",
            "browserVersion",
            "platformName",
            "pageLoadStrategy",
            "acceptInsecureCerts",
            "proxy",
            "setWindowRect",
            "timeouts",
            "strictFileInteractability",
            "unhandledPromptBehavior");

    public SauceFirefoxBuilder(FirefoxOptions firefoxOptions) {
        sauceOptions = new SauceOptions(firefoxOptions);
    }

    public SauceFirefoxBuilder setImplicitWaitTimeout(Integer integer) {
        timeouts.put(Timeouts.IMPLICIT, integer);
        return this;
    }

    public SauceFirefoxBuilder setPageLoadTimeout(Integer integer) {
        timeouts.put(Timeouts.PAGE_LOAD, integer);
        return this;
    }

    public SauceFirefoxBuilder setScriptTimeout(Integer integer) {
        timeouts.put(Timeouts.SCRIPT, integer);
        return this;
    }

    public SauceFirefoxConfigs sauce() {
        return new SauceFirefoxConfigs(this);
    }

    public SauceOptions build() {
        CapabilityManager builderManager = new CapabilityManager(this);
        CapabilityManager sauceOptionsManager = new CapabilityManager(sauceOptions);

        getValidOptions().forEach((capability) -> {
            Object value = builderManager.getCapability(capability);
            if (value != null) {
                sauceOptionsManager.setCapability(capability, value);
            }
        });
        return sauceOptions;
    }
}
