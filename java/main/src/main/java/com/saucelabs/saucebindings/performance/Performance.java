package com.saucelabs.saucebindings.performance;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Performance {
    private final RemoteWebDriver driver;
    private final String testName;

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

    public PerformanceResults getResults(String metric) {
        List<String> metrics = new ArrayList<>();
        metrics.add(metric);
        return getResults(metrics);
    }

    public PerformanceResults getResults(List<String> metrics) {
        HashMap<String, Object> perf = new HashMap<>();
        perf.put("name", testName);
        perf.put("metrics", metrics);
        Map<String, Object> performance = (Map<String, Object>) driver.executeScript("sauce:performance", perf);
        return new PerformanceResults(performance);
    }

    public JankinessResults getJankinessResults() {
        Map<String, Object> jankiness = (Map<String, Object>) driver.executeScript("sauce:jankinessCheck");
        return new JankinessResults(jankiness);
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

    // https://docs.saucelabs.com/performance/transitions#defining-a-performance-budget
    // Can pull this from yaml or json
    public Map<String, Object> getBudgetFailures(Map<String, Map<String, Object>> budget) {
        Map<String, Object> violations = new HashMap<>();

        budget.forEach((url, values) -> {
            driver.get(url);
            PerformanceMetrics metrics = getMetrics();
            values.forEach((metric, value) -> {
                if ("score".equals(metric)) {
                    Double score = (Double) metrics.getRawData().get(metric);
                    if (score < (Double) value) {
                        violations.put(metric, value);
                    }
                } else if (((Number) metrics.getRawData().get(metric)).longValue() > ((Number) value).longValue()) {
                    violations.put(metric, value);
                }
            });
        });
        return violations;
    }
}
