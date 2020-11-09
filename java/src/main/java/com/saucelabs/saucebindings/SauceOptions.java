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
public class SauceOptions extends Options {
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
        capabilities = new MutableCapabilities(options.asMap());
        capabilityManager = new CapabilityManager(this);
        systemManager = new SystemManager();
        if (options.getCapability("browserName") != null) {
            setCapability("browserName", options.getCapability("browserName"));
        }
    }

    public MutableCapabilities toCapabilities() {
        capabilityManager.addCapabilities();
        capabilities.setCapability("sauce:options", sauce().toCapabilities());
        return capabilities;
    }

    // Use Case is pulling serialized information from JSON/YAML, converting it to a HashMap and passing it in
    // This is a preferred pattern as it avoids conditionals in code
    public void mergeCapabilities(Map<String, Object> capabilities) {
        capabilities.forEach(this::setCapability);
    }

    // This will convert string value to an enum if necessary
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
        } else if ("sauce".equals(key)) {
            sauce().setSauceCapabilities((HashMap<String, Object>) value);
        } else if (sauce().getValidOptions().contains(key)) {
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

    /**
     * Everything below here is deprecated for superclass.
     * Prepend with sauce()
     */

    @Deprecated
    public boolean isKnownCI() {
        return sauce().isKnownCI();
    }

    @Deprecated
    public Options setAvoidProxy(Boolean avoidProxy) {
        return sauce().setAvoidProxy(avoidProxy);
    }

    @Deprecated
    public Options setBuild(String build) {
        return sauce().setBuild(build);
    }

    @Deprecated
    public Options setCapturePerformance(Boolean capturePerformance) {
        return sauce().setCapturePerformance(capturePerformance);
    }

    @Deprecated
    public Options setChromedriverVersion(String chromedriverVersion) {
        return sauce().setChromedriverVersion(chromedriverVersion);
    }

    @Deprecated
    public Options setCommandTimeout(Integer commandTimeout) {
        return sauce().setCommandTimeout(commandTimeout);
    }

    @Deprecated
    public Options setCustomData(Map<String, Object> customData) {
        return sauce().setCustomData(customData);
    }

    @Deprecated
    public Options setExtendedDebugging(Boolean extendedDebugging) {
        return sauce().setExtendedDebugging(extendedDebugging);
    }

    @Deprecated
    public Options setIdleTimeout(Integer idleTimeout) {
        return sauce().setIdleTimeout(idleTimeout);
    }

    @Deprecated
    public Options setIedriverVersion(String iedriverVersion) {
        return sauce().setIedriverVersion(iedriverVersion);
    }

    @Deprecated
    public Options setMaxDuration(Integer maxDuration) {
        return sauce().setMaxDuration(maxDuration);
    }

    @Deprecated
    public Options setName(String name) {
        return sauce().setName(name);
    }

    @Deprecated
    public Options setParentTunnel(String parentTunnel) {
        return sauce().setParentTunnel(parentTunnel);
    }

    @Deprecated
    public Options setPrerun(Map<Prerun, Object> prerun) {
        return sauce().setPrerun(prerun);
    }

    @Deprecated
    public Options setPrerunUrl(URL prerunUrl) {
        return sauce().setPrerunUrl(prerunUrl);
    }

    @Deprecated
    public Options setPriority(Integer priority) {
        return sauce().setPriority(priority);
    }

    @Deprecated
    public Options setJobVisibility(JobVisibility jobVisibility) {
        return sauce().setJobVisibility(jobVisibility);
    }

    @Deprecated
    public Options setRecordLogs(Boolean recordLogs) {
        return sauce().setRecordLogs(recordLogs);
    }

    @Deprecated
    public Options setRecordScreenshots(Boolean recordScreenshots) {
        return sauce().setRecordScreenshots(recordScreenshots);
    }

    @Deprecated
    public Options setRecordVideo(Boolean recordVideo) {
        return sauce().setRecordVideo(recordVideo);
    }

    @Deprecated
    public Options setScreenResolution(String screenResolution) {
        return sauce().setScreenResolution(screenResolution);
    }

    @Deprecated
    public Options setSeleniumVersion(String seleniumVersion) {
        return sauce().setSeleniumVersion(seleniumVersion);
    }

    @Deprecated
    public Options setTags(List<String> tags) {
        return sauce().setTags(tags);
    }

    @Deprecated
    public Options setTimeZone(String timeZone) {
        return sauce().setTimeZone(timeZone);
    }

    @Deprecated
    public Options setTunnelIdentifier (String tunnelIdentifier) {
        return sauce().setTunnelIdentifier(tunnelIdentifier);
    }

    @Deprecated
    public Options setVideoUploadOnPass(Boolean videoUploadOnPass) {
        return sauce().setVideoUploadOnPass(videoUploadOnPass);
    }

    @Deprecated
    public Boolean getAvoidProxy() {
        return sauce().getAvoidProxy();
    }

    @Deprecated
    public String getBuild() {
        return sauce().getBuild();
    }

    @Deprecated
    public Boolean getCapturePerformance() {
        return sauce().getCapturePerformance();
    }

    @Deprecated
    public String getChromedriverVersion() {
        return sauce().getChromedriverVersion();
    }

    @Deprecated
    public Integer getCommandTimeout() {
        return sauce().getCommandTimeout();
    }

    @Deprecated
    public Map<String, Object> getCustomData() {
        return sauce().getCustomData();
    }

    @Deprecated
    public Boolean getExtendedDebugging() {
        return sauce().getExtendedDebugging();
    }

    @Deprecated
    public Integer getIdleTimeout() {
        return sauce().getIdleTimeout();
    }

    @Deprecated
    public String getIedriverVersion() {
        return sauce().getIedriverVersion();
    }

    @Deprecated
    public Integer getMaxDuration() {
        return sauce().getMaxDuration();
    }

    @Deprecated
    public String getName() {
        return sauce().getName();
    }

    @Deprecated
    public String getParentTunnel() {
        return sauce().getParentTunnel();
    }

    @Deprecated
    public Map<Prerun, Object> getPrerun() {
        return sauce().getPrerun();
    }

    @Deprecated
    public URL getPrerunUrl() {
        return sauce().getPrerunUrl();
    }

    @Deprecated
    public Integer getPriority() {
        return sauce().getPriority();
    }

    @Deprecated
    public JobVisibility getJobVisibility() {
        return sauce().getJobVisibility();
    }

    @Deprecated
    public Boolean getRecordLogs() {
        return sauce().getRecordLogs();
    }

    @Deprecated
    public Boolean getRecordScreenshots() {
        return sauce().getRecordScreenshots();
    }

    @Deprecated
    public Boolean getRecordVideo() {
        return sauce().getRecordVideo();
    }

    @Deprecated
    public String getScreenResolution() {
        return sauce().getScreenResolution();
    }

    @Deprecated
    public String getSeleniumVersion() {
        return sauce().getSeleniumVersion();
    }

    @Deprecated
    public List<String> getTags() {
        return sauce().getTags();
    }

    @Deprecated
    public String getTimeZone() {
        return sauce().getTimeZone();
    }

    @Deprecated
    public String getTunnelIdentifier () {
        return sauce().getTunnelIdentifier();
    }

    @Deprecated
    public Boolean getVideoUploadOnPass() {
        return sauce().getVideoUploadOnPass();
    }

    @Deprecated
    public MutableCapabilities getSeleniumCapabilities() {
        return capabilities;
    }
}
