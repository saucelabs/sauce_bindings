package com.saucelabs.saucebindings;

import lombok.Getter;

public enum DataCenter {
    US_LEGACY("https://ondemand.saucelabs.com/wd/hub"),
    US_WEST("https://ondemand.us-west-1.saucelabs.com/wd/hub"),
    US_EAST("https://ondemand.us-east-1.saucelabs.com/wd/hub"),
    HEADLESS("https://ondemand.us-east-1.saucelabs.com/wd/hub"),
    EU_CENTRAL("https://ondemand.eu-central-1.saucelabs.com/wd/hub");

    @Getter private final String value;

    DataCenter(String value) {
        this.value = value;
    }

    public boolean supportsW3C() {
        return value.equals("https://ondemand.saucelabs.com/wd/hub");
    }

    public String toString() {
        return value;
    }
}
