package com.saucelabs.saucebindings;

import com.saucelabs.saucebindings.options.SauceOptions;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class VisualSession extends SauceSession {
    public VisualSession(String testName) {
        super(SauceOptions.chrome().setName(testName).build());
    }

    @Override
    public URL getSauceUrl() {
        try {
            return new URL("https://hub.screener.io/wd/hub");
        } catch (MalformedURLException e) {
            throw new InvalidArgumentException("Invalid URL", e);
        }
    }

    public RemoteWebDriver start() {
        MutableCapabilities capabilities = getSauceOptions().toCapabilities();

        String sauceVisualApiKey = SystemManager.get("SCREENER_API_KEY");
        Map<String, Object> visual = new HashMap<>();
        visual.put("apiKey", sauceVisualApiKey);
        visual.put("projectName", new CITools().getBuildName());
        visual.put("branch", "_default_");

        capabilities.setCapability("sauce:visual", visual);

        this.driver = createRemoteWebDriver(getSauceUrl(), capabilities);
        driver.executeScript("/*@visual.init*/", getSauceOptions().sauce().getName());

        return driver;
    }

    public void takeSnapshot(String name) {
        driver.executeScript("/*@visual.snapshot*/", name);
    }

    public Map<String, Object> getResults() {
        return (Map<String, Object>) driver.executeScript("/*@visual.end*/");
    }

    public void stop() {
        System.out.println("\n Visual Results: " + getResults());
        super.quit();
    }
}
