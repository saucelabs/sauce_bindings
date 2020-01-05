package com.saucelabs.simplesauce;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum UnhandledPromptBehavior {
    DISMISS("dismiss"),
    ACCEPT("accept"),
    DISMISS_AND_NOTIFY("dismiss and notify"),
    ACCEPT_AND_NOTIFY("accept and notify"),
    IGNORE("ignore");

    @Getter
    private String value;

    private static final class UnhandledPromptBehaviorLookup {
        private static final Map<String, String> lookup = new HashMap<String, String>();
    }

    public static Set keys() {
        return UnhandledPromptBehaviorLookup.lookup.keySet();
    }

    UnhandledPromptBehavior(String value) {
        this.value = value;
        UnhandledPromptBehaviorLookup.lookup.put(value, this.name());
    }

    public static String fromString(String value) {
        return UnhandledPromptBehaviorLookup.lookup.get(value);
    }

    public String toString() {
        return this.value;
    }
}
