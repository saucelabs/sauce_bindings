package com.saucelabs.simplesauce;

import com.saucelabs.simplesauce.enums.MacVersion;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.remote.BrowserType;

public class SauceOptions {
    @Getter @Setter private String browserName = BrowserType.CHROME;
    @Getter @Setter private String browserVersion = "latest";
    @Getter @Setter private String operatingSystem = Platforms.windowsLatest().getOsVersion();

    public SauceOptions withChrome() {
        browserName = BrowserType.CHROME;
        return this;
    }

    public SauceOptions withSafari() {
        browserName = BrowserType.SAFARI;
        return this;
    }

    public SauceOptions withSafari(SafariVersion version) {
        browserName = BrowserType.SAFARI;
        browserVersion = version.getVersion();
        return this;
    }

    public SauceOptions withLinux() {
        operatingSystem = "Linux";
        return this;
    }

    public SauceOptions withWindows10() {
        operatingSystem = "windows 10";
        return this;
    }
    public SauceOptions withWindows8_1() {
        operatingSystem = "Windows 8.1";
        return this;
    }
    public SauceOptions withWindows8() {
        operatingSystem = "Windows 8";
        return this;
    }

    public SauceOptions withWindows7() {
        operatingSystem = "Windows 7";
        return this;
    }

    public SauceOptions withEdge() {
        return withEdge18();
    }

    public SauceOptions withEdge18() {
        return withEdge(EdgeVersion._18);
    }
    public SauceOptions withEdge17() {
        return withEdge(EdgeVersion._17);
    }

    public SauceOptions withEdge16() {
        return withEdge(EdgeVersion._16);
    }

    public SauceOptions withEdge15() {
        return withEdge(EdgeVersion._15);
    }

    public SauceOptions withEdge14() {
        return withEdge(EdgeVersion._14);
    }

    public SauceOptions withEdge13() {
        return withEdge(EdgeVersion._13);
    }

    public SauceOptions withEdge(EdgeVersion version) {
        browserName = "MicrosoftEdge";
        browserVersion = version.getVersion();
        return this;
    }

    public SauceOptions withFirefox() {
        browserName = "firefox";
        return this;
    }

    public SauceOptions withIE(IEVersion version) {
        browserName = "internet explorer";
        browserVersion = version.getVersion();
        return this;
    }

    public SauceOptions withIE() {
        browserName = "internet explorer";
        return this;
    }

    public SauceOptions withMac(MacVersion macVersion) {
        operatingSystem = macVersion.label;
        return this;
    }
}
