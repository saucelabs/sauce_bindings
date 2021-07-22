package com.saucelabs.saucebindings.performance;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.HashMap;
import java.util.Map;

public class Performance {
    private RemoteWebDriver driver;
    private String testName;

    public Performance(RemoteWebDriver driver, String testName) {
        this.testName = testName;
        this.driver = driver;
    }

    public PerformanceResults getResults() {
        HashMap<String, Object> perf = new HashMap<>();
        perf.put("name", testName);
        Map<String, Object> performance = (Map<String, Object>) driver.executeScript("sauce:performance", perf);
        return new PerformanceResults(performance);
    }

    public PerformanceMetrics getMetrics() {
        HashMap<String, Object> metricsLog = new HashMap<>();
        metricsLog.put("type", "sauce:performance");

        Map<String, Object> metrics = (Map<String, Object>) driver.executeScript("sauce:log", metricsLog);
        return new PerformanceMetrics(metrics);
    }

    public void enable() {
        driver.executeScript("sauce:performanceEnable");
    }

    public void disable() {
        driver.executeScript("sauce:performanceDisable");
    }

}
