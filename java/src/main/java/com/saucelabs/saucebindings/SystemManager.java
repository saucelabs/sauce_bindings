package com.saucelabs.saucebindings;

public class SystemManager {
    protected String get(String key, String errorMessage) {
        if (getProperty(key) != null) {
            return getProperty(key);
        } else if (getEnv(key) != null) {
            return getEnv(key);
        } else {
            throw new SauceEnvironmentVariablesNotSetException(errorMessage);
        }
    }

    protected String getProperty(String key) {
        return System.getProperty(key);
    }

    protected String getEnv(String key) {
        return System.getenv(key);
    }
}
