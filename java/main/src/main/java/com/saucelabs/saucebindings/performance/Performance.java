package com.saucelabs.saucebindings.performance;

import com.saucelabs.saucebindings.SauceSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Performance {
    private final SauceSession session;

    /**
     * This class is intended to be initialized via `SauceSession.performance()`
     *
     * @param session
     * @see SauceSession#performance()
     */
    public Performance(SauceSession session) {
        this.session = session;
    }

    /**
     * This measures the performance output against a baseline of previously accepted performance values
     *
     * @return PerformanceResults is a wrapper of the HashMap results to make it easier to work with them
     * @see <a href="https://docs.saucelabs.com/performance/transitions#implementing-the-performance-command-assertion">Implementing the Performance Command Assertion</a>
     */
    public PerformanceResults getResults() {
        HashMap<String, Object> perf = new HashMap<>();
        perf.put("name", session.getSauceOptions().sauce().getName());
        Map<String, Object> performance = (Map<String, Object>) session.getDriver().executeScript("sauce:performance", perf);
        return new PerformanceResults(performance);
    }

    /**
     * This measures the performance output of a specific metric against a baseline of previously accepted performance values
     *
     * @param metric A String value for which performance metrics to evaluate
     * @return PerformanceResults is a wrapper of the HashMap results to make it easier to work with them
     * @see <a href="https://docs.saucelabs.com/performance/transitions#implementing-the-performance-command-assertion">Implementing the Performance Command Assertion</a>
     * @see <a href="https://docs.saucelabs.com/performance/one-page/index.html#metric-values">Metric Values</a>
     */
    public PerformanceResults getResults(String metric) {
        List<String> metrics = new ArrayList<>();
        metrics.add(metric);
        return getResults(metrics);
    }

    /**
     * This measures the performance output of a specific list of metrics against a baseline of previously accepted performance values
     *
     * @param metrics A List of values for which performance metrics to evaluate
     * @return PerformanceResults is a wrapper of the HashMap results to make it easier to work with them
     * @see <a href="https://docs.saucelabs.com/performance/transitions#implementing-the-performance-command-assertion">Implementing the Performance Command Assertion</a>
     * @see <a href="https://docs.saucelabs.com/performance/one-page/index.html#metric-values">Metric Values</a>
     */
    public PerformanceResults getResults(List<String> metrics) {
        HashMap<String, Object> perf = new HashMap<>();
        perf.put("name", session.getSauceOptions().sauce().getName());
        perf.put("metrics", metrics);
        Map<String, Object> performance = (Map<String, Object>) session.getDriver().executeScript("sauce:performance", perf);
        return new PerformanceResults(performance);
    }

    /**
     * @return JenkinsResults is a wrapper of the HashMap results to make it easier to work with them
     * @see <a href="https://docs.saucelabs.com/performance/motion">Measuring On-Page Motion Effects</a>
     */
    public JankinessResults getJankinessResults() {
        Map<String, Object> jankiness = (Map<String, Object>) session.getDriver().executeScript("sauce:jankinessCheck");
        return new JankinessResults(jankiness);
    }

    /**
     * This obtains information on all of the collected metrics to allow making assertions on one or more values
     *
     * @return PerformanceMetrics is a wrapper of the HashMap results to make it easier to work with them
     * @see <a href="https://docs.saucelabs.com/performance/transitions#logging-performance-results">Logging Performance Results</a>
     */
    public PerformanceMetrics getMetrics() {
        HashMap<String, Object> metricsLog = new HashMap<>();
        metricsLog.put("type", "sauce:performance");

        Map<String, Object> metrics = (Map<String, Object>) session.getDriver().executeScript("sauce:log", metricsLog);
        return new PerformanceMetrics(metrics);
    }

    /**
     * Turn on recording of metrics during session
     * @see <a href="https://docs.saucelabs.com/performance/transitions#target-specific-urls-in-a-script">Target Specific URLs in a Script</a>
     */
    public void enable() {
        session.getDriver().executeScript("sauce:performanceEnable");
    }

    /**
     * Turn off recording of metrics during session
     * @see <a href="https://docs.saucelabs.com/performance/transitions#target-specific-urls-in-a-script">Target Specific URLs in a Script</a>
     */
    public void disable() {
        session.getDriver().executeScript("sauce:performanceDisable");
    }

    /**
     * The map used as input will typically be converted from serialized information stored in a yaml or JSON file
     * @param budget Map of Maps with the URL as the keys of outer maps, the metric as the keys of the nested maps and the maximum value
     *               of the metric as its value
     * @return A Map of failures if any, including the failing metric and its value
     * @see <a href="https://docs.saucelabs.com/performance/transitions#defining-a-performance-budget">Defining a Performance Budget</a>
     */
    public Map<String, Object> getBudgetFailures(Map<String, Map<String, Object>> budget) {
        Map<String, Object> violations = new HashMap<>();

        budget.forEach((url, values) -> {
            session.getDriver().get(url);
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
