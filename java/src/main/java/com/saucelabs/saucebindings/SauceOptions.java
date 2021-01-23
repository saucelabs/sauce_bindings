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
     * @deprecated Use com.saucelabs.saucebindings.options.SauceOptions(ChromeOptions options) instead
     */
    @Deprecated
    public SauceOptions(ChromeOptions options) {
        this(new MutableCapabilities(options));
    }

    /**
     * @deprecated Use com.saucelabs.saucebindings.options.SauceOptions(EdgeOptions options) instead
     */
    @Deprecated
    public SauceOptions(EdgeOptions options) {
        this(new MutableCapabilities(options));
    }

    /**
     * @deprecated Use com.saucelabs.saucebindings.options.SauceOptions(FirefoxOptions options) instead
     */
    @Deprecated
    public SauceOptions(FirefoxOptions options) {
        this(new MutableCapabilities(options));
    }

    /**
     * @deprecated Use com.saucelabs.saucebindings.options.SauceOptions(InternetExplorerOptions options) instead
     */
    @Deprecated
    public SauceOptions(InternetExplorerOptions options) {
        this(new MutableCapabilities(options));
    }

    /**
     * @deprecated Use com.saucelabs.saucebindings.options.SauceOptions(SafariOptions options) instead
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
     * @deprecated Use validOptions instead
     * @return
     */
    @Deprecated
    public final List<String> w3cDefinedOptions = this.validOptions;

    /**
     * @deprecated Use sauce().validOptions instead
     * @return
     */
    @Deprecated
    public final List<String> sauceDefinedOptions = sauce().validOptions;

    /**
     * @deprecated Use sauce().validOptions instead
     * @return
     */
    @Deprecated
    public boolean isKnownCI() {
        return sauce().isKnownCI();
    }

    /**
     * @deprecated Use SauceLabsOptions.knownCITools instead
     * @return
     */
    @Deprecated
    public static final Map<String, String> knownCITools = SauceLabsOptions.knownCITools;
}
