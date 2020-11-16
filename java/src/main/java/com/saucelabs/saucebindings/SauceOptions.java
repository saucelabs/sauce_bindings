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

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Accessors(chain = true)
@Setter @Getter
public class SauceOptions extends BaseOptions {
    @Setter(AccessLevel.NONE) @Getter(AccessLevel.NONE) private SauceLabsOptions sauceLabsOptions = null;
    @Setter(AccessLevel.NONE) @Getter(AccessLevel.NONE) private VisualOptions visualOptions = null;
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
        return sauceLabsOptions;
    }

    public VisualOptions visual() {
        if (visualOptions == null) {
            visualOptions = new VisualOptions();
        }
        return visualOptions;
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
        sauceLabsOptions = new SauceLabsOptions();
        if (options.getCapability("browserName") != null) {
            setCapability("browserName", options.getCapability("browserName"));
        }
    }

    public MutableCapabilities toCapabilities() {
        capabilityManager.addCapabilities();
        capabilities.setCapability("sauce:options", sauce().toCapabilities());
        if (visualOptions != null) {
            capabilities.setCapability("sauce:visual", visualOptions.toCapabilities());
        }
        return capabilities;
    }

    // This will convert string value to an enum if necessary
    @Override
    protected void setCapability(String key, Object value) {
        if ("browserName".equals(key)) {
            capabilityManager.validateCapability("Browser", Browser.keys(), (String) value);
            setBrowserName(Browser.valueOf(Browser.fromString((String) value)));
        } else if ("platformName".equals(key)) {
            capabilityManager.validateCapability("SaucePlatform", SaucePlatform.keys(), (String) value);
            setPlatformName(SaucePlatform.valueOf(SaucePlatform.fromString((String) value)));
        } else if ("pageLoadStrategy".equals(key)) {
            capabilityManager.validateCapability("PageLoadStrategy", PageLoadStrategy.keys(), (String) value);
            setPageLoadStrategy(PageLoadStrategy.valueOf(PageLoadStrategy.fromString((String) value)));
        } else if ("unhandledPromptBehavior".equals(key)) {
            capabilityManager.validateCapability("UnhandledPromptBehavior", UnhandledPromptBehavior.keys(), (String) value);
            setUnhandledPromptBehavior(UnhandledPromptBehavior.valueOf(UnhandledPromptBehavior.fromString((String) value)));
        } else if ("timeouts".equals(key)) {
            Map<Timeouts, Integer> timeoutsMap = new HashMap<>();
            ((Map) value).forEach((oldKey, val) -> {
                capabilityManager.validateCapability("Timeouts", Timeouts.keys(), (String) oldKey);
                String keyString = Timeouts.fromString((String) oldKey);
                timeoutsMap.put(Timeouts.valueOf(keyString), (Integer) val);
            });
            setTimeouts(timeoutsMap);
        } else if ("sauce".equals(key)) {
            sauce().mergeCapabilities((HashMap<String, Object>) value);
        } else if ("visual".equals(key)) {
            visual().mergeCapabilities((HashMap<String, Object>) value);
        } else if (sauce().getValidOptions().contains(key)) {
            deprecatedSetCapability(key, value);
        } else {
            capabilityManager.setCapability(key, value);
        }
    }

    @Deprecated
    private void deprecatedSetCapability(String key, Object value) {
        System.out.println("WARNING: using merge() of Map with value of (" + key + ") is DEPRECATED");
        System.out.println("place this value inside a nested Map with the keyword 'sauce'");
        sauce().setCapability(key, value);
    }

    /**
     * @deprecated No longer supported
     * @return
     */
    @Deprecated
    public boolean isKnownCI() {
        return sauce().isKnownCI();
    }

    /**
     * @deprecated Use with sauce() instead
     * @param avoidProxy
     * @return
     */
    @Deprecated
    public BaseOptions setAvoidProxy(Boolean avoidProxy) {
        return sauce().setAvoidProxy(avoidProxy);
    }

    /**
     * @deprecated Use with sauce() instead
     * @param build
     * @return
     */
    @Deprecated
    public BaseOptions setBuild(String build) {
        return sauce().setBuild(build);
    }

    /**
     * @deprecated Use with sauce() instead
     * @param capturePerformance
     * @return
     */
    @Deprecated
    public BaseOptions setCapturePerformance(Boolean capturePerformance) {
        return sauce().setCapturePerformance(capturePerformance);
    }

    /**
     * @deprecated Use with sauce() instead
     * @param chromedriverVersion
     * @return
     */
    @Deprecated
    public BaseOptions setChromedriverVersion(String chromedriverVersion) {
        return sauce().setChromedriverVersion(chromedriverVersion);
    }

    /**
     * @deprecated Use with sauce() instead
     * @param commandTimeout
     * @return
     */
    @Deprecated
    public BaseOptions setCommandTimeout(Integer commandTimeout) {
        return sauce().setCommandTimeout(commandTimeout);
    }

    /**
     * @deprecated Use with sauce() instead
     * @param customData
     * @return
     */
    @Deprecated
    public BaseOptions setCustomData(Map<String, Object> customData) {
        return sauce().setCustomData(customData);
    }

    /**
     * @deprecated Use with sauce() instead
     * @param extendedDebugging
     * @return
     */
    @Deprecated
    public BaseOptions setExtendedDebugging(Boolean extendedDebugging) {
        return sauce().setExtendedDebugging(extendedDebugging);
    }

    /**
     * @deprecated Use with sauce() instead
     * @param idleTimeout
     * @return
     */
    @Deprecated
    public BaseOptions setIdleTimeout(Integer idleTimeout) {
        return sauce().setIdleTimeout(idleTimeout);
    }

    /**
     * @deprecated Use with sauce() instead
     * @param iedriverVersion
     * @return
     */
    @Deprecated
    public BaseOptions setIedriverVersion(String iedriverVersion) {
        return sauce().setIedriverVersion(iedriverVersion);
    }

    /**
     * @deprecated Use with sauce() instead
     * @param maxDuration
     * @return
     */
    @Deprecated
    public BaseOptions setMaxDuration(Integer maxDuration) {
        return sauce().setMaxDuration(maxDuration);
    }

    /**
     * @deprecated Use with sauce() instead
     * @param name
     * @return
     */
    @Deprecated
    public BaseOptions setName(String name) {
        return sauce().setName(name);
    }

    /**
     * @deprecated Use with sauce() instead
     * @param parentTunnel
     * @return
     */
    @Deprecated
    public BaseOptions setParentTunnel(String parentTunnel) {
        return sauce().setParentTunnel(parentTunnel);
    }

    /**
     * @deprecated Use with sauce() instead
     * @param prerun
     * @return
     */
    @Deprecated
    public BaseOptions setPrerun(Map<Prerun, Object> prerun) {
        return sauce().setPrerun(prerun);
    }

    /**
     * @deprecated Use with sauce() instead
     * @param prerunUrl
     * @return
     */
    @Deprecated
    public BaseOptions setPrerunUrl(URL prerunUrl) {
        return sauce().setPrerunUrl(prerunUrl);
    }

    /**
     * @deprecated Use with sauce() instead
     * @param priority
     * @return
     */
    @Deprecated
    public BaseOptions setPriority(Integer priority) {
        return sauce().setPriority(priority);
    }

    /**
     * @deprecated Use with sauce() instead
     * @param jobVisibility
     * @return
     */
    @Deprecated
    public BaseOptions setJobVisibility(JobVisibility jobVisibility) {
        return sauce().setJobVisibility(jobVisibility);
    }

    /**
     * @deprecated Use with sauce() instead
     * @param recordLogs
     * @return
     */
    @Deprecated
    public BaseOptions setRecordLogs(Boolean recordLogs) {
        return sauce().setRecordLogs(recordLogs);
    }

    /**
     * @deprecated Use with sauce() instead
     * @param recordScreenshots
     * @return
     */
    @Deprecated
    public BaseOptions setRecordScreenshots(Boolean recordScreenshots) {
        return sauce().setRecordScreenshots(recordScreenshots);
    }

    /**
     * @deprecated Use with sauce() instead
     * @param recordVideo
     * @return
     */
    @Deprecated
    public BaseOptions setRecordVideo(Boolean recordVideo) {
        return sauce().setRecordVideo(recordVideo);
    }

    /**
     * @deprecated Use with sauce() instead
     * @param screenResolution
     * @return
     */
    @Deprecated
    public BaseOptions setScreenResolution(String screenResolution) {
        return sauce().setScreenResolution(screenResolution);
    }

    /**
     * @deprecated Use with sauce() instead
     * @param seleniumVersion
     * @return
     */
    @Deprecated
    public BaseOptions setSeleniumVersion(String seleniumVersion) {
        return sauce().setSeleniumVersion(seleniumVersion);
    }

    /**
     * @deprecated Use with sauce() instead
     * @param tags
     * @return
     */
    @Deprecated
    public BaseOptions setTags(List<String> tags) {
        return sauce().setTags(tags);
    }

    /**
     * @deprecated Use with sauce() instead
     * @param timeZone
     * @return
     */
    @Deprecated
    public BaseOptions setTimeZone(String timeZone) {
        return sauce().setTimeZone(timeZone);
    }

    /**
     * @deprecated Use with sauce() instead
     * @param tunnelIdentifier
     * @return
     */
    @Deprecated
    public BaseOptions setTunnelIdentifier (String tunnelIdentifier) {
        return sauce().setTunnelIdentifier(tunnelIdentifier);
    }

    /**
     * @deprecated Use with sauce() instead
     * @param videoUploadOnPass
     * @return
     */
    @Deprecated
    public BaseOptions setVideoUploadOnPass(Boolean videoUploadOnPass) {
        return sauce().setVideoUploadOnPass(videoUploadOnPass);
    }

    /**
     * @deprecated Use with sauce() instead
     * @return
     */
    @Deprecated
    public Boolean getAvoidProxy() {
        return sauce().getAvoidProxy();
    }

    /**
     * @deprecated Use with sauce() instead
     * @return
     */
    @Deprecated
    public String getBuild() {
        return sauce().getBuild();
    }

    /**
     * @deprecated Use with sauce() instead
     * @return
     */
    @Deprecated
    public Boolean getCapturePerformance() {
        return sauce().getCapturePerformance();
    }

    /**
     * @deprecated Use with sauce() instead
     * @return
     */
    @Deprecated
    public String getChromedriverVersion() {
        return sauce().getChromedriverVersion();
    }

    /**
     * @deprecated Use with sauce() instead
     * @return
     */
    @Deprecated
    public Integer getCommandTimeout() {
        return sauce().getCommandTimeout();
    }

    /**
     * @deprecated Use with sauce() instead
     * @return
     */
    @Deprecated
    public Map<String, Object> getCustomData() {
        return sauce().getCustomData();
    }

    /**
     * @deprecated Use with sauce() instead
     * @return
     */
    @Deprecated
    public Boolean getExtendedDebugging() {
        return sauce().getExtendedDebugging();
    }

    /**
     * @deprecated Use with sauce() instead
     * @return
     */
    @Deprecated
    public Integer getIdleTimeout() {
        return sauce().getIdleTimeout();
    }

    /**
     * @deprecated Use with sauce() instead
     * @return
     */
    @Deprecated
    public String getIedriverVersion() {
        return sauce().getIedriverVersion();
    }

    /**
     * @deprecated Use with sauce() instead
     * @return
     */
    @Deprecated
    public Integer getMaxDuration() {
        return sauce().getMaxDuration();
    }

    /**
     * @deprecated Use with sauce() instead
     * @return
     */
    @Deprecated
    public String getName() {
        return sauce().getName();
    }

    /**
     * @deprecated Use with sauce() instead
     * @return
     */
    @Deprecated
    public String getParentTunnel() {
        return sauce().getParentTunnel();
    }

    /**
     * @deprecated Use with sauce() instead
     * @return
     */
    @Deprecated
    public Map<Prerun, Object> getPrerun() {
        return sauce().getPrerun();
    }

    /**
     * @deprecated Use with sauce() instead
     * @return
     */
    @Deprecated
    public URL getPrerunUrl() {
        return sauce().getPrerunUrl();
    }

    /**
     * @deprecated Use with sauce() instead
     * @return
     */
    @Deprecated
    public Integer getPriority() {
        return sauce().getPriority();
    }

    /**
     * @deprecated Use with sauce() instead
     * @return
     */
    @Deprecated
    public JobVisibility getJobVisibility() {
        return sauce().getJobVisibility();
    }

    /**
     * @deprecated Use with sauce() instead
     * @return
     */
    @Deprecated
    public Boolean getRecordLogs() {
        return sauce().getRecordLogs();
    }

    /**
     * @deprecated Use with sauce() instead
     * @return
     */
    @Deprecated
    public Boolean getRecordScreenshots() {
        return sauce().getRecordScreenshots();
    }

    /**
     * @deprecated Use with sauce() instead
     * @return
     */
    @Deprecated
    public Boolean getRecordVideo() {
        return sauce().getRecordVideo();
    }

    /**
     * @deprecated Use with sauce() instead
     * @return
     */
    @Deprecated
    public String getScreenResolution() {
        return sauce().getScreenResolution();
    }

    /**
     * @deprecated Use with sauce() instead
     * @return
     */
    @Deprecated
    public String getSeleniumVersion() {
        return sauce().getSeleniumVersion();
    }

    /**
     * @deprecated Use with sauce() instead
     * @return
     */
    @Deprecated
    public List<String> getTags() {
        return sauce().getTags();
    }

    /**
     * @deprecated Use with sauce() instead
     * @return
     */
    @Deprecated
    public String getTimeZone() {
        return sauce().getTimeZone();
    }

    /**
     * @deprecated Use with sauce() instead
     * @return
     */
    @Deprecated
    public String getTunnelIdentifier () {
        return sauce().getTunnelIdentifier();
    }

    /**
     * @deprecated Use with sauce() instead
     * @return
     */
    @Deprecated
    public Boolean getVideoUploadOnPass() {
        return sauce().getVideoUploadOnPass();
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
