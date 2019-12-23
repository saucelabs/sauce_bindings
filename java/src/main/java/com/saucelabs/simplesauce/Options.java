package com.saucelabs.simplesauce;

import java.util.List;

public class Options {
    public static final List<String> w3c = List.of(
            "browserName",
            "browserVersion",
            "platformName",
            "pageLoadStrategy",
            "acceptInsecureCerts",
            "proxy",
            "setWindowRect",
            "timeouts",
            "strictFileInteractability",
            "unhandledPromptBehavior");

    public static final List<String> sauce = List.of(
            "avoidProxy",
            "build",
            "capturePerformance",
            "chromedriverVersion",
            "commandTimeout",
            "customData",
            "extendedDebugging",
            "idleTimeout",
            "iedriverVersion",
            "maxDuration",
            "name",
            "parentTunnel",
            "prerun",
            "priority",
            "recordLogs",
            "recordScreenshots",
            "recordVideo",
            "screenResolution",
            "seleniumVersion",
            "tags",
            "timeZone",
            "tunnelIdentifier",
            "videoUploadOnPass");

}
