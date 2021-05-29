package com.saucelabs.saucebindings;

import com.saucelabs.saucebindings.options.VisualOptions;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class VisualSession {
    @Getter @Setter private DataCenter dataCenter = DataCenter.VISUAL;
    @Getter private final VisualOptions visualOptions;
    @Getter @Setter private RemoteWebDriver driver;

    public VisualSession(VisualOptions visualOptions) {
        this.visualOptions = visualOptions;
    }

    public void start(RemoteWebDriver driver, String name) {
        this.driver = driver;
        init(name);
    }

    public void init(String name) {
        driver.executeScript("/*@visual.init*/", name);
    }

    public URL getUrl() {
        try {
            return new URL(dataCenter.getValue());
        } catch (MalformedURLException e) {
            throw new InvalidArgumentException("Invalid URL");
        }
    }

    public Map getResults() {
        return (Map) driver.executeScript("/*@visual.end*/");
    }

    public void takeSnapshot(String name) {
        driver.executeScript("/*@visual.snapshot*/", name);
    }
}
