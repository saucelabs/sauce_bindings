package com.saucelabs.simplesauce;

import lombok.Getter;
import lombok.Setter;

public class SauceTimeout {
    @Getter
    @Setter
    private int commandTimeout;
    @Getter
    @Setter
    private int idleTimeout;
    @Getter
    private int maxTestDurationTimeout;

    public void setMaxTestDurationTimeout(int maxTestDurationInSec) throws MaxTestDurationTimeoutExceeded {
        if(maxTestDurationInSec > 1800)
        {
            throw new MaxTestDurationTimeoutExceeded();
        }
        maxTestDurationTimeout = maxTestDurationInSec;
    }
}

