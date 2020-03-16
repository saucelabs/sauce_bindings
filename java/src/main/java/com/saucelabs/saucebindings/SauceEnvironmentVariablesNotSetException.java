package com.saucelabs.saucebindings;

class SauceEnvironmentVariablesNotSetException extends RuntimeException {
    public SauceEnvironmentVariablesNotSetException(String message) {
        super(message);
    }
}
