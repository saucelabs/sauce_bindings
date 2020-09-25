package com.saucelabs.saucebindings;

import com.saucelabs.saucebindings.visual.SauceVisualOptions;
import lombok.Getter;
import lombok.Setter;
import lombok.var;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class SauceVisualSession {
    private SauceVisualOptions visualOptions;
    private RemoteWebDriver driver;

    public SauceVisualSession() {
    }

    public SauceVisualSession(SauceVisualOptions sauceVisualOptions) {
        visualOptions = sauceVisualOptions;
    }

    public RemoteWebDriver start() {
        visualOptions = new SauceVisualOptions();
//        visualOptions.setCapability("projectName", "visual-e2e-test");
//        visualOptions.setCapability("viewportSize", "1280x1024");

        SauceOptions sauceOptions = new SauceOptions();
        sauceOptions.setVisualTesting(visualOptions);

        driver = createRemoteWebDriver(getHubUrl(), sauceOptions.toCapabilities());
        return driver;
    }

    protected RemoteWebDriver createRemoteWebDriver(URL url, MutableCapabilities capabilities) {
        return new RemoteWebDriver(url, capabilities);
    }

    @Getter
    @Setter
    private String hubUrl = "https://hub.screener.io/wd/hub";

    public URL getHubUrl() {
        try {
            return new URL(hubUrl);
        } catch (MalformedURLException e) {
            throw new InvalidArgumentException("Invalid screener hub url");
        }
    }

    public void setTestName(String testName) {
        ((JavascriptExecutor)driver).executeScript("/*@visual.init*/",
                testName);
    }

    public void takeSnapshot(String snapshotName) {
        ((JavascriptExecutor)driver).executeScript("/*@visual.snapshot*/",
                snapshotName);
    }

    public Boolean stop() {
        var response = (Map<String, Object>)
                ((JavascriptExecutor)driver).executeScript("/*@visual.end*/");
        return (Boolean) response.get("passed");
    }
}
