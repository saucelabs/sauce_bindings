package com.saucelabs.saucebindings;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum DeviceType {
    PHONE("phone"),
    TABLET("tablet");

    @Getter
    private final String value;

    private static final class DeviceTypeLookup {
        private static final Map<String, String> lookup = new HashMap<String, String>();
    }

    public static Set keys() {
        return DeviceTypeLookup.lookup.keySet();
    }

    DeviceType(String value) {
        this.value = value;
        DeviceTypeLookup.lookup.put(value, this.name());
    }

    public static String fromString(String value) {
        return DeviceTypeLookup.lookup.get(value);
    }

    public String toString() {
        return this.value;
    }
}
