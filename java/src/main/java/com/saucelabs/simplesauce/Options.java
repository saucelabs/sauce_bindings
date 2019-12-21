package com.saucelabs.simplesauce;

public enum Options {
    a;

    public enum W3C {
        browserName, browserVersion, platformName, pageLoadStrategy,
        acceptInsecureCerts, proxy, setWindowRect, timeouts, strictFileInteractability,
        unhandledPromptBehavior
    }

    public enum SAUCE {
        avoidProxy, build, capturePerformance, chromedriverVersion, commandTimeout,
        customData, extendedDebugging, idleTimeout, iedriverVersion, maxDuration, name, parentTunnel, prerun,
        priority, recordLogs, recordScreenshots, recordVideo, screenResolution, seleniumVersion,
        tags, timeZone, tunnelIdentifier, videoUploadOnPass;
    }
}
