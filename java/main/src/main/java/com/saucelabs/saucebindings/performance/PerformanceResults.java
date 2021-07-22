package com.saucelabs.saucebindings.performance;

import java.util.Map;

public class PerformanceResults {
    private String reason;
    private boolean result;
    private Map details;

    public PerformanceResults(Map<String, Object> performance) {
        this.reason = (String) performance.get("reason");
        this.result = performance.get("result").equals("pass");
        this.details = (Map<String, Object>) performance.get("details");
    }

    public boolean isPassed() {
        return result;
    }

    public String getReason() {
        return this.reason;
    }

    public Map getDetails() {
        return details;
    }
}
