package com.saucelabs.saucebindings.options;

import lombok.Getter;
import org.openqa.selenium.MutableCapabilities;

import java.util.List;
import java.util.Map;

public abstract class BaseOptions {
    @Getter protected MutableCapabilities capabilities = new MutableCapabilities();
    protected CapabilityManager capabilityManager;
    @Getter public final List<String> validOptions = null;

    /**
     * Use Case is pulling serialized information from JSON/YAML, converting it to a HashMap and passing it in
     * This is a preferred pattern as it avoids conditionals in code
     *
     * @deprecated pass in the location of the config file and the corresponding key directly to the constructor
     * @param capabilitiesToMerge a Map object representing key value pairs to convert to capabilities
     */
    @Deprecated
    public void mergeCapabilities(Map<String, Object> capabilitiesToMerge) {
        capabilitiesToMerge.forEach(this::setCapability);
    }

    /**
     * This dynamically calls setter
     * Applicable enums must override this method in subclass
     * @param key the name of the capability getting set on the capabilities instance being built
     * @param value the object representing the value of what is set on the capabilities instance being set
     */
    protected void setCapability(String key, Object value) {
        capabilityManager.setCapability(key, value);
    }
}
