package com.saucelabs.simplesauce;

import lombok.Getter;

public enum TestResult {
    PASS("passed"),
    FAIL("failed");

    @Getter private final String result;

    TestResult(String result) {
        this.result = result;
    }
}
