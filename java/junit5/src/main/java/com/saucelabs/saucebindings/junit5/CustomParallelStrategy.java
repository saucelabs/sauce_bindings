package com.saucelabs.saucebindings.junit5;

import org.junit.platform.engine.ConfigurationParameters;
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfiguration;
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfigurationStrategy;

public class CustomParallelStrategy implements ParallelExecutionConfiguration, ParallelExecutionConfigurationStrategy {

    @Override
    public int getParallelism() {
        return getParallelCount();
    }

    @Override
    public int getMinimumRunnable() {
        return 0;
    }

    @Override
    public int getMaxPoolSize() {
        return getParallelCount();
    }

    @Override
    public int getCorePoolSize() {
        return getParallelCount();
    }

    @Override
    public int getKeepAliveSeconds() {
        return 60;
    }

    @Override
    public ParallelExecutionConfiguration createConfiguration(final ConfigurationParameters configurationParameters) {
        return this;
    }

    private int getParallelCount() {
        if (System.getProperty("PARALLEL_COUNT") != null) {
            return Integer.parseInt(System.getProperty("PARALLEL_COUNT"));
        } else {
            return Runtime.getRuntime().availableProcessors();
        }
    }
}
