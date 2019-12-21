package com.saucelabs.simplesauce;

public enum OptionsEnums {
    a;

    public enum w3c {
        browserName, browserVersion, platformName, pageLoadStrategy,
        acceptInsecureCerts, proxy, setWindowRect, timeouts, strictFileInteractability,
        unhandledPromptBehavior
    }

    public enum sauce {
        accessKey, avoidProxy, build, captureHtml, capturePerformance, chromedriverVersion, commandTimeout,
        crmuxdriverVersion, customData, disablePopupHandler, extendedDebugging, firefoxAdapterVersion,
        firefoxProfileUrl, idleTimeout, iedriverVersion, maxDuration, name, parentTunnel, passed, prerun,
        preventRequeu, priority, proxyHost, Public, recordLogs, recordStreenshots, recordVideo,
        restrictedPublicInfo, screenResolution, seleniumVersion, source, tags, timeZone, tunnelIdentifier,
        username, videoUploadOnPass;
    }
}
