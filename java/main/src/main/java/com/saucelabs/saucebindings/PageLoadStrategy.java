package com.saucelabs.saucebindings;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Valid Page Load Strategies.
 *
 * @see <a href="https://docs.saucelabs.com/dev/test-configuration-options/#pageloadstrategy">pageLoadStrategy</a>
 */
public enum PageLoadStrategy {
    NONE("none"),
    EAGER("eager"),
    NORMAL("normal");

    @Getter
    private final String value;

    private static final class PageLoadStrategyLookup {
        private static final Map<String, String> lookup = new HashMap<>();
    }

    public static Set<String> keys() {
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
