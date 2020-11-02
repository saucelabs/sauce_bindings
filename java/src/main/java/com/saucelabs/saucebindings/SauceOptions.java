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
import java.util.Set;

@Accessors(chain = true)
@Setter @Getter
public class SauceOptions {
    @Setter(AccessLevel.NONE) private MutableCapabilities seleniumCapabilities;
    @Setter(AccessLevel.NONE) @Getter(AccessLevel.NONE) private SauceLabsOptions sauceLabsOptions = null;
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

    public static final List<String> primaryEnum = Arrays.asList(
            "browserName",
            "pageLoadStrategy",
            "platformName",
            "timeouts",
            "unhandledPromptBehavior"
    );

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


    public SauceLabsOptions sauce() {
        if (sauceLabsOptions == null) {
            sauceLabsOptions = new SauceLabsOptions();
        }
        return sauceLabsOptions;
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

    protected SauceOptions(MutableCapabilities options) {
        seleniumCapabilities = new MutableCapabilities(options.asMap());
        if (options.getCapability("browserName") != null) {
            setCapability("browserName", options.getCapability("browserName"));
        }
    }

    public MutableCapabilities toCapabilities() {
        w3cDefinedOptions.forEach((capability) -> {
            addCapabilityIfDefined(seleniumCapabilities, capability);
        });

        seleniumCapabilities.setCapability("sauce:options", sauce().toCapabilities());
        return seleniumCapabilities;
    }

    protected void addCapabilityIfDefined(MutableCapabilities capabilities, String capability) {
        Object value = this.getCapability(capability);
        if (value != null) {
            capabilities.setCapability(capability, value);
        }
    }


    // Use Case is pulling serialized information from JSON/YAML, converting it to a HashMap and passing it in
    // This is a preferred pattern as it avoids conditionals in code
    public void mergeCapabilities(Map<String, Object> capabilities) {
        capabilities.forEach(this::setCapability);
    }

    // This might be made public in future version; For now, no good reason to prefer it over direct accessor
    protected Object getCapability(String capability) {
        try {
            String getter = "get" + capability.substring(0, 1).toUpperCase() + capability.substring(1);
            Method declaredMethod = null;
            declaredMethod = this.getClass().getDeclaredMethod(getter);
            return declaredMethod.invoke(this);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setCapability(String key, Object value) {
        if (primaryEnum.contains(key) && value.getClass().equals(String.class)) {
            setEnumCapability(key, (String) value);
        } else if ("timeouts".equals(key)) {
            Map<Timeouts, Integer> timeoutsMap = new HashMap<>();
            ((Map) value).forEach((oldKey, val) -> {
                enumValidator("Timeouts", Timeouts.keys(), (String) oldKey);
                String keyString = Timeouts.fromString((String) oldKey);
                timeoutsMap.put(Timeouts.valueOf(keyString), (Integer) val);
            });
            setTimeouts(timeoutsMap);
        } else if ("sauce".equals(key)) {
            sauce().setSauceCapabilities((HashMap<String, Object>) value);
        } else if (SauceLabsOptions.sauceLabsOptions.contains(key)) {
            sauce().setSauceCapability(key, value);
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

    private boolean isKeyString(HashMap map) {
        return map.keySet().toArray()[0].getClass().equals(String.class);
    }

    // this method is only used when setting capabilities from mergeCapabilities method
    private void setEnumCapability(String key, String value) {
        switch (key) {
            case "browserName":
                enumValidator("Browser", Browser.keys(), value);
                setBrowserName(Browser.valueOf(Browser.fromString(value)));
                break;
            case "platformName":
                enumValidator("SaucePlatform", SaucePlatform.keys(), value);
                setPlatformName(SaucePlatform.valueOf(SaucePlatform.fromString(value)));
                break;
            case "pageLoadStrategy":
                enumValidator("PageLoadStrategy", PageLoadStrategy.keys(), value);
                setPageLoadStrategy(PageLoadStrategy.valueOf(PageLoadStrategy.fromString(value)));
                break;
            case "unhandledPromptBehavior":
                enumValidator("UnhandledPromptBehavior", UnhandledPromptBehavior.keys(), value);
                setUnhandledPromptBehavior(UnhandledPromptBehavior.valueOf(UnhandledPromptBehavior.fromString(value)));
                break;
            default:
                break;
        }
    }

    protected void enumValidator(String name, Set values, String value) {
        if (!values.contains(value)) {
            String message = value + " is not a valid " + name + ", please choose from: " + values;
            throw new InvalidSauceOptionsArgumentException(message);
        }
    }

    protected String tryToGetVariable(String key, String errorMessage) {
        if (getSystemProperty(key) != null) {
            return getSystemProperty(key);
        } else if (getEnvironmentVariable(key) != null) {
            return getEnvironmentVariable(key);
        } else {
            throw new SauceEnvironmentVariablesNotSetException(errorMessage);
        }
    }

    protected String getSystemProperty(String key) {
        return System.getProperty(key);
    }

    protected String getEnvironmentVariable(String key) {
        return System.getenv(key);
    }

    /**
     * Everything below here is deprecated for superclass.
     * Prepend with sauce()
     */

    public boolean isKnownCI() {
        return sauce().isKnownCI();
    }

    public SauceOptions setAvoidProxy(Boolean avoidProxy) {
        return sauce().setAvoidProxy(avoidProxy);
    }

    public SauceOptions setBuild(String build) {
        return sauce().setBuild(build);
    }

    public SauceOptions setCapturePerformance(Boolean capturePerformance) {
        return sauce().setCapturePerformance(capturePerformance);
    }

    public SauceOptions setChromedriverVersion(String chromedriverVersion) {
        return sauce().setChromedriverVersion(chromedriverVersion);
    }

    public SauceOptions setCommandTimeout(Integer commandTimeout) {
        return sauce().setCommandTimeout(commandTimeout);
    }

    public SauceOptions setCustomData(Map<String, Object> customData) {
        return sauce().setCustomData(customData);
    }

    public SauceOptions setExtendedDebugging(Boolean extendedDebugging) {
        return sauce().setExtendedDebugging(extendedDebugging);
    }

    public SauceOptions setIdleTimeout(Integer idleTimeout) {
        return sauce().setIdleTimeout(idleTimeout);
    }

    public SauceOptions setIedriverVersion(String iedriverVersion) {
        return sauce().setIedriverVersion(iedriverVersion);
    }

    public SauceOptions setMaxDuration(Integer maxDuration) {
        return sauce().setMaxDuration(maxDuration);
    }

    public SauceOptions setName(String name) {
        return sauce().setName(name);
    }

    public SauceOptions setParentTunnel(String parentTunnel) {
        return sauce().setParentTunnel(parentTunnel);
    }

    public SauceOptions setPrerun(Map<Prerun, Object> prerun) {
        return sauce().setPrerun(prerun);
    }

    public SauceOptions setPrerunUrl(URL prerunUrl) {
        return sauce().setPrerunUrl(prerunUrl);
    }

    public SauceOptions setPriority(Integer priority) {
        return sauce().setPriority(priority);
    }

    public SauceOptions setJobVisibility(JobVisibility jobVisibility) {
        return sauce().setJobVisibility(jobVisibility);
    }

    public SauceOptions setRecordLogs(Boolean recordLogs) {
        return sauce().setRecordLogs(recordLogs);
    }

    public SauceOptions setRecordScreenshots(Boolean recordScreenshots) {
        return sauce().setRecordScreenshots(recordScreenshots);
    }

    public SauceOptions setRecordVideo(Boolean recordVideo) {
        return sauce().setRecordVideo(recordVideo);
    }

    public SauceOptions setScreenResolution(String screenResolution) {
        return sauce().setScreenResolution(screenResolution);
    }

    public SauceOptions setSeleniumVersion(String seleniumVersion) {
        return sauce().setSeleniumVersion(seleniumVersion);
    }

    public SauceOptions setTags(List<String> tags) {
        return sauce().setTags(tags);
    }

    public SauceOptions setTimeZone(String timeZone) {
        return sauce().setTimeZone(timeZone);
    }

    public SauceOptions setTunnelIdentifier (String tunnelIdentifier) {
        return sauce().setTunnelIdentifier(tunnelIdentifier);
    }

    public SauceOptions setVideoUploadOnPass(Boolean videoUploadOnPass) {
        return sauce().setVideoUploadOnPass(videoUploadOnPass);
    }

    public Boolean getAvoidProxy() {
        return sauce().getAvoidProxy();
    }

    public String getBuild() {
        return sauce().getBuild();
    }

    public Boolean getCapturePerformance() {
        return sauce().getCapturePerformance();
    }

    public String getChromedriverVersion() {
        return sauce().getChromedriverVersion();
    }

    public Integer getCommandTimeout() {
        return sauce().getCommandTimeout();
    }

    public Map<String, Object> getCustomData() {
        return sauce().getCustomData();
    }

    public Boolean getExtendedDebugging() {
        return sauce().getExtendedDebugging();
    }

    public Integer getIdleTimeout() {
        return sauce().getIdleTimeout();
    }

    public String getIedriverVersion() {
        return sauce().getIedriverVersion();
    }

    public Integer getMaxDuration() {
        return sauce().getMaxDuration();
    }

    public String getName() {
        return sauce().getName();
    }

    public String getParentTunnel() {
        return sauce().getParentTunnel();
    }

    public Map<Prerun, Object> getPrerun() {
        return sauce().getPrerun();
    }

    public URL getPrerunUrl() {
        return sauce().getPrerunUrl();
    }

    public Integer getPriority() {
        return sauce().getPriority();
    }

    public JobVisibility getJobVisibility() {
        return sauce().getJobVisibility();
    }

    public Boolean getRecordLogs() {
        return sauce().getRecordLogs();
    }

    public Boolean getRecordScreenshots() {
        return sauce().getRecordScreenshots();
    }

    public Boolean getRecordVideo() {
        return sauce().getRecordVideo();
    }

    public String getScreenResolution() {
        return sauce().getScreenResolution();
    }

    public String getSeleniumVersion() {
        return sauce().getSeleniumVersion();
    }

    public List<String> getTags() {
        return sauce().getTags();
    }

    public String getTimeZone() {
        return sauce().getTimeZone();
    }

    public String getTunnelIdentifier () {
        return sauce().getTunnelIdentifier();
    }

    public Boolean getVideoUploadOnPass() {
        return sauce().getVideoUploadOnPass();
    }
}
