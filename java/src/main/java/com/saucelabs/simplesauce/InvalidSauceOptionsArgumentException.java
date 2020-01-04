package com.saucelabs.simplesauce;

class InvalidSauceOptionsArgumentException extends RuntimeException {
    public InvalidSauceOptionsArgumentException(String message) {
        super(message);
    }
}
