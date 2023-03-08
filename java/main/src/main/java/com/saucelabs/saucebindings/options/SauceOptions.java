package com.saucelabs.saucebindings.options;

import com.saucelabs.saucebindings.Browser;
import com.saucelabs.saucebindings.PageLoadStrategy;
import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.TimeoutStore;
import com.saucelabs.saucebindings.Timeouts;
import com.saucelabs.saucebindings.UnhandledPromptBehavior;
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

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * All Valid Options for a Sauce Labs Session.
 *
 * @see <a href="https://docs.saucelabs.com/dev/test-configuration-options/">Test Configuration Options</a>
 */
@Accessors(chain = true)
@Setter
@Getter
public class SauceOptions extends BaseOptions {
    @Setter(AccessLevel.NONE) @Getter(AccessLevel.NONE) private SauceLabsOptions sauceLabsOptions;

    /**
     * A way to store timeout values.
     */
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

    /**
     * This needs to be public for Capabilities Manager to use it.
     * Valid list Sauce Labs specific options for currently supported platforms.
     */
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

    /**
     * This method allows building a default Sauce Options instance for Chrome
     * Call build() method on return value rather than using directly.
     *
     * @return instance of ChromeConfigurations
     * @see ChromeConfigurations#build()
     */
    public static ChromeConfigurations chrome() {
        return chrome(new ChromeOptions());
    }

    /**
     * This method allows building a Sauce Options instance for Chrome using a provided Selenium ChromeOptions instance.
     * Call build() method on return value rather than using directly.
     *
     * @param chromeOptions an instance of a Selenium ChromeOptions class
     * @return instance of ChromeConfigurations
     * @see ChromeConfigurations#build()
     */
    public static ChromeConfigurations chrome(ChromeOptions chromeOptions) {
        return new ChromeConfigurations(chromeOptions);
    }

    /**
     * This method allows building a default Sauce Options instance for Edge.
     * Call build() method on return value rather than using directly.
     *
     * @return instance of EdgeConfigurations
     * @see EdgeConfigurations#build()
     */
    public static EdgeConfigurations edge() {
        return edge(new EdgeOptions());
    }

    /**
     * This method allows building a Sauce Options instance for Edge using a provided Selenium EdgeOptions instance.
     * Call build() method on return value rather than using directly.
     *
     * @param edgeOptions an instance of a Selenium EdgeOptions class
     * @return instance of EdgeConfigurations
     * @see EdgeConfigurations#build()
     */
    public static EdgeConfigurations edge(EdgeOptions edgeOptions) {
        return new EdgeConfigurations(edgeOptions);
    }

    /**
     * This method allows building a default Sauce Options instance for Firefox.
     * Call build() method on return value rather than using directly.
     *
     * @return instance of FirefoxConfigurations
     * @see FirefoxConfigurations#build()
     */
    public static FirefoxConfigurations firefox() {
        return firefox(new FirefoxOptions());
    }

    /**
     * Allows building a Sauce Options instance for Firefox using a provided Selenium FirefoxOptions instance.
     * Call build() method on return value rather than using directly.
     *
     * @param firefoxOptions an instance of a Selenium FirefoxOptions class
     * @return instance of FirefoxConfigurations
     * @see FirefoxConfigurations#build()
     */
    public static FirefoxConfigurations firefox(FirefoxOptions firefoxOptions) {
        return new FirefoxConfigurations(firefoxOptions);
    }

    /**
     * Allows building a default Sauce Options instance for Internet Explorer.
     * Call build() method on return value rather than using directly.
     *
     * @return instance of InternetExplorerConfigurations
     * @see InternetExplorerConfigurations#build()
     */
    public static InternetExplorerConfigurations ie() {
        return ie(new InternetExplorerOptions());
    }

    /**
     * Allows building a Sauce Options instance for Internet Explorer.
     * Uses a provided Selenium InternetExplorerOptions instance.
     * Call build() method on return value rather than using directly.
     *
     * @param internetExplorerOptions an instance of a Selenium InternetExplorerOptions class
     * @return instance of InternetExplorerConfigurations
     * @see InternetExplorerConfigurations#build()
     */
    public static InternetExplorerConfigurations ie(InternetExplorerOptions internetExplorerOptions) {
        return new InternetExplorerConfigurations(internetExplorerOptions);
    }

    /**
     * This method allows building a default Sauce Options instance for Safari.
     * Call build() method on return value rather than using directly.
     *
     * @return instance of SafariConfigurations
     * @see SafariConfigurations#build()
     */
    public static SafariConfigurations safari() {
        return safari(new SafariOptions());
    }

    /**
     * This method allows building a Sauce Options instance for Safari using a provided Selenium SafariOptions instance.
     * Call build() method on return value rather than using directly.
     *
     * @param safariOptions an instance of a Selenium SafariOptions class
     * @return instance of SafariConfigurations
     * @see SafariConfigurations#build()
     */
    public static SafariConfigurations safari(SafariOptions safariOptions) {
        return new SafariConfigurations(safariOptions);
    }

    /**
     * This method allows getting values of Sauce specific values associated with SauceOptions.
     *
     * @return an instance of SauceLabsOptions built by configurations
     */
    public SauceLabsOptions sauce() {
        return sauceLabsOptions;
    }

    public SauceOptions() {
        this(new MutableCapabilities());
    }

    /**
     * This is not needed by the user, but is needed by the CapabilityManager.
     * TODO: move this package private with lombok
     *
     * @see SauceOptions#getImplicitWaitTimeout()
     * @see SauceOptions#getPageLoadTimeout()
     * @see SauceOptions#getScriptTimeout()
     * @return any timeouts set
     */
    public Map<Timeouts, Integer> getTimeouts() {
        if (timeout.getTimeouts().isEmpty()) {
            return timeouts;
        }
        return timeout.getTimeouts();
    }

    /**
     * For Configuration classes to build a SauceOptions instance.
     * This resets Browser Options subclasses to MutableCapabilities superclass.
     * Selenium deletes capabilities by default when making w3c safe, so need to do separate validations.
     * Equivalent code: `new MutableCapabilities(CapabilitiesUtils.makeW3CSafe(options).findFirst().get().asMap())`
     *
     * @param options Selenium capability values
     */
    SauceOptions(MutableCapabilities options) {
        capabilities = new MutableCapabilities(options.asMap());
        capabilityManager = new CapabilityManager(this);
        sauceLabsOptions = new SauceLabsOptions();
        if (options.getCapability("browserName") != null) {
            setCapability("browserName", options.getCapability("browserName"));
        }
    }

    /**
     * Converts the SauceOptions settings into the capabilities that will get sent to Sauce Labs.
     *
     * @return instance of MutableCapabilities representing all key value pairs set in SauceOptions
     * @see SauceSession#start()
     */
    public MutableCapabilities toCapabilities() {
        capabilityManager.addCapabilities();
        capabilities.setCapability("sauce:options", sauce().toCapabilities());
        return capabilities;
    }

    /**
     * This method is to handle special cases and enums as necessary.
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

    /**
     * Implicit Wait.
     *
     * @return duration to wait for finding an element
     */
    public Duration getImplicitWaitTimeout() {
        return Duration.ofMillis(getTimeouts().get(Timeouts.IMPLICIT));
    }

    /**
     * Page Load Timeout.
     *
     * @return duration to wait for page to load
     */
    public Duration getPageLoadTimeout() {
        return Duration.ofMillis(getTimeouts().get(Timeouts.PAGE_LOAD));
    }

    /**
     * Script Timeout.
     *
     * @return duration to wait for script to execute
     */
    public Duration getScriptTimeout() {
        return Duration.ofMillis(getTimeouts().get(Timeouts.SCRIPT));
    }

    private void deprecatedSetCapability(String key, Object value) {
        System.out.println("WARNING: using merge() of Map with value of (" + key + ") is DEPRECATED");
        System.out.println("place this value inside a nested Map with the keyword 'sauce'");
        sauce().setCapability(key, value);
    }
}
