package com.saucelabs.saucebindings.performance;

import lombok.Getter;

import java.util.Map;

@Getter
public class PerformanceResults {
    private final String reason;
    private final boolean passed;
    private final Map<String, Object> details;

    /**
     * @param performance Map of the raw results from Sauce Labs when executing performance result
     */
    public PerformanceResults(Map<String, Object> performance) {
        this.reason = (String) performance.get("reason");
        this.passed = performance.get("result").equals("pass");
        this.details = (Map<String, Object>) performance.get("details");
    }
}
