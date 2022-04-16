package com.saucelabs.saucebindings;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum Prerun {
    EXECUTABLE("executable"),
    ARGS("args"),
    BACKGROUND("background"),
    TIMEOUT("timeout");

    @Getter
    private final String value;

    private static final class PrerunLookup {
        private static final Map<String, String> lookup = new HashMap<>();
    }

    public static Set<String> keys() {
        return PrerunLookup.lookup.keySet();
    }

    Prerun(String value) {
        this.value = value;
        PrerunLookup.lookup.put(value, this.name());
    }

    public static String fromString(String value) {
        return PrerunLookup.lookup.get(value);
    }

    public String toString() {
        return this.value;
    }
}
