package com.saucelabs.saucebindings;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum DeviceOrientation {
    LANDSCAPE("landscape"),
    PORTRAIT("portrait");

    @Getter
    private final String value;

    private static final class DeviceOrientationLookup {
        private static final Map<String, String> lookup = new HashMap<String, String>();
    }

    public static Set keys() {
        return DeviceOrientationLookup.lookup.keySet();
    }

    DeviceOrientation(String value) {
        this.value = value;
        DeviceOrientationLookup.lookup.put(value, this.name());
    }

    public static String fromString(String value) {
        return DeviceOrientationLookup.lookup.get(value);
    }

    public String toString() {
        return this.value;
    }
}
