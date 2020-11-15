package com.saucelabs.saucebindings;

public class SystemManager {
    protected static String get(String key, String errorMessage) {
        try {
            return get(key);
        } catch (Exception e) {
            throw new SauceEnvironmentVariablesNotSetException(errorMessage);
        }
    }

    public static String get(String key) {
        if (getProperty(key) != null) {
            return getProperty(key);
        } else if (getEnv(key) != null) {
            return getEnv(key);
        } else {
            return getProperty(key);
        }
    }

    protected static String getProperty(String key) {
        return System.getProperty(key);
    }

    protected static String getEnv(String key) {
        return System.getenv(key);
    }
}
