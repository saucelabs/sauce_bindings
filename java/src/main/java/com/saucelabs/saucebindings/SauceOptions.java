package com.saucelabs.saucebindings;

import com.saucelabs.saucebindings.options.BaseOptions;
import com.saucelabs.saucebindings.options.CapabilityManager;
import com.saucelabs.saucebindings.options.SauceLabsOptions;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariOptions;

import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class SauceOptions extends com.saucelabs.saucebindings.options.SauceOptions {

    /**
     * @deprecated Use com.saucelabs.saucebindings.options.SauceOptions() instead
     */
    @Deprecated
    public SauceOptions() {
        this(new MutableCapabilities());
    }

    /**
     * @deprecated Use com.saucelabs.saucebindings.options.SauceOptions.chrome(options) instead
     */
    @Deprecated
    public SauceOptions(ChromeOptions options) {
        this(new MutableCapabilities(options));
    }

    /**
     * @deprecated Use com.saucelabs.saucebindings.options.SauceOptions.edge(options) instead
     */
    @Deprecated
    public SauceOptions(EdgeOptions options) {
        this(new MutableCapabilities(options));
    }

    /**
     * @deprecated Use com.saucelabs.saucebindings.options.SauceOptions.firefox(options) instead
     */
    @Deprecated
    public SauceOptions(FirefoxOptions options) {
        this(new MutableCapabilities(options));
    }

    /**
     * @deprecated Use com.saucelabs.saucebindings.options.SauceOptions.ie(options) instead
     */
    @Deprecated
    public SauceOptions(InternetExplorerOptions options) {
        this(new MutableCapabilities(options));
    }

    /**
     * @deprecated Use com.saucelabs.saucebindings.options.SauceOptions.safari(options) instead
     */
    @Deprecated
    public SauceOptions(SafariOptions options) {
        this(new MutableCapabilities(options));
    }

    private SauceOptions(MutableCapabilities options) {
        capabilities = new MutableCapabilities(options.asMap());
        capabilityManager = new CapabilityManager(this);
        if (options.getCapability("browserName") != null) {
            setCapability("browserName", options.getCapability("browserName"));
        }
    }

    /**
     * @deprecated Use getCapabilities() instead
     * @return instance capabilities that will get sent to the RemoteWebDriver
     */
    @Deprecated
    public MutableCapabilities getSeleniumCapabilities() {
        return capabilities;
    }

    /**
     * @return Map of timeout values from the TimeoutStore
     * @deprecated Set the timeout you are interested in directly instead of using a Map
     * @see com.saucelabs.saucebindings.options.VDCConfigurations#setImplicitWaitTimeout(Duration)
     * @see com.saucelabs.saucebindings.options.VDCConfigurations#setScriptTimeout(Duration)
     * @see com.saucelabs.saucebindings.options.VDCConfigurations#setPageLoadTimeout(Duration)
     */
    @Deprecated
    public Map<Timeouts, Integer> getTimeouts() {
        if (timeout.getTimeouts().isEmpty()) {
            return timeouts;
        }
        return timeout.getTimeouts();
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setAvoidProxy(Boolean avoidProxy) {
        return sauce().setAvoidProxy(avoidProxy);
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setBuild(String build) {
        return sauce().setBuild(build);
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setCapturePerformance(Boolean capturePerformance) {
        return sauce().setCapturePerformance(capturePerformance);
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setChromedriverVersion(String chromedriverVersion) {
        return sauce().setChromedriverVersion(chromedriverVersion);
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setCommandTimeout(Integer commandTimeout) {
        return sauce().setCommandTimeout(commandTimeout);
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setCustomData(Map<String, Object> customData) {
        return sauce().setCustomData(customData);
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setExtendedDebugging(Boolean extendedDebugging) {
        return sauce().setExtendedDebugging(extendedDebugging);
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setIdleTimeout(Integer idleTimeout) {
        return sauce().setIdleTimeout(idleTimeout);
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setIedriverVersion(String iedriverVersion) {
        return sauce().setIedriverVersion(iedriverVersion);
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setMaxDuration(Integer maxDuration) {
        return sauce().setMaxDuration(maxDuration);
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setName(String name) {
        return sauce().setName(name);
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setParentTunnel(String parentTunnel) {
        return sauce().setParentTunnel(parentTunnel);
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setPrerun(Map<Prerun, Object> prerun) {
        return sauce().setPrerun(prerun);
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setPrerunUrl(URL prerunUrl) {
        return sauce().setPrerunUrl(prerunUrl);
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setPriority(Integer priority) {
        return sauce().setPriority(priority);
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setJobVisibility(JobVisibility jobVisibility) {
        return sauce().setJobVisibility(jobVisibility);
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setRecordLogs(Boolean recordLogs) {
        return sauce().setRecordLogs(recordLogs);
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setRecordScreenshots(Boolean recordScreenshots) {
        return sauce().setRecordScreenshots(recordScreenshots);
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setRecordVideo(Boolean recordVideo) {
        return sauce().setRecordVideo(recordVideo);
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setScreenResolution(String screenResolution) {
        return sauce().setScreenResolution(screenResolution);
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setSeleniumVersion(String seleniumVersion) {
        return sauce().setSeleniumVersion(seleniumVersion);
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setTags(List<String> tags) {
        return sauce().setTags(tags);
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setTimeZone(String timeZone) {
        return sauce().setTimeZone(timeZone);
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setTunnelIdentifier (String tunnelIdentifier) {
        return sauce().setTunnelIdentifier(tunnelIdentifier);
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setVideoUploadOnPass(Boolean videoUploadOnPass) {
        return sauce().setVideoUploadOnPass(videoUploadOnPass);
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Boolean getAvoidProxy() {
        return sauce().getAvoidProxy();
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public String getBuild() {
        return sauce().getBuild();
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Boolean getCapturePerformance() {
        return sauce().getCapturePerformance();
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public String getChromedriverVersion() {
        return sauce().getChromedriverVersion();
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Integer getCommandTimeout() {
        return sauce().getCommandTimeout();
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Map<String, Object> getCustomData() {
        return sauce().getCustomData();
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Boolean getExtendedDebugging() {
        return sauce().getExtendedDebugging();
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Integer getIdleTimeout() {
        return sauce().getIdleTimeout();
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public String getIedriverVersion() {
        return sauce().getIedriverVersion();
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Integer getMaxDuration() {
        return sauce().getMaxDuration();
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public String getName() {
        return sauce().getName();
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public String getParentTunnel() {
        return sauce().getParentTunnel();
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Map<Prerun, Object> getPrerun() {
        return sauce().getPrerun();
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public URL getPrerunUrl() {
        return sauce().getPrerunUrl();
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Integer getPriority() {
        return sauce().getPriority();
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public JobVisibility getJobVisibility() {
        return sauce().getJobVisibility();
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Boolean getRecordLogs() {
        return sauce().getRecordLogs();
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Boolean getRecordScreenshots() {
        return sauce().getRecordScreenshots();
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Boolean getRecordVideo() {
        return sauce().getRecordVideo();
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public String getScreenResolution() {
        return sauce().getScreenResolution();
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public String getSeleniumVersion() {
        return sauce().getSeleniumVersion();
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public List<String> getTags() {
        return sauce().getTags();
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public String getTimeZone() {
        return sauce().getTimeZone();
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public String getTunnelIdentifier () {
        return sauce().getTunnelIdentifier();
    }

    /**
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Boolean getVideoUploadOnPass() {
        return sauce().getVideoUploadOnPass();
    }

    /**
     * @deprecated Use validOptions instead
     */
    @Deprecated
    public final List<String> w3cDefinedOptions = this.validOptions;

    /**
     * @deprecated Use sauce().validOptions instead
     */
    @Deprecated
    public final List<String> sauceDefinedOptions = sauce().validOptions;

    /**
     * @deprecated This method is no longer supported
     */
    @Deprecated
    public boolean isKnownCI() {
        return sauce().isKnownCI();
    }

    /**
     * @deprecated This method is no longer supported
     */
    @Deprecated
    public static final Map<String, String> knownCITools = SauceLabsOptions.knownCITools;
}
