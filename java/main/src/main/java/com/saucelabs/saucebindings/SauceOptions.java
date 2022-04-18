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

/** This is a deprecated class for storing Sauce Options. Please use options.SauceOptions. */
public class SauceOptions extends com.saucelabs.saucebindings.options.SauceOptions {

    /**
     * Create instance from capabilities.
     *
     * @deprecated Use com.saucelabs.saucebindings.options.SauceOptions() instead
     */
    @Deprecated
    public SauceOptions() {
        this(new MutableCapabilities());
    }

    /**
     * Create instance from ChromeOptions.
     *
     * @param options instance of Selenium browser options
     * @deprecated Use com.saucelabs.saucebindings.options.SauceOptions.chrome(options) instead
     */
    @Deprecated
    public SauceOptions(ChromeOptions options) {
        this(new MutableCapabilities(options));
    }

    /**
     * Create instance from EdgeOptions.
     *
     * @param options instance of Selenium browser options
     * @deprecated Use com.saucelabs.saucebindings.options.SauceOptions.edge(options) instead
     */
    @Deprecated
    public SauceOptions(EdgeOptions options) {
        this(new MutableCapabilities(options));
    }

    /**
     * Create instance from FirefoxOptions.
     *
     * @param options instance of Selenium browser options
     * @deprecated Use com.saucelabs.saucebindings.options.SauceOptions.firefox(options) instead
     */
    @Deprecated
    public SauceOptions(FirefoxOptions options) {
        this(new MutableCapabilities(options));
    }

    /**
     * Create instance from InternetExplorerOptions.
     *
     * @param options instance of Selenium browser options
     * @deprecated Use com.saucelabs.saucebindings.options.SauceOptions.ie(options) instead
     */
    @Deprecated
    public SauceOptions(InternetExplorerOptions options) {
        this(new MutableCapabilities(options));
    }

    /**
     * Create instance from SafariOptions.
     *
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
     * Gets the capabilities to be sent to Selenium.
     *
     * @return instance capabilities that will get sent to the RemoteWebDriver
     * @deprecated Use getCapabilities() instead
     */
    @Deprecated
    public MutableCapabilities getSeleniumCapabilities() {
        return capabilities;
    }

    /**
     * Avoid Proxy.
     *
     * @param avoidProxy whether to avoid proxy
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setAvoidProxy(Boolean avoidProxy) {
        return sauce().setAvoidProxy(avoidProxy);
    }

    /**
     * Build name.
     *
     * @param build name of build
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setBuild(String build) {
        return sauce().setBuild(build);
    }

    /**
     * Toggle on capturing performance.
     *
     * @param capturePerformance whether to capture performance during test
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setCapturePerformance(Boolean capturePerformance) {
        return sauce().setCapturePerformance(capturePerformance);
    }

    /**
     * Specify chromedriver version.
     *
     * @param chromedriverVersion version of chromedriver
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setChromedriverVersion(String chromedriverVersion) {
        return sauce().setChromedriverVersion(chromedriverVersion);
    }

    /**
     * command timeout.
     *
     * @param commandTimeout command timeout value
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setCommandTimeout(Integer commandTimeout) {
        return sauce().setCommandTimeout(commandTimeout);
    }

    /**
     * custom data.
     *
     * @param customData custom data
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setCustomData(Map<String, Object> customData) {
        return sauce().setCustomData(customData);
    }

    /**
     * extended debugging.
     *
     * @param extendedDebugging whether extended debugging is turned on
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setExtendedDebugging(Boolean extendedDebugging) {
        return sauce().setExtendedDebugging(extendedDebugging);
    }

    /**
     * idle timeout.
     *
     * @param idleTimeout idle timeout
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setIdleTimeout(Integer idleTimeout) {
        return sauce().setIdleTimeout(idleTimeout);
    }

    /**
     * IE Driver version.
     *
     * @param iedriverVersion version of IE Driver
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setIedriverVersion(String iedriverVersion) {
        return sauce().setIedriverVersion(iedriverVersion);
    }

    /**
     * Max Duration.
     *
     * @param maxDuration maximum duration of test
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setMaxDuration(Integer maxDuration) {
        return sauce().setMaxDuration(maxDuration);
    }

    /**
     * Test name.
     *
     * @param name name of test
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setName(String name) {
        return sauce().setName(name);
    }

    /**
     * Parent Tunnel.
     *
     * @param parentTunnel parent tunnel
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setParentTunnel(String parentTunnel) {
        return sauce().setParentTunnel(parentTunnel);
    }

    /**
     * Prerun executable.
     *
     * @param prerun settings for prerun executable
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setPrerun(Map<Prerun, Object> prerun) {
        return sauce().setPrerun(prerun);
    }

    /**
     * Prerun executable URL.
     *
     * @param prerunUrl location of prerun executable
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setPrerunUrl(URL prerunUrl) {
        return sauce().setPrerunUrl(prerunUrl);
    }

    /**
     * Job priority.
     *
     * @param priority priority of job
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setPriority(Integer priority) {
        return sauce().setPriority(priority);
    }

    /**
     * Job visibility.
     *
     * @param jobVisibility visibility of job
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setJobVisibility(JobVisibility jobVisibility) {
        return sauce().setJobVisibility(jobVisibility);
    }

    /**
     * Record Logs.
     *
     * @param recordLogs whether to record logs of test
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setRecordLogs(Boolean recordLogs) {
        return sauce().setRecordLogs(recordLogs);
    }

    /**
     * Record Screenshots.
     *
     * @param recordScreenshots whether to record screenshots
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setRecordScreenshots(Boolean recordScreenshots) {
        return sauce().setRecordScreenshots(recordScreenshots);
    }

    /**
     * Record Video.
     *
     * @param recordVideo whether to record video of test
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setRecordVideo(Boolean recordVideo) {
        return sauce().setRecordVideo(recordVideo);
    }

    /**
     * Screen Resolution.
     *
     * @param screenResolution screen resolution for test
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setScreenResolution(String screenResolution) {
        return sauce().setScreenResolution(screenResolution);
    }

    /**
     * Selenium Version.
     *
     * @param seleniumVersion version of selenium server for Sauce to use
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setSeleniumVersion(String seleniumVersion) {
        return sauce().setSeleniumVersion(seleniumVersion);
    }

    /**
     * Tags.
     *
     * @param tags user set tags for job
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setTags(List<String> tags) {
        return sauce().setTags(tags);
    }

    /**
     * Time Zone.
     *
     * @param timeZone time zone for test
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setTimeZone(String timeZone) {
        return sauce().setTimeZone(timeZone);
    }

    /**
     * Sauce Connect Tunnel ID.
     *
     * @param tunnelIdentifier name of tunnel
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setTunnelIdentifier(String tunnelIdentifier) {
        return sauce().setTunnelIdentifier(tunnelIdentifier);
    }

    /**
     * Upload Video On Passing.
     *
     * @param videoUploadOnPass whether to upload video when test passes
     * @return instance of Options
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public BaseOptions setVideoUploadOnPass(Boolean videoUploadOnPass) {
        return sauce().setVideoUploadOnPass(videoUploadOnPass);
    }

    /**
     * Avoid Proxy.
     *
     * @return whether to avoid proxy
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Boolean getAvoidProxy() {
        return sauce().getAvoidProxy();
    }

    /**
     * Build Name.
     *
     * @return Build name
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public String getBuild() {
        return sauce().getBuild();
    }

    /**
     * Capture Performance.
     *
     * @return whether to capture performance
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Boolean getCapturePerformance() {
        return sauce().getCapturePerformance();
    }

    /**
     * Chromedriver version.
     *
     * @return Chromedriver version
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public String getChromedriverVersion() {
        return sauce().getChromedriverVersion();
    }

    /**
     * Command Timeout.
     *
     * @return command timeout
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Integer getCommandTimeout() {
        return sauce().getCommandTimeout();
    }

    /**
     * Custom Data.
     *
     * @return custom data
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Map<String, Object> getCustomData() {
        return sauce().getCustomData();
    }

    /**
     * Extended Debugging.
     *
     * @return whether extended debugging is used
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Boolean getExtendedDebugging() {
        return sauce().getExtendedDebugging();
    }

    /**
     * Idle Timeout.
     *
     * @return Idle timeout
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Integer getIdleTimeout() {
        return sauce().getIdleTimeout();
    }

    /**
     * IE Driver Version.
     *
     * @return IE Driver version
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public String getIedriverVersion() {
        return sauce().getIedriverVersion();
    }

    /**
     * Max Duration.
     *
     * @return maximum test duration
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Integer getMaxDuration() {
        return sauce().getMaxDuration();
    }

    /**
     * Test Name.
     *
     * @return name of test
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public String getName() {
        return sauce().getName();
    }

    /**
     * Name of parent account running desired Sauce Connect Tunnel.
     *
     * @return parent tunnel
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public String getParentTunnel() {
        return sauce().getParentTunnel();
    }

    /**
     * Prerun.
     *
     * @return prerun executable settings
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Map<Prerun, Object> getPrerun() {
        return sauce().getPrerun();
    }

    /**
     * Prerun URL.
     *
     * @return location of prerun executable
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public URL getPrerunUrl() {
        return sauce().getPrerunUrl();
    }

    /**
     * Priority.
     *
     * @return job priority
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Integer getPriority() {
        return sauce().getPriority();
    }

    /**
     * Visibiity.
     *
     * @return job visibility
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public JobVisibility getJobVisibility() {
        return sauce().getJobVisibility();
    }

    /**
     * Record Logs.
     *
     * @return whether to record logs
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Boolean getRecordLogs() {
        return sauce().getRecordLogs();
    }

    /**
     * Record Screenshots.
     *
     * @return whether to record screenshots
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Boolean getRecordScreenshots() {
        return sauce().getRecordScreenshots();
    }

    /**
     * Record Video.
     *
     * @return whether to record video
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Boolean getRecordVideo() {
        return sauce().getRecordVideo();
    }

    /**
     * Screen Resolution.
     *
     * @return screen resolution
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public String getScreenResolution() {
        return sauce().getScreenResolution();
    }

    /**
     * Selenium Version.
     *
     * @return selenium version
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public String getSeleniumVersion() {
        return sauce().getSeleniumVersion();
    }

    /**
     * Tags.
     *
     * @return tags
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public List<String> getTags() {
        return sauce().getTags();
    }

    /**
     * Time Zone.
     *
     * @return time zone
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public String getTimeZone() {
        return sauce().getTimeZone();
    }

    /**
     * Sauce Connect Tunnel ID.
     *
     * @return tunnel identifier
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public String getTunnelIdentifier() {
        return sauce().getTunnelIdentifier();
    }

    /**
     * Upload Video When Test Passes.
     *
     * @return whether video should be uploaded when test passes
     * @deprecated Use with sauce() or even better, use the new Builder Pattern
     */
    @Deprecated
    public Boolean getVideoUploadOnPass() {
        return sauce().getVideoUploadOnPass();
    }

    /**
     * Valid W3C Options.
     *
     * @deprecated Use validOptions instead
     */
    @Deprecated
    public final List<String> w3cDefinedOptions = this.validOptions;

    /**
     * Valid Sauce Labs Options.
     *
     * @deprecated Use sauce().validOptions instead
     */
    @Deprecated
    public final List<String> sauceDefinedOptions = sauce().validOptions;

    /**
     * Whether Sauce Bindings can provide default build name for the current CI tool.
     *
     * @return whether CI is accounted for in code
     * @deprecated This method is no longer supported
     */
    @Deprecated
    public boolean isKnownCI() {
        return CITools.getBuildName() != null;
    }

    /**
     * List of known CI Tools.
     *
     * @deprecated This method is no longer supported
     */
    @Deprecated
    public static final Map<String, String> knownCITools = SauceLabsOptions.knownCITools;
}
