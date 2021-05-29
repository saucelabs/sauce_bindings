package com.saucelabs.saucebindings;

import com.saucelabs.saucebindings.options.VisualOptions;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.remote.RemoteWebDriver;

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

    public Map getResults() {
        return (Map) driver.executeScript("/*@visual.end*/");
    }

    public void takeSnapshot(String name) {
        driver.executeScript("/*@visual.snapshot*/", name);
    }
}
