package com.saucelabs.simplesauce;

public class ConcreteSystemManager implements EnvironmentManager {
    public String getEnvironmentVariable(String variable) {
        return System.getenv(variable);
    }
}
