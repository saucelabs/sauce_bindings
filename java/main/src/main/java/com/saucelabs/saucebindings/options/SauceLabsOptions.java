package com.saucelabs.saucebindings.options;

import com.saucelabs.saucebindings.CITools;
import com.saucelabs.saucebindings.JobVisibility;
import com.saucelabs.saucebindings.Prerun;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.SystemManager;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.openqa.selenium.MutableCapabilities;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Sauce Labs Specific Options.
 *
 * @see <a href="https://docs.saucelabs.com/dev/test-configuration-options/#desktop-and-mobile-capabilities-sauce-specific--optional">
 *     Sauce Labs Specific Configuration Options</a>
 */
@Accessors(chain = true)
@Setter
@Getter
public class SauceLabsOptions extends BaseOptions {
    private Boolean avoidProxy = null;
    private String build;
    private Boolean capturePerformance = null;
    private String chromedriverVersion;
    private Integer commandTimeout = null;
    private Map<String, Object> customData = null;
    private Boolean extendedDebugging = null;
    private String geckodriverVersion;
    private String edgedriverVersion;
    private Integer idleTimeout = null;
    private String iedriverVersion;
    private Integer maxDuration = null;
    private String name;
    private String parentTunnel;
    private Map<Prerun, Object> prerun = new HashMap<>();
    private URL prerunUrl;
    private Integer priority = null;
    private JobVisibility jobVisibility; // the actual key for this is a reserved keyword "public"
    private Boolean recordLogs = null;
    private Boolean recordScreenshots = null;
    private Boolean recordVideo = null;
    private String screenResolution;
    private String seleniumVersion;
    private List<String> tags = null;
    private String timeZone;
    private String tunnelIdentifier;
    private Boolean videoUploadOnPass = null;

    public final List<String> validOptions = Arrays.asList(
            "avoidProxy",
            "build",
            "capturePerformance",
            "chromedriverVersion",
            "commandTimeout",
            "customData",
            "extendedDebugging",
            "geckodriverVersion",
            "edgedriverVersion",
            "idleTimeout",
            "iedriverVersion",
            "jobVisibility",
            "maxDuration",
            "name",
            "parentTunnel",
            "prerun",
            "prerunUrl",
            "priority",
            // public, do not use, reserved keyword, using jobVisibility with enum
            "recordLogs",
            "recordScreenshots",
            "recordVideo",
            "screenResolution",
            "seleniumVersion",
            "tags",
            "timeZone",
            "tunnelIdentifier",
            "videoUploadOnPass");

    /**
     * Default SauceLabsOptions constructor; only used by SauceOptions for storing Sauce specifics options.
     *
     * @deprecated Do not need to use this class directly
     */
    @Deprecated
    public SauceLabsOptions() {
        this(new MutableCapabilities());
    }

    /**
     * Used by SauceOptions to store Sauce Labs specific configurations.
     *
     * @param sauceCapabilities Map or Capabilities instance with capabilities
     */
    SauceLabsOptions(Object sauceCapabilities) {
        capabilityManager = new CapabilityManager(this);
        processOptions(sauceCapabilities);
    }

    /**
     * This converts SauceLabsOptions settings to the Capabilities that will get sent to "sauce:options".
     *
     * @return instance of MutableCapabilities representing all key value pairs set in SauceOptions
     * @see SauceSession#start()
     */
    public MutableCapabilities toCapabilities() {
        capabilities.setCapability("username", getSauceUsername());
        capabilities.setCapability("accessKey", getSauceAccessKey());

        Object visibilityValue = capabilityManager.getCapability("jobVisibility");
        if (visibilityValue != null) {
            capabilities.setCapability("public", visibilityValue);
            setJobVisibility(null);
        }

        Object customDataValue = capabilityManager.getCapability("customData");
        if (customDataValue != null) {
            capabilities.setCapability("custom-data", customDataValue);
            setCustomData(null);
        }

        Object prerunValue = capabilityManager.getCapability("prerunUrl");
        if (prerunValue != null) {
            capabilities.setCapability("prerun", prerunValue);
            setPrerunUrl(null);
        }

        capabilityManager.addCapabilities();
        return capabilities;
    }

    /**
     * This method is to handle special cases and enums as necessary.
     *
     * @param key   Which capability to set on this instance's Selenium MutableCapabilities instance
     * @param value The value of the capability getting set
     */
    @Override
    protected void setCapability(String key, Object value) {
        if ("jobVisibility".equals(key)) {
            capabilityManager.validateCapability("JobVisibility", JobVisibility.keys(), value.toString());
            this.jobVisibility = JobVisibility.valueOf(JobVisibility.fromString(value.toString()));
        } else if ("prerun".equals(key)) {
            ((Map<String, Object>) value).forEach((oldKey, val) -> {
                capabilityManager.validateCapability("Prerun", Prerun.keys(), oldKey);
                prerun.put(Prerun.valueOf(Prerun.fromString(oldKey)), val);
            });
        } else {
            super.setCapability(key, value);
        }
    }

    /**
     * Generates a default build name if one is not set.
     *
     * @return a String representing the best default build name and number for your test based on CI Tool ENV Variables
     */
    public String getBuild() {
        return build != null ? build : CITools.getBuildName() + ": " + CITools.getBuildNumber();
    }

    /**
     * Sauce Bindings generates default build names for known CI tools.
     *
     * @return whether CI is accounted for in code
     * @deprecated This method is no longer supported
     */
    @Deprecated
    public boolean isKnownCI() {
        return CITools.getBuildName() != null;
    }

    /**
     * Known CI Tools.
     *
     * @deprecated use CITools.knownTools instead
     */
    @Deprecated
    public static final Map<String, String> knownCITools = CITools.KNOWN_TOOLS;

    protected String getSauceUsername() {
        return SystemManager.get("SAUCE_USERNAME", "Sauce Username was not provided");
    }

    protected String getSauceAccessKey() {
        return SystemManager.get("SAUCE_ACCESS_KEY", "Sauce Access Key was not provided");
    }

    private void processOptions(Object options) {
        Map<String, Object> sauceOptions = new HashMap<>();
        if (options instanceof MutableCapabilities) {
            MutableCapabilities sauceMutableCaps = (MutableCapabilities) options;
            sauceOptions = sauceMutableCaps.asMap();
        } else if (options instanceof Map) {
            // In case Immutable Map
            sauceOptions = new HashMap<>((Map<? extends String, ?>) options);
        }

        // Ignore credentials passed in, Environment Variables are required
        sauceOptions.remove("username");
        sauceOptions.remove("accessKey");
        sauceOptions.forEach(this::setCapability);
    }
}
