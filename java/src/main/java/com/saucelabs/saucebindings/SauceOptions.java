package com.saucelabs.saucebindings;

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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Accessors(chain = true)
@Setter @Getter
public class SauceOptions {
    @Setter(AccessLevel.NONE) private MutableCapabilities capabilities;
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE) private CapabilityManager capabilityManager;
    public TimeoutStore timeout = new TimeoutStore();

    // w3c Settings
    private Browser browserName = Browser.CHROME;
    private String browserVersion = "latest";
    private SaucePlatform platformName = SaucePlatform.WINDOWS_10;
    private PageLoadStrategy pageLoadStrategy;
    private Boolean acceptInsecureCerts = null;
    private Proxy proxy;
    private Boolean setWindowRect = null;
    @Getter(AccessLevel.NONE) private Map<Timeouts, Integer> timeouts;
    private Boolean strictFileInteractability = null;
    private UnhandledPromptBehavior unhandledPromptBehavior;

    // Sauce Settings
    private Boolean avoidProxy = null;
    private String build;
    private Boolean capturePerformance = null;
    private String chromedriverVersion;
    private Integer commandTimeout = null;
    private Map<String, Object> customData = null;
    private Boolean extendedDebugging = null;
    private Integer idleTimeout = null;
    private String iedriverVersion;
    private Integer maxDuration = null;
    private String name;
    private String parentTunnel;
    private Map<Prerun, Object> prerun;
    private URL prerunUrl;
    private Integer priority = null;
    private JobVisibility jobVisibility; // the actual key for this is a Java reserved keyword "public"
    private Boolean recordLogs = null;
    private Boolean recordScreenshots = null;
    private Boolean recordVideo = null;
    private String screenResolution;
    private String seleniumVersion;
    private List<String> tags = null;
    private String timeZone;
    private String tunnelIdentifier;
    private Boolean videoUploadOnPass = null;

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

    // Use Case is pulling serialized information from JSON/YAML, converting it to a HashMap and passing it in
    // This is a preferred pattern as it avoids conditionals in code
    public void mergeCapabilities(Map<String, Object> capabilities) {
        capabilities.forEach(this::setCapability);
    }

    public void setCapability(String key, Object value) {
        if ("browserName".equals(key)) {
            capabilityManager.capabilityValidator("Browser", Browser.keys(), (String) value);
            setBrowserName(Browser.valueOf(Browser.fromString((String) value)));
        } else if ("platformName".equals(key)) {
            capabilityManager.capabilityValidator("SaucePlatform", SaucePlatform.keys(), (String) value);
            setPlatformName(SaucePlatform.valueOf(SaucePlatform.fromString((String) value)));
        } else if ("pageLoadStrategy".equals(key)) {
            capabilityManager.capabilityValidator("PageLoadStrategy", PageLoadStrategy.keys(), (String) value);
            setPageLoadStrategy(PageLoadStrategy.valueOf(PageLoadStrategy.fromString((String) value)));
        } else if ("unhandledPromptBehavior".equals(key)) {
            capabilityManager.capabilityValidator("UnhandledPromptBehavior", UnhandledPromptBehavior.keys(), (String) value);
            setUnhandledPromptBehavior(UnhandledPromptBehavior.valueOf(UnhandledPromptBehavior.fromString((String) value)));
        } else if ("timeouts".equals(key)) {
            Map<Timeouts, Integer> timeoutsMap = new HashMap<>();
            ((Map) value).forEach((oldKey, val) -> {
                capabilityManager.capabilityValidator("Timeouts", Timeouts.keys(), (String) oldKey);
                String keyString = Timeouts.fromString((String) oldKey);
                timeoutsMap.put(Timeouts.valueOf(keyString), (Integer) val);
            });
            setTimeouts(timeoutsMap);
        } else if ("jobVisibility".equals(key)) {
            capabilityManager.capabilityValidator("JobVisibility", JobVisibility.keys(), (String) value);
            setJobVisibility(JobVisibility.valueOf(JobVisibility.fromString((String) value)));
        } else if ("prerun".equals(key)) {
            Map<Prerun, Object> prerunMap = new HashMap<>();
            ((Map) value).forEach((oldKey, val) -> {
                capabilityManager.capabilityValidator("Prerun", Prerun.keys(), (String) oldKey);
                String keyString = Prerun.fromString((String) oldKey);
                prerunMap.put(Prerun.valueOf(keyString), val);
            });
            setPrerun(prerunMap);
        } else {
            try {
                Class<?> type = SauceOptions.class.getDeclaredField(key).getType();
                String setter = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
                Method method = SauceOptions.class.getDeclaredMethod(setter, type);
                method.invoke(this, value);
            } catch (NoSuchFieldException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    protected String getSauceUsername() {
        return SystemManager.get("SAUCE_USERNAME", "Sauce Username was not provided");
    }

    protected String getSauceAccessKey() {
        return SystemManager.get("SAUCE_ACCESS_KEY", "Sauce Access Key was not provided");
    }

    /**
     * @deprecated Use getCapabilities() instead
     * @return
     */
    @Deprecated
    public MutableCapabilities getSeleniumCapabilities() {
        return capabilities;
    }
}
