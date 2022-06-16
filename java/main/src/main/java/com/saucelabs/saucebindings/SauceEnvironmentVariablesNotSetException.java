package com.saucelabs.saucebindings;

/**
 * Setting Environment Variables to Authenticate with Sauce Labs is required.
 */
class SauceEnvironmentVariablesNotSetException extends RuntimeException {
    public SauceEnvironmentVariablesNotSetException(String message) {
        super(message);
    }
}
