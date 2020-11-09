package com.saucelabs.saucebindings;

import org.openqa.selenium.MutableCapabilities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

public class CapabilityManager {
    private final SauceOptions options;

    public CapabilityManager(SauceOptions options) {
        this.options = options;
    }

    public MutableCapabilities addCapabilities(MutableCapabilities capabilities, List<String> validOptions) {
        validOptions.forEach((capability) -> {
            Object value = getCapability(capability);
            if (value != null) {
                capabilities.setCapability(capability, value);
            }
        });
        return capabilities;
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
