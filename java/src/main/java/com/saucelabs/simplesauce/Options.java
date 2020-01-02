package com.saucelabs.simplesauce;

import lombok.Getter;

import java.util.*;

public class Options {
    public static final List<String> primaryEnum = List.of(
            "browserName",
            "jobVisibility",
            "pageLoadStrategy",
            "platformName",
            "timeouts",
            "unhandledPromptBehavior"
    );

    public static final List<String> secondaryEnum = List.of(
            "prerun",
            "timeouts"
    );

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

    private static final class BrowserLookup {
        private static final Map<String, String> lookup = new HashMap<String, String>();
    }

    private static final class PlatformLookup {
        private static final Map<String, String> lookup = new HashMap<String, String>();
    }

    private static final class PageLoadStrategyLookup {
        private static final Map<String, String> lookup = new HashMap<String, String>();
    }

    private static final class TimeoutsLookup {
        private static final Map<String, String> lookup = new HashMap<String, String>();
    }

    private static final class UnhandledPromptBehaviorLookup {
        private static final Map<String, String> lookup = new HashMap<String, String>();
    }

    private static final class JobVisibilityLookup {
        private static final Map<String, String> lookup = new HashMap<String, String>();
    }

    private static final class PreRunLookup {
        private static final Map<String, String> lookup = new HashMap<String, String>();
    }

    public enum PageLoadStrategy {
        NONE("none"),
        EAGER("eager"),
        NORMAL("normal");

        @Getter private String value;

        public static Set keys() {
            return PageLoadStrategyLookup.lookup.keySet();
        }

        PageLoadStrategy(String value) {
            this.value = value;
            PageLoadStrategyLookup.lookup.put(value, this.name());
        }

        public static String fromString(String value) {
            return PageLoadStrategyLookup.lookup.get(value);
        }

        public String toString() {
            return this.value;
        }
    }

    public enum UnhandledPromptBehavior {
        DISMISS("dismiss"),
        ACCEPT("accept"),
        DISMISS_AND_NOTIFY("dismiss and notify"),
        ACCEPT_AND_NOTIFY("accept and notify"),
        IGNORE("ignore");

        @Getter private String value;

        public static Set keys() {
            return UnhandledPromptBehaviorLookup.lookup.keySet();
        }

        UnhandledPromptBehavior(String value) {
            this.value = value;
            UnhandledPromptBehaviorLookup.lookup.put(value, this.name());
        }

        public static String fromString(String value) {
            return UnhandledPromptBehaviorLookup.lookup.get(value);
        }

        public String toString() {
            return this.value;
        }
    }

    public enum Prerun {
        EXECUTABLE("executable"),
        ARGS("args"),
        BACKGROUND("background"),
        TIMEOUT("timeout");

        @Getter private String value;

        public static Set keys() {
            return PreRunLookup.lookup.keySet();
        }

        Prerun(String value) {
            this.value = value;
            PreRunLookup.lookup.put(value, this.name());
        }

        public static String fromString(String value) {
            return PreRunLookup.lookup.get(value);
        }

        public String toString() {
            return this.value;
        }
    }

    public enum JobVisibility {
        PUBLIC("public"),
        PUBLIC_RESTRICTED("public restricted"),
        SHARE("share"),
        TEAM("team"),
        PRIVATE("private");

        @Getter private String value;

        public static Set keys() {
            return JobVisibilityLookup.lookup.keySet();
        }

        JobVisibility(String value) {
            this.value = value;
            JobVisibilityLookup.lookup.put(value, this.name());
        }

        public static String fromString(String value) {
            return JobVisibilityLookup.lookup.get(value);
        }

        public String toString() {
            return this.value;
        }
    }

    public enum Browser {
        CHROME("chrome"),
        INTERNET_EXPLORER("internet explorer"),
        EDGE("MicrosoftEdge"),
        SAFARI("safari"),
        FIREFOX("firefox");

        @Getter private final String value;

        public static Set keys() {
            return BrowserLookup.lookup.keySet();
        }

        Browser(String value) {
            this.value = value;
            BrowserLookup.lookup.put(value, this.name());
        }

        public static String fromString(String value) {
            return BrowserLookup.lookup.get(value);
        }

        public String toString() {
            return this.value;
        }
    }

    public enum Timeouts {
        IMPLICIT("implicit"),
        PAGE_LOAD("pageLoad"),
        SCRIPT("script");

        @Getter private final String value;

        public static Set keys() {
            return TimeoutsLookup.lookup.keySet();
        }

        Timeouts(String value) {
            this.value = value;
            TimeoutsLookup.lookup.put(value, this.name());
        }

        public static String fromString(String value) {
            return TimeoutsLookup.lookup.get(value);
        }

        public String toString() {
            return this.value;
        }
    }

    public enum Platform {
        WINDOWS_10("Windows 10"),
        WINDOWS_8_1("Windows 8.1"),
        WINDOWS_8("Windows 8"),
        MAC_MOJAVE("macOS 10.14"),
        MAC_HIGH_SIERRA("macOS 10.13"),
        MAC_SIERRA("macOS 10.12"),
        MAC_EL_CAPITAN("OS X 10.11"),
        MAC_YOSEMITE("OS X 10.10");

        @Getter
        private final String value;

        public static Set keys() {
            return PlatformLookup.lookup.keySet();
        }

        Platform(String value) {
            this.value = value;
            PlatformLookup.lookup.put(value, this.name());
        }

        public static String fromString(String value) {
            return PlatformLookup.lookup.get(value);
        }

        public String toString() {
            return this.value;
        }
    }
}
