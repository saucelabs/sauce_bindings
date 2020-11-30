package com.saucelabs.saucebindings.configs;

import com.saucelabs.saucebindings.JobVisibility;
import com.saucelabs.saucebindings.SauceOptions;
import com.saucelabs.saucebindings.SaucePlatform;

import java.util.List;
import java.util.Map;

public class SauceConfigs<T extends SauceConfigs<T>> {
    // Needs to be instantiated in subclass
    SauceOptions sauceOptions = null;

    // Available on all configs desktop & mobile

    // Override this in subclasses to ensure valid enum
    public T setPlatformName(SaucePlatform platform) {
        sauceOptions.setPlatformName(platform);
        return (T) this;
    }

    public T setName(String name) {
        sauceOptions.setName(name);
        return (T) this;
    }

    public T setBuild(String build) {
        sauceOptions.setBuild(build);
        return (T) this;
    }
    
    public T setTags(List<String> tags) {
        sauceOptions.setTags(tags);
        return (T) this;
    }
    
    public T setCustomData(Map<String, Object> data) {
        sauceOptions.setCustomData(data);
        return (T) this;
    }
    
    // This corresponds to "public" keyword
    public T setJobVisibility(JobVisibility visibility) {
        sauceOptions.setJobVisibility(visibility);
        return (T) this;
    }
    
    public T setTunnelIdentifier(String identifier) {
        sauceOptions.setTunnelIdentifier(identifier);
        return (T) this;
    }

    public T setParentTunnel(String identifier) {
        sauceOptions.setParentTunnel(identifier);
        return (T) this;
    }

    public SauceOptions build() {
        return sauceOptions;
    }
}
