package com.saucelabs.saucebindings.screen_resolutions;

public interface PixelDimensions {
    public static boolean hasKey(String key) {
        return false;
    }
    public String getValue();
    public String getDisplayName();
}
