package com.saucelabs.simplesauce;

public class SauceTimeout {
    private int maxTestDurationInSeconds = 1800;
    private int commandTimeout;
    private int idleTimeout;

    public int getMaxTestDuration() {
        return maxTestDurationInSeconds;
    }

    public void setCommandTimeout(int commandTimeout) {
        this.commandTimeout = commandTimeout;
    }

    public int getCommandTimeout() {
        return commandTimeout;
    }

    public void setIdleTimeout(int idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public int getIdleTimeout() {
        return idleTimeout;
    }
}
