package com.saucelabs.simplesauce;

import lombok.Getter;

public enum DataCenter {
    US_WEST("ondemand.us-west-1.saucelabs.com"),
    US_EAST("ondemand.us-east-1.saucelabs.com"),
    EU_CENTRAL("ondemand.eu-central-1.saucelabs.com");

    @Getter private final String value;

    DataCenter(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
