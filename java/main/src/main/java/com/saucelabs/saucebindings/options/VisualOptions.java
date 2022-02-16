package com.saucelabs.saucebindings.options;

import com.saucelabs.saucebindings.CITools;
import com.saucelabs.saucebindings.GitManager;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.SystemManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.openqa.selenium.MutableCapabilities;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Accessors(chain = true) @Setter @Getter
public class VisualOptions extends BaseOptions {
    @Setter(AccessLevel.NONE) private SauceOptions sauceOptions;
    private String projectName;
    private String viewportSize;
    private String branch;
    private String baseBranch;
    private Map<String, Object> diffOptions = null;
    private String ignore;
    private Boolean failOnNewStates = null;
    private Boolean alwaysAcceptBaseBranch = null;
    private Boolean disableBranchBaseline = null;
    private Boolean scrollAndStitchScreenshots = null;
    private Boolean disableCORS = null;
    private Boolean iframes = null;
    private Map<String, Object> iframesOptions = null;

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
            "disableCORS",
            "iframes",
            "iframesOptions");

    public VisualOptions(String testName) {
        this(SauceOptions.chrome().setName(testName).build());
    }

    public VisualOptions(SauceOptions options) {
        if (options.sauce().getName() == null) {
            String msg = "Visual Tests Require setting a name in SauceOptions";
            throw new InvalidSauceOptionsArgumentException(msg);
        }

        this.sauceOptions = options;
        capabilityManager = new CapabilityManager(this);
    }

    /**
     * @return instance of MutableCapabilities representing all key value pairs set in SauceOptions
     * @see SauceSession#start()
     */
    public MutableCapabilities toCapabilities() {
        String msg = "Environment Variable or System Property for 'SCREENER_API_KEY' must be provided";
        String sauceVisualApiKey = SystemManager.get("SCREENER_API_KEY", msg);
        capabilities.setCapability("apiKey", sauceVisualApiKey);

        if (projectName == null) {
            projectName = CITools.getBuildName();
        }

        if (branch == null) {
            branch = GitManager.getCurrentBranch();
        }

        capabilityManager.addCapabilities();

        MutableCapabilities toReturn = getSauceOptions().toCapabilities();
        toReturn.setCapability("sauce:visual", capabilities);

        return toReturn;
    }

    /**
     * Use Case is pulling serialized information from JSON/YAML, converting it to a HashMap and passing it in
     * This is a preferred pattern as it avoids conditionals in code
     * Warning: For VisualOptions this adds a hard coded Test Name that needs to be updated
     *
     * @param capabilitiesToMerge a Map object representing key value pairs to convert to capabilities
     */
    public void mergeCapabilities(Map<String, Object> capabilitiesToMerge) {
        for (Map.Entry<String, Object> entry : capabilitiesToMerge.entrySet()) {
            String key = entry.getKey();
            if (key.equals("visual")) {
                ((Map<String, Object>) entry.getValue()).forEach(this::setCapability);
            } else {
                sauceOptions.setCapability(key, entry.getValue());
            }
        }
    }
}
