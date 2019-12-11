package com.saucelabs.simplesauce;

import com.google.common.annotations.VisibleForTesting;

public class EnvironmentManagerImpl implements EnvironmentManager {

    @VisibleForTesting
    //This exists mostly so that we can mock getting environment variables during unit tests
    public String getEnvironmentVariable(String variable) {
        return System.getenv(variable);
    }
}
