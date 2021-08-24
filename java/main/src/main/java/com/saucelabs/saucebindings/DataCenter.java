package com.saucelabs.saucebindings;

import lombok.Getter;

public enum DataCenter {
    US_WEST("https://ondemand.us-west-1.saucelabs.com/wd/hub"),
    US_EAST("https://ondemand.us-east-1.saucelabs.com/wd/hub"),
    EU_CENTRAL("https://ondemand.eu-central-1.saucelabs.com/wd/hub"),
    APAC_SOUTHEAST("https://ondemand.apac-southeast-1.saucelabs.com/wd/hub");

    @Getter private final String value;

    DataCenter(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
