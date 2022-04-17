package com.saucelabs.saucebindings;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// Supports traditional values, selenium.Platform enum and return values from platform API endpoint
// TODO - remove the weird Windows names when require Selenium > v4.1.3
public enum SaucePlatform {
    LINUX("Linux"),
    WINDOWS_11("Windows 11"),
    WINDOWS_10("Windows 10", "WIN10"),
    WINDOWS_8_1("Windows 8.1", "WIN8_1"),
    WINDOWS_8("Windows 8", "WIN8"),
    MAC_MONTEREY("macOS 12", "macOS 12.0", "Mac 12"),
    MAC_BIG_SUR("macOS 11", "macOS 11.0", "Mac 11"),
    MAC_CATALINA("macOS 10.15", "Mac 10.15"),
    MAC_MOJAVE("macOS 10.14", "Mac 10.14"),
    MAC_HIGH_SIERRA("macOS 10.13", "Mac 10.13"),
    MAC_SIERRA("macOS 10.12", "Mac 10.12"),
    MAC_EL_CAPITAN("OS X 10.11", "Mac 10.11"),
    MAC_YOSEMITE("OS X 10.10", "Mac 10.10");

    @Getter
    private final String[] values;

    private static final class PlatformLookup {
        private static final Map<String, String> lookup = new HashMap<>();
    }

    public static Set<String> keys() {
        return PlatformLookup.lookup.keySet();
    }

    SaucePlatform(String... values) {
        this.values = values;
        for(String str: values) {
            PlatformLookup.lookup.put(str, this.name());
        }
    }

    public static String fromString(String value) {
        return PlatformLookup.lookup.get(value);
    }

    /**
     * Whether the platform is on an Apple Mac OS.
     *
     * @return true if platform is OS X or macOS
     */
    public boolean isMac() {
        return Arrays.stream(this.values).anyMatch(val -> val.contains("macOS") || val.contains("OS X") || val.contains("Mac"));
    }

    /**
     * Whether the platform is Microsoft Windows.
     *
     * @return true if platform is Windows OS
     */
    public boolean isWindows() {
        return Arrays.stream(this.values).anyMatch(val -> val.contains("Windows"));
    }

    public String toString() {
        return this.values[0];
    }
}
