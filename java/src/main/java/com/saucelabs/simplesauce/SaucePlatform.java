package com.saucelabs.simplesauce;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum SaucePlatform {
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

    private static final class PlatformLookup {
        private static final Map<String, String> lookup = new HashMap<String, String>();
    }

    public static Set keys() {
        return PlatformLookup.lookup.keySet();
    }

    SaucePlatform(String value) {
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
