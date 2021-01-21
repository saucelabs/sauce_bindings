package com.saucelabs.saucebindings.options.capabilities;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum ScreenResolution {
    SVGA("800x600"),
    XGA("1024x768"),
    XGA_PLUS("1152x864"),
    WXGA("1280x768"),
    WXGA_PLUS("1200x800"),
    SXGA_MINUS("1200x960"),
    SXGA("1200x1024"),
    SXGA_PLUS("1400x1050"),
    WSXGA("1440x900"),
    UXGA("1600x1200"),
    WSXGA_PLUS("1680x1050"),
    FHD("1920x1080"),
    WUXGA("1920x1200"),
    WQXGA("1920x1200");

    @Getter
    private final String value;

    private static final class ResolutionLookup {
        private static final Map<String, String> lookup = new HashMap<String, String>();
    }

    public static Set keys() {
        return ResolutionLookup.lookup.keySet();
    }

    ScreenResolution(String value) {
        this.value = value;
        ResolutionLookup.lookup.put(value, this.name());
    }

    public static String fromString(String value) {
        return ResolutionLookup.lookup.get(value);
    }

    public String toString() {
        return this.value;
    }
}
