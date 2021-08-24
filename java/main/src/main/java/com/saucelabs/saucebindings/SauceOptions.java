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
     * @param options instance of Selenium browser options
     * @deprecated Use com.saucelabs.saucebindings.options.SauceOptions.chrome(options) instead
     */
    @Deprecated
    public SauceOptions(ChromeOptions options) {
        this(new MutableCapabilities(options));
    }

    /**
     * @param options instance of Selenium browser options
     * @deprecated Use com.saucelabs.saucebindings.options.SauceOptions.edge(options) instead
     */
    @Deprecated
    public SauceOptions(EdgeOptions options) {
        this(new MutableCapabilities(options));
    }

    /**
     * @param options instance of Selenium browser options
     * @deprecated Use com.saucelabs.saucebindings.options.SauceOptions.firefox(options) instead
     */
    @Deprecated
    public SauceOptions(FirefoxOptions options) {
        this(new MutableCapabilities(options));
    }

    /**
     * @param options instance of Selenium browser options
     * @deprecated Use com.saucelabs.saucebindings.options.SauceOptions.ie(options) instead
     */
    @Deprecated
    public SauceOptions(InternetExplorerOptions options) {
        this(new MutableCapabilities(options));
    }

    /**
     * @param options instance of Selenium browser options
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
     * @return instance capabilities that will get sent to the RemoteWebDriver
     * @deprecated Use getCapabilities() instead
     */
    @Deprecated
    public MutableCapabilities getSeleniumCapabilities() {
        return capabilities;
    }

    /**
     * @return Map of timeout values from the TimeoutStore
     * @see com.saucelabs.saucebindings.options.VDCConfigurations#setImplicitWaitTimeout(Duration)
     * @see com.saucelabs.saucebindings.options.VDCConfigurations#setScriptTimeout(Duration)
     * @see com.saucelabs.saucebindings.options.VDCConfigurations#setPageLoadTimeout(Duration)
     * @deprecated Set the timeout you are interested in directly instead of using a Map
     */
    @Deprecated
    public Map<Timeouts, Integer> getTimeouts() {
        if (timeout.getTimeouts().isEmpty()) {
            return timeouts;
        }
        return timeout.getTimeouts();
    }

    /**
     * @param avoidProxy whether to avoid proxy
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setAvoidProxy(Boolean avoidProxy) {
        return sauce().setAvoidProxy(avoidProxy);
    }

    /**
     * @param build name of build
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setBuild(String build) {
        return sauce().setBuild(build);
    }

    /**
     * @param capturePerformance whether to capture performance during test
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setCapturePerformance(Boolean capturePerformance) {
        return sauce().setCapturePerformance(capturePerformance);
    }

    /**
     * @param chromedriverVersion version of chromedriver
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setChromedriverVersion(String chromedriverVersion) {
        return sauce().setChromedriverVersion(chromedriverVersion);
    }

    /**
     * @param commandTimeout command timeout value
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setCommandTimeout(Integer commandTimeout) {
        return sauce().setCommandTimeout(commandTimeout);
    }

    /**
     * @param customData custom data
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setCustomData(Map<String, Object> customData) {
        return sauce().setCustomData(customData);
    }

    /**
     * @param extendedDebugging whether extended debugging is turned on
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setExtendedDebugging(Boolean extendedDebugging) {
        return sauce().setExtendedDebugging(extendedDebugging);
    }

    /**
     * @param idleTimeout idle timeout
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setIdleTimeout(Integer idleTimeout) {
        return sauce().setIdleTimeout(idleTimeout);
    }

    /**
     * @param iedriverVersion version of IE Driver
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setIedriverVersion(String iedriverVersion) {
        return sauce().setIedriverVersion(iedriverVersion);
    }

    /**
     * @param maxDuration maximum duration of test
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setMaxDuration(Integer maxDuration) {
        return sauce().setMaxDuration(maxDuration);
    }

    /**
     * @param name name of test
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setName(String name) {
        return sauce().setName(name);
    }

    /**
     * @param parentTunnel parent tunnel
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setParentTunnel(String parentTunnel) {
        return sauce().setParentTunnel(parentTunnel);
    }

    /**
     * @param prerun settings for prerun executable
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setPrerun(Map<Prerun, Object> prerun) {
        return sauce().setPrerun(prerun);
    }

    /**
     * @param prerunUrl location of prerun executable
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setPrerunUrl(URL prerunUrl) {
        return sauce().setPrerunUrl(prerunUrl);
    }

    /**
     * @param priority priority of job
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setPriority(Integer priority) {
        return sauce().setPriority(priority);
    }

    /**
     * @param jobVisibility visibility of job
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setJobVisibility(JobVisibility jobVisibility) {
        return sauce().setJobVisibility(jobVisibility);
    }

    /**
     * @param recordLogs whether to record logs of test
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setRecordLogs(Boolean recordLogs) {
        return sauce().setRecordLogs(recordLogs);
    }

    /**
     * @param recordScreenshots whether to record screenshots
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setRecordScreenshots(Boolean recordScreenshots) {
        return sauce().setRecordScreenshots(recordScreenshots);
    }

    /**
     * @param recordVideo whether to record video of test
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setRecordVideo(Boolean recordVideo) {
        return sauce().setRecordVideo(recordVideo);
    }

    /**
     * @param screenResolution screen resolution for test
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setScreenResolution(String screenResolution) {
        return sauce().setScreenResolution(screenResolution);
    }

    /**
     * @param seleniumVersion version of selenium server for Sauce to use
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setSeleniumVersion(String seleniumVersion) {
        return sauce().setSeleniumVersion(seleniumVersion);
    }

    /**
     * @param tags user set tags for job
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setTags(List<String> tags) {
        return sauce().setTags(tags);
    }

    /**
     * @param timeZone time zone for test
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setTimeZone(String timeZone) {
        return sauce().setTimeZone(timeZone);
    }

    /**
     * @param tunnelIdentifier name of tunnel
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setTunnelIdentifier (String tunnelIdentifier) {
        return sauce().setTunnelIdentifier(tunnelIdentifier);
    }

    /**
     * @param videoUploadOnPass whether to upload video when test passes
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setVideoUploadOnPass(Boolean videoUploadOnPass) {
        return sauce().setVideoUploadOnPass(videoUploadOnPass);
    }

    /**
     * @return whether to avoid proxy
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Boolean getAvoidProxy() {
        return sauce().getAvoidProxy();
    }

    /**
     * @return Build name
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public String getBuild() {
        return sauce().getBuild();
    }

    /**
     * @return whether to capture performance
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Boolean getCapturePerformance() {
        return sauce().getCapturePerformance();
    }

    /**
     * @return Chromedriver version
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public String getChromedriverVersion() {
        return sauce().getChromedriverVersion();
    }

    /**
     * @return command timeout
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Integer getCommandTimeout() {
        return sauce().getCommandTimeout();
    }

    /**
     * @return custom data
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Map<String, Object> getCustomData() {
        return sauce().getCustomData();
    }

    /**
     * @return whether extended debugging is used
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Boolean getExtendedDebugging() {
        return sauce().getExtendedDebugging();
    }

    /**
     * @return Idle timeout
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Integer getIdleTimeout() {
        return sauce().getIdleTimeout();
    }

    /**
     * @return IE Driver version
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public String getIedriverVersion() {
        return sauce().getIedriverVersion();
    }

    /**
     * @return maximum test duration
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Integer getMaxDuration() {
        return sauce().getMaxDuration();
    }

    /**
     * @return name of test
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public String getName() {
        return sauce().getName();
    }

    /**
     * @return parent tunnel
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public String getParentTunnel() {
        return sauce().getParentTunnel();
    }

    /**
     * @return prerun executable settings
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Map<Prerun, Object> getPrerun() {
        return sauce().getPrerun();
    }

    /**
     * @return location of prerun executable
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public URL getPrerunUrl() {
        return sauce().getPrerunUrl();
    }

    /**
     * @return job priority
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Integer getPriority() {
        return sauce().getPriority();
    }

    /**
     * @return job visibility
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public JobVisibility getJobVisibility() {
        return sauce().getJobVisibility();
    }

    /**
     * @return whether to record logs
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Boolean getRecordLogs() {
        return sauce().getRecordLogs();
    }

    /**
     * @return whether to record screenshots
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Boolean getRecordScreenshots() {
        return sauce().getRecordScreenshots();
    }

    /**
     * @return whether to record video
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Boolean getRecordVideo() {
        return sauce().getRecordVideo();
    }

    /**
     * @return screen resolution
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public String getScreenResolution() {
        return sauce().getScreenResolution();
    }

    /**
     * @return selenium version
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public String getSeleniumVersion() {
        return sauce().getSeleniumVersion();
    }

    /**
     * @return tags
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public List<String> getTags() {
        return sauce().getTags();
    }

    /**
     * @return time zone
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public String getTimeZone() {
        return sauce().getTimeZone();
    }

    /**
     * @return tunnel identifier
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public String getTunnelIdentifier () {
        return sauce().getTunnelIdentifier();
    }

    /**
     * @return whether video should be uploaded when test passes
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
     * @return whether CI is accounted for in code
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
