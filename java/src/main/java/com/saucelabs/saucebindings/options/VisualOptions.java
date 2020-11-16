package com.saucelabs.saucebindings.options;

import com.saucelabs.saucebindings.CapabilityManager;
import com.saucelabs.saucebindings.SystemManager;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.openqa.selenium.MutableCapabilities;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Accessors(chain = true) @Setter @Getter
public class VisualOptions extends BaseOptions {

    // Sauce Visual Settings
    // https://screener.io/v2/docs/visual-e2e/visual-options
    private String projectName = "Default Project Name";
    private String viewportSize;
    private String branch;
    private String baseBranch;
    private Map<String, Object> diffOptions = null;
    private String ignore = null;   // This probably should be a List not a String, but conversions, ugh
    private Boolean failOnNewStates = null;
    private Boolean alwaysAcceptBaseBranch = null;
    private Boolean disableBranchBaseline = null;
    private Boolean scrollAndStitchScreenshots = null;
    private Boolean disableCORS = null;

    public final List<String> validOptions = Arrays.asList(
            "projectName",
            "viewportSize",
            "branch",
            "baseBranch",
            "diffOptions",
            "ignore",
            "failOnNewStates",
            "alwaysAcceptBaseBranch",
            "disableBranchBaseline",
            "scrollAndStitchScreenshots",
            "disableCORS");

    public VisualOptions() {
        capabilityManager = new CapabilityManager(this);
    }

    public MutableCapabilities toCapabilities() {
        capabilities.setCapability("apiKey", getScreenerApiKey());
        capabilityManager.addCapabilities();
        return capabilities;
    }

    protected String getScreenerApiKey() {
        String error = "Screener Access Key was not set in your env variables. Please set SCREENER_API_KEY value.";
        return SystemManager.get("SCREENER_API_KEY", error);
    }
}
