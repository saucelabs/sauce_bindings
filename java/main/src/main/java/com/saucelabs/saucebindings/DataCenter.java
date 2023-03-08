package com.saucelabs.saucebindings;

import lombok.Getter;

/**
 * Valid Data Center Server Names.
 *
 * @see <a href="https://docs.saucelabs.com/basics/data-center-endpoints/">Data Center Endpoints</a>
 */
@Getter
public enum DataCenter {
    US_WEST("us-west-1"),
    US_EAST("us-east-1"),
    EU_CENTRAL("eu-central-1"),
    APAC_SOUTHEAST("apac-southeast-1");

    private final String value;
    private final String endpoint;
    private final String testLink;

    @Deprecated
    public String getValue() {
        return this.endpoint;
    }

    DataCenter(String value) {
        this.value = value;
        this.endpoint = "https://ondemand." + value + ".saucelabs.com/wd/hub";
        if ("us-west-1".equals(value)) {
            this.testLink = "https://app.saucelabs.com/tests/";
        } else {
            this.testLink = "https://app." + value + ".saucelabs.com/tests/";
        }
    }

    public String toString() {
        return value;
    }
}
