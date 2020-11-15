package com.saucelabs.saucebindings.options.builders;

import com.saucelabs.saucebindings.*;
import com.saucelabs.saucebindings.options.BaseOptions;
import com.saucelabs.saucebindings.options.configs.SauceIEConfigs;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Accessors(chain = true) @Setter @Getter
public class SauceIEBuilder extends BaseOptions {
    public SauceOptions sauceOptions;

    private Browser browserName = Browser.INTERNET_EXPLORER;
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

    public SauceIEBuilder(InternetExplorerOptions internetExplorerOptions) {
        sauceOptions = new SauceOptions(internetExplorerOptions);
    }

    public SauceIEBuilder setImplicitWaitTimeout(Integer integer) {
        timeouts.put(Timeouts.IMPLICIT, integer);
        return this;
    }

    public SauceIEBuilder setPageLoadTimeout(Integer integer) {
        timeouts.put(Timeouts.PAGE_LOAD, integer);
        return this;
    }

    public SauceIEBuilder setScriptTimeout(Integer integer) {
        timeouts.put(Timeouts.SCRIPT, integer);
        return this;
    }

    public SauceIEConfigs sauce() {
        return new SauceIEConfigs(this);
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
