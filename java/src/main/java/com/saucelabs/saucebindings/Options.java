package com.saucelabs.saucebindings;

import lombok.Getter;
import org.openqa.selenium.MutableCapabilities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public abstract class Options {
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
    // Applicable enums must be dealt with in subclass method
    protected void setCapability(String key, Object value) {
        try {
            Class<?> type = this.getClass().getDeclaredField(key).getType();
            String setter = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
            Method method = this.getClass().getDeclaredMethod(setter, type);
            method.invoke(this, value);
        } catch (NoSuchFieldException e) {
            throw new InvalidSauceOptionsArgumentException(key + "is not a valid configuration value");
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    };
}
