package com.saucelabs.saucebindings;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class CapabilityManager {
    private final Options options;

    public CapabilityManager(Options options) {
        this.options = options;
    }

    public void addCapabilities() {
        options.getValidOptions().forEach((capability) -> {
            Object value = getCapability(capability);
            if (value != null) {
                options.capabilities.setCapability(capability, value);
            }
        });
    }

    public Object getCapability(String capability) {
        try {
            String getter = "get" + capability.substring(0, 1).toUpperCase() + capability.substring(1);
            Method declaredMethod = null;
            declaredMethod = options.getClass().getDeclaredMethod(getter);
            return declaredMethod.invoke(options);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected void capabilityValidator(String name, Set values, String value) {
        if (!values.contains(value)) {
            String message = value + " is not a valid " + name + ", please choose from: " + values;
            throw new InvalidSauceOptionsArgumentException(message);
        }
    }
}
