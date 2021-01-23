package com.saucelabs.saucebindings.options;

import com.saucelabs.saucebindings.JobVisibility;
import com.saucelabs.saucebindings.SaucePlatform;

import java.util.List;
import java.util.Map;

public abstract class BaseConfigurations<T extends BaseConfigurations<T>> {
    // Needs to be instantiated in subclass
    SauceOptions sauceOptions = null;

    // Available on all configs desktop & mobile

    // Override this in subclasses to ensure valid enum
    public T setPlatformName(SaucePlatform platform) {
        sauceOptions.setPlatformName(platform);
        return (T) this;
    }

    public T setName(String name) {
        sauceOptions.sauce().setName(name);
        return (T) this;
    }

    public T setBuild(String build) {
        sauceOptions.sauce().setBuild(build);
        return (T) this;
    }
    
    public T setTags(List<String> tags) {
        sauceOptions.sauce().setTags(tags);
        return (T) this;
    }
    
    public T setCustomData(Map<String, Object> data) {
        sauceOptions.sauce().setCustomData(data);
        return (T) this;
    }
    
    // This corresponds to "public" keyword
    public T setJobVisibility(JobVisibility visibility) {
        sauceOptions.sauce().setJobVisibility(visibility);
        return (T) this;
    }
    
    public T setTunnelIdentifier(String identifier) {
        sauceOptions.sauce().setTunnelIdentifier(identifier);
        return (T) this;
    }

    public T setParentTunnel(String identifier) {
        sauceOptions.sauce().setParentTunnel(identifier);
        return (T) this;
    }

    public SauceOptions build() {
        return sauceOptions;
    }
}
