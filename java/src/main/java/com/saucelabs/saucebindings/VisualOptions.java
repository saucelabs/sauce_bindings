package com.saucelabs.saucebindings;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.openqa.selenium.MutableCapabilities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Accessors(chain = true)
@Setter @Getter
public class VisualOptions extends SauceOptions {
    // Sauce Visual Settings
    // https://screener.io/v2/docs/visual-e2e/visual-options
    private String projectName;
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

    public static final List<String> sauceVisualOptions = Arrays.asList(
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


    public VisualOptions(String projectName) {
        setProjectName(projectName);
    }

    public MutableCapabilities toCapabilities() {
        MutableCapabilities sauceVisualCapabilities = new MutableCapabilities();
        sauceVisualCapabilities.setCapability("apiKey", getScreenerApiKey());
        sauceVisualOptions.forEach((capability) -> {
            addCapabilityIfDefined(sauceVisualCapabilities, capability);
        });

        return sauceVisualCapabilities;
    }

    protected String getScreenerApiKey() {
        String error = "Screener Access Key was not set in your env variables. Please set SCREENER_API_KEY value.";
        return tryToGetVariable("SCREENER_API_KEY", error);
    }

    protected void setVisualCapabilities(Map<String, Object> visualCapabilities) {
        visualCapabilities.forEach(this::setVisualCapability);
    }

    private void setVisualCapability(String key, Object value) {
        try {
            Class<?> type = VisualOptions.class.getDeclaredField(key).getType();
            String setter = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
            Method method = VisualOptions.class.getDeclaredMethod(setter, type);
            method.invoke(this, value);
        } catch (NoSuchFieldException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
