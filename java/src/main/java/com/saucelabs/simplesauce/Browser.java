package com.saucelabs.simplesauce;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum Browser {
    CHROME("chrome"),
    INTERNET_EXPLORER("internet explorer"),
    EDGE("MicrosoftEdge"),
    SAFARI("safari"),
    FIREFOX("firefox");

    @Getter
    private final String value;

    private static final class BrowserLookup {
        private static final Map<String, String> lookup = new HashMap<String, String>();
    }

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

