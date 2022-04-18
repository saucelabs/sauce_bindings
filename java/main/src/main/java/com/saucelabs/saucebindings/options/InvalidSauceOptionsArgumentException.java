package com.saucelabs.saucebindings.options;

/**
 * When a submitted capability is invalid.
 */
public class InvalidSauceOptionsArgumentException extends RuntimeException {
    public InvalidSauceOptionsArgumentException(String message) {
        super(message);
    }

    public InvalidSauceOptionsArgumentException(String message, Throwable ex) {
        super(message, ex);
    }
}
