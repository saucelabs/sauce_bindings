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

    public boolean isKnownCI() {
        return sauce().isKnownCI();
    }

    public Options setAvoidProxy(Boolean avoidProxy) {
        return sauce().setAvoidProxy(avoidProxy);
    }

    public Options setBuild(String build) {
        return sauce().setBuild(build);
    }

    public Options setCapturePerformance(Boolean capturePerformance) {
        return sauce().setCapturePerformance(capturePerformance);
    }

    public Options setChromedriverVersion(String chromedriverVersion) {
        return sauce().setChromedriverVersion(chromedriverVersion);
    }

    public Options setCommandTimeout(Integer commandTimeout) {
        return sauce().setCommandTimeout(commandTimeout);
    }

    public Options setCustomData(Map<String, Object> customData) {
        return sauce().setCustomData(customData);
    }

    public Options setExtendedDebugging(Boolean extendedDebugging) {
        return sauce().setExtendedDebugging(extendedDebugging);
    }

    public Options setIdleTimeout(Integer idleTimeout) {
        return sauce().setIdleTimeout(idleTimeout);
    }

    public Options setIedriverVersion(String iedriverVersion) {
        return sauce().setIedriverVersion(iedriverVersion);
    }

    public Options setMaxDuration(Integer maxDuration) {
        return sauce().setMaxDuration(maxDuration);
    }

    public Options setName(String name) {
        return sauce().setName(name);
    }

    public Options setParentTunnel(String parentTunnel) {
        return sauce().setParentTunnel(parentTunnel);
    }

    public Options setPrerun(Map<Prerun, Object> prerun) {
        return sauce().setPrerun(prerun);
    }

    public Options setPrerunUrl(URL prerunUrl) {
        return sauce().setPrerunUrl(prerunUrl);
    }

    public Options setPriority(Integer priority) {
        return sauce().setPriority(priority);
    }

    public Options setJobVisibility(JobVisibility jobVisibility) {
        return sauce().setJobVisibility(jobVisibility);
    }

    public Options setRecordLogs(Boolean recordLogs) {
        return sauce().setRecordLogs(recordLogs);
    }

    public Options setRecordScreenshots(Boolean recordScreenshots) {
        return sauce().setRecordScreenshots(recordScreenshots);
    }

    public Options setRecordVideo(Boolean recordVideo) {
        return sauce().setRecordVideo(recordVideo);
    }

    public Options setScreenResolution(String screenResolution) {
        return sauce().setScreenResolution(screenResolution);
    }

    public Options setSeleniumVersion(String seleniumVersion) {
        return sauce().setSeleniumVersion(seleniumVersion);
    }

    public Options setTags(List<String> tags) {
        return sauce().setTags(tags);
    }

    public Options setTimeZone(String timeZone) {
        return sauce().setTimeZone(timeZone);
    }

    public Options setTunnelIdentifier (String tunnelIdentifier) {
        return sauce().setTunnelIdentifier(tunnelIdentifier);
    }

    public Options setVideoUploadOnPass(Boolean videoUploadOnPass) {
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

    public MutableCapabilities getSeleniumCapabilities() {
        return capabilities;
    }
}
