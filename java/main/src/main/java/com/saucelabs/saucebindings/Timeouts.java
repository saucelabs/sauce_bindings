package com.saucelabs.saucebindings;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Valid W3C timeout values.
 *
 * @see <a href="https://docs.saucelabs.com/dev/test-configuration-options/#timeouts">timeouts</a>
 */
public enum Timeouts {

    IMPLICIT("implicit"),
    PAGE_LOAD("pageLoad"),
    SCRIPT("script");

    @Getter
    private final String value;

    private static final class TimeoutsLookup {
        private static final Map<String, String> lookup = new HashMap<>();
    }

    public static Set<String> keys() {
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

