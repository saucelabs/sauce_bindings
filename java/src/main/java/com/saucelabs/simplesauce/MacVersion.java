package com.saucelabs.simplesauce;

public enum MacVersion {
    Mojave("macOS 10.14"),
    HighSierra("macOS 10.13"),
    Sierra("macOS 10.12"),
    ElCapitan("OS X 10.11"),
    Yosemite("OS X 10.10");

    public String label;

    MacVersion(String label) {
        this.label = label;
    }
}
