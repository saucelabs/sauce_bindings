package com.saucelabs.saucebindings.screen_resolutions;

public class ScreenDimensions {
    public static String desktop(Desktop value) {
        return String.valueOf(Desktop.valueOf(value.toString()));
    }

    public static String apple(Apple value) {
        return String.valueOf(Apple.valueOf(value.toString()));
    }

    public static String google(Google value) {
        return String.valueOf(Google.valueOf(value.toString()));
    }

    public static String samsung(Samsung value) {
        return String.valueOf(Samsung.valueOf(value.toString()));
    }

    public static DeviceDimensions getResolution(String key) {
        if (Desktop.hasKey(key)) {
            return Desktop.valueOf(key);
        }
        if (Google.hasKey(key)) {
            return Google.valueOf(key);
        }
        if (Apple.hasKey(key)) {
            return Apple.valueOf(key);
        }
        if (Samsung.hasKey(key)) {
            return Samsung.valueOf(key);
        }
        throw new RuntimeException("Unable to locate a resolutions for " + key);
    }
}
