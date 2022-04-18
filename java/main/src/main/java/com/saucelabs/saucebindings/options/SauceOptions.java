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
import org.json.JSONObject;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
     *
     * @deprecated set timeouts directly with setter methods
     */
    @Deprecated
    public TimeoutStore timeout = new TimeoutStore();

    // w3c Settings
    @Setter(AccessLevel.NONE) protected Browser browserName = Browser.CHROME;
    protected String browserVersion = "latest";
    protected SaucePlatform platformName = SaucePlatform.WINDOWS_10;
    protected PageLoadStrategy pageLoadStrategy;
    protected Boolean acceptInsecureCerts = null;
    protected Proxy proxy;
    protected Boolean setWindowRect = null;
    @Setter(AccessLevel.NONE) @Getter(AccessLevel.NONE) protected Map<Timeouts, Long> timeouts = new HashMap<>();
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
     * Supports creating a SauceOptions instance from a config file instead of with conditionals in code
     * JSON & YAML are both supported.
     * Example Usage:
     * <pre>{@code new SauceOptions(pathToConfigFile, System.getEnv("firefox_mac"));}</pre>
     *
     * @param path location of the yaml or json configuration file
     * @param key which configuration in that file to use
     */
    public static SauceOptions fromFile(Path path, String key) {
        Map<String, Object> options = (Map<String, Object>) deserialize(path).get(key);
        return new SauceOptions(options);
    }

    /**
     * This method allows building a default Sauce Options instance for Chrome.
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

    private static Map<String, Object> deserialize(Path path) {
        PathMatcher yamlMatcher = FileSystems.getDefault().getPathMatcher("glob:**.{yaml,yml}");
        PathMatcher jsonMatcher = FileSystems.getDefault().getPathMatcher("glob:**.json");

        try {
            if (yamlMatcher.matches(path)) {
                return loadYaml(path);
            } else if (jsonMatcher.matches(path)) {
                return loadJSON(path);
            }
        } catch (IOException e) {
            throw new InvalidSauceOptionsArgumentException("Invalid file Location", e);
        }

        throw new InvalidSauceOptionsArgumentException("Unable to parse this file type");
    }

    private static Map<String, Object> loadYaml(Path path) throws FileNotFoundException {
        InputStream input = new FileInputStream(path.toString());
        Yaml yaml = new Yaml();
        return yaml.load(input);
    }

    private static Map<String, Object> loadJSON(Path path) throws IOException {
        String content = new String(Files.readAllBytes(path));
        JSONObject jsonObject = new JSONObject(content);
        return jsonObject.toMap();
    }

    /**
     * This method allows getting values of Sauce specific values associated with SauceOptions.
     *
     * @return an instance of SauceLabsOptions built by configurations
     */
    public SauceLabsOptions sauce() {
        return sauceLabsOptions;
    }

    /**
     * This was created to be used by SauceSession, but SauceSession does something different now.
     *
     * @deprecated Use static browser method instead e.g., SauceOptions.chrome().build()
     */
    @Deprecated
    public SauceOptions() {
        this(new MutableCapabilities());
    }

    /**
     * This is primarily intended to facilitate using a JSON file or a YAML to create a SauceOptions instance.
     * Needs to be public for SauceSession to support a MutableCapabilities constructor.
     *
     * @see  SauceOptions#fromFile(Path path, String key)
     * @param map key value pairs to set on a SauceOptions instance
     * @deprecated This is intended for private access, do not use directly
     */
    @Deprecated
    public SauceOptions(Map<String, Object> map) {
        this(new MutableCapabilities(map));
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
        sauceLabsOptions = new SauceLabsOptions(capabilities.getCapability("sauce:options"));

        if (options.getCapability("browserName") != null) {
            setCapability("browserName", options.getCapability("browserName"));
        }

        processOptions();
    }

    /**
     * This is not needed by the user, but is needed by the CapabilityManager.
     * TODO: move this package private with lombok
     *
     * @see SauceOptions#getImplicitWaitTimeout()
     * @see SauceOptions#getPageLoadTimeout()
     * @see SauceOptions#getScriptTimeout()
     * @return any timeouts set
     * @deprecated use the getter for the specific timeout you are interested in
     */
    @Deprecated
    public Map<Timeouts, Integer> getTimeouts() {
        if (timeout.getTimeouts().isEmpty()) {
            return timeouts.entrySet().stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            e -> e.getValue().intValue()
                    ));
        }
        return timeout.getTimeouts();
    }

    // This insanity is required because Java treats InternetExplorerOptions differently for some reason
    private void processOptions() {
        capabilities.asMap().keySet().forEach((capability) -> {
            if (!capability.contains(":")
                    && !"browserName".equals(capability)
                    && !(Browser.INTERNET_EXPLORER.equals(browserName)
                    && ((Map<String, Object>) capabilities.getCapability("se:ieOptions")).containsKey(capability))) {
                setCapability(capability, capabilities.getCapability(capability));
            }
        });
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
            capabilityManager.validateCapability("Browser", Browser.keys(), value.toString());
            this.browserName = Browser.valueOf(Browser.fromString(value.toString()));
            break;
        case "platformName":
            capabilityManager.validateCapability("SaucePlatform", SaucePlatform.keys(), value.toString());
            this.platformName = SaucePlatform.valueOf(SaucePlatform.fromString(value.toString()));
            break;
        case "pageLoadStrategy":
            capabilityManager.validateCapability("PageLoadStrategy", PageLoadStrategy.keys(), value.toString());
            this.pageLoadStrategy = PageLoadStrategy.valueOf(PageLoadStrategy.fromString(value.toString()));
            break;
        case "unhandledPromptBehavior":
            capabilityManager.validateCapability("UnhandledPromptBehavior",
                    UnhandledPromptBehavior.keys(),
                    value.toString());
            this.unhandledPromptBehavior = UnhandledPromptBehavior
                    .valueOf(UnhandledPromptBehavior
                    .fromString(value.toString()));
            break;
        case "timeouts":
            ((Map<String, Integer>) value).forEach((oldKey, val) -> {
                capabilityManager.validateCapability("Timeouts", Timeouts.keys(), oldKey);
                timeouts.put(Timeouts.valueOf(Timeouts.fromString(oldKey)), val.longValue());
            });
            break;
        case "sauce":
        case "sauce:options":
            Map<String, Object> map = new HashMap<>((Map<? extends String, ?>) value);
            map.keySet().forEach(k -> sauce().setCapability(k, map.get(k)));
            break;
        default:
            if (sauce().getValidOptions().contains(key)) {
                deprecatedSetCapability(key, value);
            } else if (!key.contains(":")) { // fapabilities() ignores this, the new constructor does not
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
        if (getTimeouts().get(Timeouts.IMPLICIT) != null) {
            return Duration.ofMillis((getTimeouts().get(Timeouts.IMPLICIT).longValue()));
        } else {
            return null;
        }
    }

    /**
     * Page Load Timeout.
     *
     * @return duration to wait for page to load
     */
    public Duration getPageLoadTimeout() {
        if (getTimeouts().get(Timeouts.PAGE_LOAD) != null) {
            return Duration.ofMillis(getTimeouts().get(Timeouts.PAGE_LOAD).longValue());
        } else {
            return null;
        }
    }

    /**
     * Script Timeout.
     *
     * @return duration to wait for script to execute
     */
    public Duration getScriptTimeout() {
        if (getTimeouts().get(Timeouts.SCRIPT) != null) {
            return Duration.ofMillis(getTimeouts().get(Timeouts.SCRIPT).longValue());
        } else {
            return null;
        }
    }

    /**
     * Timeouts.
     *
     * @param timeouts valid timeouts and their associated values in milliseconds
     * @return instance of this class
     * @deprecated Use setters for specific timeouts instead
     */
    @Deprecated
    public SauceOptions setTimeouts(Map<Timeouts, Long> timeouts) {
        this.timeouts = timeouts;
        return this;
    }

    /**
     * Implicit Wait Timeout.
     *
     * @param timeout how long to wait before returning element not found error
     * @return instance of Options
     */
    public SauceOptions setImplicitWaitTimeout(Duration timeout) {
        timeouts.put(Timeouts.IMPLICIT, timeout.toMillis());
        return this;
    }

    /**
     * Page Load Timeout.
     *
     * @param timeout how long a page is allowed to spend loading
     * @return instance of Options
     */
    public SauceOptions setPageLoadTimeout(Duration timeout) {
        timeouts.put(Timeouts.PAGE_LOAD, timeout.toMillis());
        return this;
    }

    /**
     * Script Timeout.
     *
     * @param timeout how long a script is allowed to run
     * @return instance of Options
     */
    public SauceOptions setScriptTimeout(Duration timeout) {
        timeouts.put(Timeouts.SCRIPT, timeout.toMillis());
        return this;
    }

    private void deprecatedSetCapability(String key, Object value) {
        System.out.println("WARNING: using merge() of Map with value of (" + key + ") is DEPRECATED");
        System.out.println("place this value inside a nested Map with the keyword 'sauce'");
        sauce().setCapability(key, value);
    }

    /**
     * This sets the browser for the Session.
     *
     * @param browserName which browser to use
     * @return this SauceOptions instance
     * @deprecated use static browser methods with SauceOptions class to set browser name
     */
    @Deprecated
    public SauceOptions setBrowserName(Browser browserName) {
        this.browserName = browserName;
        return this;
    }
}
