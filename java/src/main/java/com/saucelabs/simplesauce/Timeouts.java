package com.saucelabs.simplesauce;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum Timeouts {

    IMPLICIT("implicit"),
    PAGE_LOAD("pageLoad"),
    SCRIPT("script");

    @Getter
    private final String value;

    private static final class TimeoutsLookup {
        private static final Map<String, String> lookup = new HashMap<String, String>();
    }

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

