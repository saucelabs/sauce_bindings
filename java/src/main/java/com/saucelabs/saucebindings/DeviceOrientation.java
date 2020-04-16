package com.saucelabs.saucebindings;

import lombok.Getter;

public enum DeviceOrientation {
   PORTRAIT("portrait"),
   LANDSCAPE("landscape");

    @Getter private final String value;

    DeviceOrientation(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
