package com.saucelabs.simplesauce;

import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.experimental.Accessors;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariOptions;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Accessors(chain = true)
@Setter @Getter
public class SauceOptions {
    @Setter(AccessLevel.NONE) private MutableCapabilities seleniumCapabilities;

    // w3c Settings
    private Options.Browser browserName = Options.Browser.CHROME;
    private String browserVersion = "latest";
    private Options.Platform platformName = Options.Platform.WINDOWS_10;
    private Options.PageLoadStrategy pageLoadStrategy;
    private Boolean acceptInsecureCerts = null;
    private Proxy proxy;
    private Boolean setWindowRect = null;
    private Map<Options.Timeouts, Integer> timeouts;
    private Boolean strictFileInteractability = null;
    private Options.UnhandledPromptBehavior unhandledPromptBehavior;

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
    private Map<Options.Prerun, Object> prerun;
    private URL prerunUrl;
    private Integer priority = null;
    private Options.JobVisibility jobVisibility; // the actual key for this is a Java reserved keyword "public"
    private Boolean recordLogs = null;
    private Boolean recordScreenshots = null;
    private Boolean recordVideo = null;
    private String screenResolution;
    private String seleniumVersion;
    private List<String> tags = null;
    private String timeZone;
    private String tunnelIdentifier;
    private Boolean videoUploadOnPass = null;

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

    private SauceOptions(MutableCapabilities options) {
        seleniumCapabilities = new MutableCapabilities(options.asMap());
        if (options.getCapability("browserName") != null) {
            setCapability("browserName", options.getCapability("browserName"));
        }
    }

    public MutableCapabilities toCapabilities() {
        MutableCapabilities w3cCapabilities = getSeleniumCapabilities();
        MutableCapabilities sauceCapabilities = new MutableCapabilities();

        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                String fieldName = field.getName();
                if (!"seleniumCapabilities".equals(fieldName)) {
                    Object value = getCapability(fieldName);
                    String key = "prerunUrl".equals(fieldName) ? "prerun" : fieldName;
                    if (value == null) {
                        continue;
                    } else if (Options.w3c.contains(key)) {
                        w3cCapabilities.setCapability(fieldName, value);
                    } else if (Options.sauce.contains(key)) {
                        sauceCapabilities.setCapability(fieldName, value);
                    } else if ("jobVisibility".equals(key)) {
                        sauceCapabilities.setCapability("public", value);
                    }
                }
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        w3cCapabilities.setCapability("sauce:options", sauceCapabilities);
        return w3cCapabilities;
    }

    public String getBuild() {
        if (build != null) {
            return build;
            // Jenkins
        } else if (getEnvironmentVariable("BUILD_TAG") != null) {
            return getEnvironmentVariable("BUILD_NAME") + ": " + getEnvironmentVariable("BUILD_NUMBER");
            // Bamboo
        } else if (getEnvironmentVariable("bamboo_agentId") != null) {
            return getEnvironmentVariable("bamboo_shortJobName") + ": " + getEnvironmentVariable("bamboo_buildNumber");
            // Travis
        } else if (getEnvironmentVariable("TRAVIS_JOB_ID") != null) {
            return getEnvironmentVariable("TRAVIS_JOB_NAME") + ": " + getEnvironmentVariable("TRAVIS_JOB_NUMBER");
            // CircleCI
        } else if (getEnvironmentVariable("CIRCLE_JOB") != null) {
            return getEnvironmentVariable("CIRCLE_JOB") + ": " + getEnvironmentVariable("CIRCLE_BUILD_NUM");
            // Gitlab
        } else if (getEnvironmentVariable("CI") != null) {
            return getEnvironmentVariable("CI_JOB_NAME") + ": " + getEnvironmentVariable("CI_JOB_ID");
            // Team City
        } else if (getEnvironmentVariable("TEAMCITY_PROJECT_NAME") != null) {
            return getEnvironmentVariable("TEAMCITY_PROJECT_NAME") + ": " + getEnvironmentVariable("BUILD_NUMBER");
            // Default
        } else {
            return "Build Time: " + System.currentTimeMillis();
        }
    }

    // Use Case is pulling serialized information from JSON/YAML, converting it to a HashMap and passing it in
    // This is a preferred pattern as it avoids conditionals in code
    public void setCapabilities(Map<String, Object> capabilities) {
        capabilities.forEach(this::setCapability);
    }

    // This might be made public in future version; For now, no good reason to prefer it over direct accessor
    private Object getCapability(String capability) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String getter = "get" + capability.substring(0, 1).toUpperCase() + capability.substring(1);
        Method declaredMethod = SauceOptions.class.getDeclaredMethod(getter);
        return declaredMethod.invoke(this);
    }

    // This might be made public in future version; For now, no good reason to prefer it over direct accessor
    public void setCapability(String key, Object value) {
        if (Options.primaryEnum.contains(key) && value.getClass().equals(String.class)) {
            setEnumCapability(key, (String) value);
        } else if ("prerun".equals(key) && ((HashMap) value).keySet().toArray()[0].getClass().equals(String.class)) {
            Map<Options.Prerun, Object> prerunMap = new HashMap<>();
            HashMap mapValue = (HashMap) value;
            mapValue.forEach((oldKey, val) -> {
                String keyString = Options.Prerun.fromString((String) oldKey);
                prerunMap.put(Options.Prerun.valueOf(keyString), val);
            });
            setPrerun(prerunMap);
        } else if ("timeouts".equals(key) && ((HashMap) value).keySet().toArray()[0].getClass().equals(String.class)) {
            Map<Options.Timeouts, Integer> timeoutsMap = new HashMap<>();
            HashMap mapValue = (HashMap) value;
            mapValue.forEach((oldKey, val) -> {
                String keyString = Options.Timeouts.fromString((String) oldKey);
                timeoutsMap.put(Options.Timeouts.valueOf(keyString), (Integer) val);
            });
            setTimeouts(timeoutsMap);
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

    private void setEnumCapability(String key, String value) {
        switch (key) {
            case "browserName":
                if (!Options.Browser.keys().contains(value)) {
                    String message = value + " is not a valid Browser, please choose from: " + Options.Browser.keys();
                    throw new InvalidSauceOptionsArguementException(message);
                } else {
                    setBrowserName(Options.Browser.valueOf(Options.Browser.fromString(value)));
                }
                break;
            case "platformName":
                if (!Options.Platform.keys().contains(value)) {
                    String message = value + " is not a valid Platform, please choose from: " + Options.Platform.keys();
                    throw new InvalidSauceOptionsArguementException(message);
                } else {
                    setPlatformName(Options.Platform.valueOf(Options.Platform.fromString(value)));
                }
                break;
            case "jobVisibility":
                if (!Options.JobVisibility.keys().contains(value)) {
                    String message = value + " is not a valid Job Visibility, please choose from: " + Options.JobVisibility.keys();
                    throw new InvalidSauceOptionsArguementException(message);
                } else {
                    setJobVisibility(Options.JobVisibility.valueOf(Options.JobVisibility.fromString(value)));
                }
                break;
            case "pageLoadStrategy":
                if (!Options.PageLoadStrategy.keys().contains(value)) {
                    String message = value + " is not a valid Job Visibility, please choose from: " + Options.PageLoadStrategy.keys();
                    throw new InvalidSauceOptionsArguementException(message);
                } else {
                    setPageLoadStrategy(Options.PageLoadStrategy.valueOf(Options.PageLoadStrategy.fromString(value)));
                }
                break;
            case "unhandledPromptBehavior":
                if (!Options.UnhandledPromptBehavior.keys().contains(value)) {
                    String message = value + " is not a valid Job Visibility, please choose from: " + Options.UnhandledPromptBehavior.keys();
                    throw new InvalidSauceOptionsArguementException(message);
                } else {
                    setUnhandledPromptBehavior(Options.UnhandledPromptBehavior.valueOf(Options.UnhandledPromptBehavior.fromString(value)));
                }
                break;
            default:
                break;
        }
    }

    protected String getEnvironmentVariable(String key) {
        return System.getenv(key);
    }
}
