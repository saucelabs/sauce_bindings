package com.saucelabs.saucebindings;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum SauceAutomationName {
    UIAUTOMATOR2("UiAutomator2"),
    APPIUM("Appium");

    @Getter
    private final String value;

    private static final class AutomationNameLookup {
        private static final Map<String, String> lookup = new HashMap<String, String>();
    }

    public static Set keys() {
        return AutomationNameLookup.lookup.keySet();
    }

    SauceAutomationName(String value) {
        this.value = value;
        AutomationNameLookup.lookup.put(value, this.name());
    }

    public static String fromString(String value) {
        return AutomationNameLookup.lookup.get(value);
    }

    public String toString() {
        return this.value;
    }
}
