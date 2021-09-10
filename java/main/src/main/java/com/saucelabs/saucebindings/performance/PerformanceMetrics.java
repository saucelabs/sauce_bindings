package com.saucelabs.saucebindings.performance;

import lombok.Getter;

import java.util.Map;

@Getter
public class PerformanceMetrics {
    private final Long load;
    private final Long speedIndex;
    private final Long firstInteractive;
    private final Long firstVisualChange;
    private final Long lastVisualChange;
    private final Long firstMeaningfulPaint;
    private final Long firstCPUIdle;
    private final Long timeToFirstByte;
    private final Long firstPaint;
    private final Long estimatedInputLatency;
    private final Long firstContentfulPaint;
    private final Long totalBlockingTime;
    private final Double score;
    private final Long domContentLoaded;
    private final Long cumulativeLayoutShift;
    private final Long serverResponseTime;
    private final Long largestContentfulPaint;
    private final Map<String, Object> rawData;

    /**
     * @param performance Map of the raw results from Sauce Labs when obtaining log of performance metrics
     */
    public PerformanceMetrics(Map<String, Object> performance) {
        this.rawData = performance;
        this.load = (Long) performance.get("load");
        this.speedIndex = (Long) performance.get("speedIndex");
        this.firstInteractive = (Long) performance.get("firstInteractive");
        this.firstVisualChange = (Long) performance.get("firstVisualChange");
        this.lastVisualChange = (Long) performance.get("lastVisualChange");
        this.firstMeaningfulPaint = (Long) performance.get("firstMeaningfulPaint");
        this.firstCPUIdle = (Long) performance.get("firstCPUIdle");
        this.timeToFirstByte = (Long) performance.get("timeToFirstByte");
        this.firstPaint = (Long) performance.get("firstPaint");
        this.estimatedInputLatency = (Long) performance.get("estimatedInputLatency");
        this.firstContentfulPaint = (Long) performance.get("firstContentfulPaint");
        this.totalBlockingTime = (Long) performance.get("totalBlockingTime");
        this.domContentLoaded = (Long) performance.get("domContentLoaded");
        this.cumulativeLayoutShift = (Long) performance.get("cumulativeLayoutShift");
        this.serverResponseTime = (Long) performance.get("serverResponseTime");
        this.largestContentfulPaint = (Long) performance.get("largestContentfulPaint");

        if (performance.get("score").getClass().equals(Long.class)) {
            this.score = ((Long) performance.get("score")).doubleValue();
        } else {
            this.score = (Double) performance.get("score");
        }
    }
}
