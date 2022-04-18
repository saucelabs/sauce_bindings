package com.saucelabs.saucebindings;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Valid W3C unhandled Prompt Behavior values.
 *
 * @see <a href="https://docs.saucelabs.com/dev/test-configuration-options/#unhandledpromptbehavior">unhandledPromptBehavior</a>
 */
public enum UnhandledPromptBehavior {
    DISMISS("dismiss"),
    ACCEPT("accept"),
    DISMISS_AND_NOTIFY("dismiss and notify"),
    ACCEPT_AND_NOTIFY("accept and notify"),
    IGNORE("ignore");

    @Getter
    private final String value;

    private static final class UnhandledPromptBehaviorLookup {
        private static final Map<String, String> lookup = new HashMap<>();
    }

    public static Set<String> keys() {
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
