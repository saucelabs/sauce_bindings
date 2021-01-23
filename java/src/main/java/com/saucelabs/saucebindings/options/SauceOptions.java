package com.saucelabs.saucebindings.options;

import com.saucelabs.saucebindings.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariOptions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Accessors(chain = true)
@Setter @Getter
public class SauceOptions extends BaseOptions {
    @Setter(AccessLevel.NONE) @Getter(AccessLevel.NONE) private SauceLabsOptions sauceLabsOptions = null;
    public TimeoutStore timeout = new TimeoutStore();

    // w3c Settings
    protected Browser browserName = Browser.CHROME;
    protected String browserVersion = "latest";
    protected SaucePlatform platformName = SaucePlatform.WINDOWS_10;
    protected PageLoadStrategy pageLoadStrategy;
    protected Boolean acceptInsecureCerts = null;
    protected Proxy proxy;
    protected Boolean setWindowRect = null;
    @Getter(AccessLevel.NONE) protected Map<Timeouts, Integer> timeouts;
    protected Boolean strictFileInteractability = null;
    protected UnhandledPromptBehavior unhandledPromptBehavior;

    public final List<String> validOptions = Arrays.asList(
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

    public static ChromeConfigurations chrome() {
        return chrome(new ChromeOptions());
    }

    public static ChromeConfigurations chrome(ChromeOptions chromeOptions) {
        return new ChromeConfigurations(chromeOptions);
    }

    public static EdgeConfigurations edge() {
        return edge(new EdgeOptions());
    }

    public static EdgeConfigurations edge(EdgeOptions edgeOptions) {
        return new EdgeConfigurations(edgeOptions);
    }

    public static FirefoxConfigurations firefox() {
        return firefox(new FirefoxOptions());
    }

    public static FirefoxConfigurations firefox(FirefoxOptions firefoxOptions) {
        return new FirefoxConfigurations(firefoxOptions);
    }

    public static InternetExplorerConfigurations ie() {
        return ie(new InternetExplorerOptions());
    }

    public static InternetExplorerConfigurations ie(InternetExplorerOptions internetExplorerOptions) {
        return new InternetExplorerConfigurations(internetExplorerOptions);
    }

    public static SafariConfigurations safari() {
        return safari(new SafariOptions());
    }

    public static SafariConfigurations safari(SafariOptions safariOptions) {
        return new SafariConfigurations(safariOptions);
    }

    public SauceLabsOptions sauce() {
        return sauceLabsOptions;
    }

    public SauceOptions() {
        this(new MutableCapabilities());
    }

    public Map<Timeouts, Integer> getTimeouts() {
        if (timeout.getTimeouts().isEmpty()) {
            return timeouts;
        }
        return timeout.getTimeouts();
    }

    SauceOptions(MutableCapabilities options) {
        capabilities = new MutableCapabilities(options.asMap());
        capabilityManager = new CapabilityManager(this);
        sauceLabsOptions = new SauceLabsOptions();
        if (options.getCapability("browserName") != null) {
            setCapability("browserName", options.getCapability("browserName"));
        }
    }

    public MutableCapabilities toCapabilities() {
        capabilityManager.addCapabilities();
        capabilities.setCapability("sauce:options", sauce().toCapabilities());
        return capabilities;
    }

    /**
     * This method is to handle special cases and enums as necessary
     *
     * @param key   Which capability to set on this instance's Selenium MutableCapabilities instance
     * @param value The value of the capability getting set
     */
    @Override
    public void setCapability(String key, Object value) {
        switch (key) {
            case "browserName":
                capabilityManager.validateCapability("Browser", Browser.keys(), (String) value);
                setBrowserName(Browser.valueOf(Browser.fromString((String) value)));
                break;
            case "platformName":
                capabilityManager.validateCapability("SaucePlatform", SaucePlatform.keys(), (String) value);
                setPlatformName(SaucePlatform.valueOf(SaucePlatform.fromString((String) value)));
                break;
            case "pageLoadStrategy":
                capabilityManager.validateCapability("PageLoadStrategy", PageLoadStrategy.keys(), (String) value);
                setPageLoadStrategy(PageLoadStrategy.valueOf(PageLoadStrategy.fromString((String) value)));
                break;
            case "unhandledPromptBehavior":
                capabilityManager.validateCapability("UnhandledPromptBehavior", UnhandledPromptBehavior.keys(), (String) value);
                setUnhandledPromptBehavior(UnhandledPromptBehavior.valueOf(UnhandledPromptBehavior.fromString((String) value)));
                break;
            case "timeouts":
                Map<Timeouts, Integer> timeoutsMap = new HashMap<>();
                ((Map) value).forEach((oldKey, val) -> {
                    capabilityManager.validateCapability("Timeouts", Timeouts.keys(), (String) oldKey);
                    String keyString = Timeouts.fromString((String) oldKey);
                    timeoutsMap.put(Timeouts.valueOf(keyString), (Integer) val);
                });
                setTimeouts(timeoutsMap);
                break;
            case "sauce":
                sauce().mergeCapabilities((HashMap<String, Object>) value);
                break;
            default:
                if (sauce().getValidOptions().contains(key)) {
                    deprecatedSetCapability(key, value);
                } else {
                    super.setCapability(key, value);
                }
        }
    }

    private void deprecatedSetCapability(String key, Object value) {
        System.out.println("WARNING: using merge() of Map with value of (" + key + ") is DEPRECATED");
        System.out.println("place this value inside a nested Map with the keyword 'sauce'");
        sauce().setCapability(key, value);
    }
}
