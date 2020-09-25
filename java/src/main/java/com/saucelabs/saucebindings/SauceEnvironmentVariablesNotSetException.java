package com.saucelabs.saucebindings;

public class SauceEnvironmentVariablesNotSetException extends RuntimeException {
    public SauceEnvironmentVariablesNotSetException(String message) {
        super(message);
    }
}
