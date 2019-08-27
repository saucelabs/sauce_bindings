package com.saucelabs.simplesauce;

public class SauceTimeout {
    private int maxTestDurationInSeconds = 1800;
    private int commandTimeout;

    public int getMaxTestDuration() {
        return maxTestDurationInSeconds;
    }

    public void setCommandTimeout(int commandTimeout) {
        this.commandTimeout = commandTimeout;
    }

    public int getCommandTimeout() {
        return commandTimeout;
    }
}
