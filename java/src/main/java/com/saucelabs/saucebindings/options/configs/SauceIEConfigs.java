package com.saucelabs.saucebindings.options.configs;

import com.saucelabs.saucebindings.CapabilityManager;
import com.saucelabs.saucebindings.JobVisibility;
import com.saucelabs.saucebindings.Prerun;
import com.saucelabs.saucebindings.options.BaseOptions;
import com.saucelabs.saucebindings.options.builders.SauceIEBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Accessors(chain = true) @Setter @Getter
public class SauceIEConfigs extends BaseOptions {
    private final SauceIEBuilder sauceIEBuilder;

    private Boolean avoidProxy = null;
    private String build;
    private Integer commandTimeout = null;
    private Map<String, Object> customData = null;
    private Integer idleTimeout = null;
    private String iedriverVersion;
    private JobVisibility jobVisibility; // the actual key for this is a Java reserved keyword "public"; uses enum
    private Integer maxDuration = null;
    private String name;
    private String parentTunnel;
    private Map<Prerun, Object> prerun;
    private URL prerunUrl;
    private Integer priority = null;
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
            "commandTimeout",
            "customData",
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

    public SauceIEConfigs(SauceIEBuilder sauceIEBuilder) {
        this.sauceIEBuilder = sauceIEBuilder;
    }

    public SauceIEBuilder build() {
        CapabilityManager builderManager = new CapabilityManager(this);
        CapabilityManager sauceOptionsManager = new CapabilityManager(sauceIEBuilder.sauceOptions.sauce());

        getValidOptions().forEach((capability) -> {
            Object value = builderManager.getCapability(capability);
            if (value != null) {
                sauceOptionsManager.setCapability(capability, value);
            }
        });
        return sauceIEBuilder;
    }
}
