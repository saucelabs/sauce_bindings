package com.saucelabs.saucebindings;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Valid Platform Options.
 * First item in list gets sent to Sauce.
 * Additional items in list matches Selenium Platform enum, and Sauce Labs platform API endpoint values
 * TODO: can remove the weird Windows names when require Selenium > v4.1.3
 *
 * @see <a href="https://docs.saucelabs.com/dev/test-configuration-options/#platformname">platformName</a>
 */
public enum SaucePlatform {
    LINUX("Linux"),
    WINDOWS_11("Windows 11"),
    WINDOWS_10("Windows 10"),
    WINDOWS_8_1("Windows 8.1"),
    WINDOWS_8("Windows 8"),
    MAC_MONTEREY("macOS 12"),
    MAC_BIG_SUR("macOS 11"),
    MAC_CATALINA("macOS 10.15"),
    MAC_MOJAVE("macOS 10.14"),
    MAC_HIGH_SIERRA("macOS 10.13"),
    MAC_SIERRA("macOS 10.12"),
    MAC_EL_CAPITAN("OS X 10.11"),
    MAC_YOSEMITE("OS X 10.10");

    @Getter
    private final String[] values;

    private static final class PlatformLookup {
        private static final Map<String, String> lookup = new HashMap<>();
    }

    public static Set<String> keys() {
        return PlatformLookup.lookup.keySet();
    }

    SaucePlatform(String... values) {
        this.values = values;
        Arrays.stream(values).forEach(str -> PlatformLookup.lookup.put(str, this.name()));
    }

    public static String fromString(String value) {
        return PlatformLookup.lookup.get(value);
    }

    /**
     * Whether the platform is on an Apple Mac OS.
     *
     * @return true if platform is OS X or macOS
     */
    public boolean isMac() {
        return Arrays.stream(this.values).anyMatch(val -> Stream.of("macOS", "OS X", "Mac").anyMatch(val::contains));
    }

    /**
     * Whether the platform is Microsoft Windows.
     *
     * @return true if platform is Windows OS
     */
    public boolean isWindows() {
        return Arrays.stream(this.values).anyMatch(val -> val.contains("Windows"));
    }

    public String toString() {
        return this.values[0];
    }
}
