package com.saucelabs.saucebindings.exceptions;

public class SauceEnvironmentVariablesNotSetException extends RuntimeException {
    public SauceEnvironmentVariablesNotSetException(String message) {
        super(message);
    }
}
