package com.saucelabs.saucebindings;

public class SauceSessionNotStartedException extends RuntimeException {
    /**
     * Some methods will not do anything if a Sauce session has not been started.
     *
     * @param message the name of the method that requires the session to be started
     */
    public SauceSessionNotStartedException(String message) {
        super("Session must be started before executing " + message);
    }
}
