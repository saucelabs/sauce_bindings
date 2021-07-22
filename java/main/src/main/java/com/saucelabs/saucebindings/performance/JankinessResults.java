package com.saucelabs.saucebindings.performance;

import lombok.Getter;

import java.util.Map;

@Getter
public class JankinessResults {
    private final String url;
    private final Map<String, Object> metrics;
    private final Map<String, Object> diagnostics;
    private final Double score;
    private final String type;

    /**
     * @param jankiness Map of the raw results from Sauce Labs when executing Jankiness Check
     * @see <a href="https://docs.saucelabs.com/performance/motion">Measuring On-Page Motion Effects</a>
     */
    public JankinessResults(Map<String, Object> jankiness) {
        this.url = (String) jankiness.get("url");
        this.metrics = (Map) jankiness.get("metrics");
        this.diagnostics = (Map) jankiness.get("diagnostics");
        this.score = (Double) jankiness.get("score");
        this.type = (String) jankiness.get("type");
    }
}
