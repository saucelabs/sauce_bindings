package com.saucelabs.simplesauce;

import com.saucelabs.simplesauce.interfaces.EnvironmentManager;

public class ConcreteSystemManager implements EnvironmentManager {
    public String getEnvironmentVariable(String variable) {
        return System.getenv(variable);
    }
}
