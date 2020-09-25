package com.saucelabs.saucebindings.visual;

import com.saucelabs.saucebindings.SauceEnvironmentVariablesNotSetException;
import org.openqa.selenium.MutableCapabilities;

public class SauceVisualOptions {
    private final MutableCapabilities visualCapabilities;
    private String visualProjectName;
    private String viewportSize;

    public SauceVisualOptions(){
        visualCapabilities = new MutableCapabilities();
        visualCapabilities.setCapability("apiKey", getScreenerApiKey());
    }

    private String getScreenerApiKey() {
        String key = "SCREENER_API_KEY";
        if (getSystemProperty(key) != null) {
            return getSystemProperty(key);
        } else if (getEnvironmentVariable(key) != null) {
            return getEnvironmentVariable(key);
        } else {
            throw new SauceEnvironmentVariablesNotSetException(
                    "Screener Access Key was not set in your env variables." +
                            "Please set " + key + " value.");
        }
    }

    protected String getSystemProperty(String key) {
        return System.getProperty(key);
    }
    protected String getEnvironmentVariable(String key) {
        return System.getenv(key);
    }

    public void setProjectName(String projectName) {
        visualProjectName = projectName;
    }

    public void setViewportSize(String viewport) {
        viewportSize = viewport;
    }
}
