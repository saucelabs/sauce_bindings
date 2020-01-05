package com.saucelabs.simplesauce;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum PageLoadStrategy {
    NONE("none"),
    EAGER("eager"),
    NORMAL("normal");

    @Getter
    private String value;

    private static final class PageLoadStrategyLookup {
        private static final Map<String, String> lookup = new HashMap<String, String>();
    }

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
