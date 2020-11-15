package com.saucelabs.saucebindings.options;

import com.saucelabs.saucebindings.CapabilityManager;
import com.saucelabs.saucebindings.SystemManager;
import lombok.Getter;
import org.openqa.selenium.MutableCapabilities;

import java.util.List;
import java.util.Map;

public abstract class BaseOptions {
    @Getter protected MutableCapabilities capabilities = new MutableCapabilities();
    protected CapabilityManager capabilityManager;
    protected SystemManager systemManager;
    @Getter public final List<String> validOptions = null;

    // Use Case is pulling serialized information from JSON/YAML, converting it to a HashMap and passing it in
    // This is a preferred pattern as it avoids conditionals in code
    public void mergeCapabilities(Map<String, Object> mergingCapabilities) {
        mergingCapabilities.forEach(this::setCapability);
    }

    // This dynamically calls setter
    // Applicable enums must override this method in subclass
    protected void setCapability(String key, Object value) {
        capabilityManager.setCapability(key, value);
    };
}
