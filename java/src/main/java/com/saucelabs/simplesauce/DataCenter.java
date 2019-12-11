package com.saucelabs.simplesauce;

import lombok.Getter;

enum DataCenter {
    US_WEST("https://ondemand.saucelabs.com/wd/hub"),
    US_EAST("https://ondemand.us-east-1.saucelabs.com/wd/hub"),
    EU_CENTRAL("https://ondemand.eu-central-1.saucelabs.com/wd/hub");

    @Getter private final String endpoint;

    DataCenter(String endpoint) {
        this.endpoint = endpoint;
    }
}
