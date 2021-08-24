package com.saucelabs.saucebindings.junit5;

import org.junit.platform.engine.ConfigurationParameters;
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfiguration;
import org.junit.platform.engine.support.hierarchical.ParallelExecutionConfigurationStrategy;

public class CustomStrategy implements ParallelExecutionConfiguration, ParallelExecutionConfigurationStrategy {

    @Override
    public int getParallelism() {
        return Integer.parseInt(System.getProperty("PARALLELISM"));
    }

    @Override
    public int getMinimumRunnable() {
        return Integer.parseInt(System.getProperty("MINIMUM_RUNNABLE"));
    }

    @Override
    public int getMaxPoolSize() {
        return Integer.parseInt(System.getProperty("MAX_POOL_SIZE"));
    }

    @Override
    public int getCorePoolSize() {
        return Integer.parseInt(System.getProperty("CORE_POOL_SIZE"));
    }

    @Override
    public int getKeepAliveSeconds() {
        return 30;
    }

    @Override
    public ParallelExecutionConfiguration createConfiguration(final ConfigurationParameters configurationParameters) {
        return this;
    }
}
