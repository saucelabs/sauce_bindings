package com.saucelabs.simplesauce;

class SauceEnvironmentVariablesNotSetException extends RuntimeException {
    public SauceEnvironmentVariablesNotSetException(String message) {
        super(message);
    }
}
