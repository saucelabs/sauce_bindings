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

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Accessors(chain = true)
@Setter @Getter
public class SauceOptions {
    @Setter(AccessLevel.NONE) protected MutableCapabilities capabilities;
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE) protected CapabilityManager capabilityManager;
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

    // Sauce Settings
    protected Boolean avoidProxy = null;
    protected String build;
    protected Boolean capturePerformance = null;
    protected String chromedriverVersion;
    protected Integer commandTimeout = null;
    protected Map<String, Object> customData = null;
    protected Boolean extendedDebugging = null;
    protected Integer idleTimeout = null;
    protected String iedriverVersion;
    protected Integer maxDuration = null;
    protected String name;
    protected String parentTunnel;
    protected Map<Prerun, Object> prerun;
    protected URL prerunUrl;
    protected Integer priority = null;
    protected JobVisibility jobVisibility; // the actual key for this is a Java reserved keyword "public"
    protected Boolean recordLogs = null;
    protected Boolean recordScreenshots = null;
    protected Boolean recordVideo = null;
    protected String screenResolution;
    protected String seleniumVersion;
    protected List<String> tags = null;
    protected String timeZone;
    protected String tunnelIdentifier;
    protected Boolean videoUploadOnPass = null;

    public static final List<String> w3cDefinedOptions = Arrays.asList(
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

    public static final List<String> sauceDefinedOptions = Arrays.asList(
            "avoidProxy",
            "build",
            "capturePerformance",
            "chromedriverVersion",
            "commandTimeout",
            "customData",
            "extendedDebugging",
            "idleTimeout",
            "iedriverVersion",
            "maxDuration",
            "name",
            "parentTunnel",
            "prerun",
            "priority",
            // public, do not use, reserved keyword, using jobVisibility
            "recordLogs",
            "recordScreenshots",
            "recordVideo",
            "screenResolution",
            "seleniumVersion",
            "tags",
            "timeZone",
            "tunnelIdentifier",
            "videoUploadOnPass");

    public static final Map<String, String> knownCITools;
    static {
        knownCITools = new HashMap<>();
        knownCITools.put("Jenkins", "BUILD_TAG");
        knownCITools.put("Bamboo", "bamboo_agentId");
        knownCITools.put("Travis", "TRAVIS_JOB_ID");
        knownCITools.put("Circle", "CIRCLE_JOB");
        knownCITools.put("GitLab", "CI");
        knownCITools.put("TeamCity", "TEAMCITY_PROJECT_NAME");
    }

    public SauceOptions() {
        this(new MutableCapabilities());
    }

    public SauceOptions(ChromeOptions options) {
        this(new MutableCapabilities(options));
    }

    public SauceOptions(EdgeOptions options) {
        this(new MutableCapabilities(options));
    }

    public SauceOptions(FirefoxOptions options) {
        this(new MutableCapabilities(options));
    }

    public SauceOptions(InternetExplorerOptions options) {
        this(new MutableCapabilities(options));
    }

    public SauceOptions(SafariOptions options) {
        this(new MutableCapabilities(options));
    }

    public Map<Timeouts, Integer> getTimeouts() {
        if (timeout.getTimeouts().isEmpty()) {
            return timeouts;
        }
        return timeout.getTimeouts();
    }

    private SauceOptions(MutableCapabilities options) {
        capabilities = new MutableCapabilities(options.asMap());
        capabilityManager = new CapabilityManager(this);
        if (options.getCapability("browserName") != null) {
            setCapability("browserName", options.getCapability("browserName"));
        }
    }

    public MutableCapabilities toCapabilities() {
        capabilityManager.addCapabilities(capabilities, w3cDefinedOptions);

        MutableCapabilities sauceCapabilities = new MutableCapabilities();
        sauceCapabilities.setCapability("username", getSauceUsername());
        sauceCapabilities.setCapability("accessKey", getSauceAccessKey());

        capabilityManager.addCapabilities(sauceCapabilities, sauceDefinedOptions);

        Object visibilityValue = capabilityManager.getCapability("jobVisibility");
        if (visibilityValue != null) {
            sauceCapabilities.setCapability("public", visibilityValue);
        }

        Object prerunValue = capabilityManager.getCapability("prerunUrl");
        if (prerunValue != null) {
            sauceCapabilities.setCapability("prerun", prerunValue);
        }

        capabilities.setCapability("sauce:options", sauceCapabilities);
        return capabilities;
    }

    public String getBuild() {
        if (build != null) {
            return build;
        } else if (SystemManager.get(knownCITools.get("Jenkins")) != null) {
            return SystemManager.get("BUILD_NAME") + ": " + SystemManager.get("BUILD_NUMBER");
        } else if (SystemManager.get(knownCITools.get("Bamboo")) != null) {
            return SystemManager.get("bamboo_shortJobName") + ": " + SystemManager.get("bamboo_buildNumber");
        } else if (SystemManager.get(knownCITools.get("Travis")) != null) {
            return SystemManager.get("TRAVIS_JOB_NAME") + ": " + SystemManager.get("TRAVIS_JOB_NUMBER");
        } else if (SystemManager.get(knownCITools.get("Circle")) != null) {
            return SystemManager.get("CIRCLE_JOB") + ": " + SystemManager.get("CIRCLE_BUILD_NUM");
        } else if (SystemManager.get(knownCITools.get("GitLab")) != null) {
            return SystemManager.get("CI_JOB_NAME") + ": " + SystemManager.get("CI_JOB_ID");
        } else if (SystemManager.get(knownCITools.get("TeamCity")) != null) {
            return SystemManager.get("TEAMCITY_PROJECT_NAME") + ": " + SystemManager.get("BUILD_NUMBER");
        } else {
            return "Build Time: " + System.currentTimeMillis();
        }
    }

    public boolean isKnownCI() {
        return !knownCITools.keySet().stream().allMatch((key) -> SystemManager.get(key) == null);
    }

    /**
     * As an alternative to using the provided methods, this allows users to merge in capabilites from a Map
     * The primary use case is to pull information from a JSON/YAML file and convert it into a MAP
     * This is a recommended pattern to avoid conditionals in code
     *
     * @param capabilities map provided to merge into resulting Selenium MutableCapabilities instance
     */
    public void mergeCapabilities(Map<String, Object> capabilities) {
        capabilities.forEach(this::setCapability);
    }

    /**
     * This method is to handle special cases and enums as necessary
     * Default delegates responsibility to CapabilityManager
     *
     * @param key   Which capability to set on this instance's Selenium MutableCapabilities instance
     * @param value The value of the capability getting set
     */
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
            case "jobVisibility":
                capabilityManager.validateCapability("JobVisibility", JobVisibility.keys(), (String) value);
                setJobVisibility(JobVisibility.valueOf(JobVisibility.fromString((String) value)));
                break;
            case "prerun":
                Map<Prerun, Object> prerunMap = new HashMap<>();
                ((Map) value).forEach((oldKey, val) -> {
                    capabilityManager.validateCapability("Prerun", Prerun.keys(), (String) oldKey);
                    String keyString = Prerun.fromString((String) oldKey);
                    prerunMap.put(Prerun.valueOf(keyString), val);
                });
                setPrerun(prerunMap);
                break;
            default:
                capabilityManager.setCapability(key, value);
        }
    }

    protected String getSauceUsername() {
        return SystemManager.get("SAUCE_USERNAME", "Sauce Username was not provided");
    }

    protected String getSauceAccessKey() {
        return SystemManager.get("SAUCE_ACCESS_KEY", "Sauce Access Key was not provided");
    }
}
