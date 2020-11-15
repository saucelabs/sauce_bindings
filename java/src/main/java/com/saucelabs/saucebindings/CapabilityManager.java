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

    public void setCapability(String key, Object value) {
        try {
            Class<?> type = options.getClass().getDeclaredField(key).getType();
            String setter = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
            Method method = options.getClass().getDeclaredMethod(setter, type);
            method.invoke(options, value);
        } catch (NoSuchFieldException e) {
            throw new InvalidSauceOptionsArgumentException(key + " is not a valid configuration value");
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public Object getCapability(String capability) {
        try {
            String getter = "get" + capability.substring(0, 1).toUpperCase() + capability.substring(1);
            Method method = options.getClass().getDeclaredMethod(getter);
            return method.invoke(options);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void validateCapability(String name, Set values, String value) {
        if (!values.contains(value)) {
            String message = value + " is not a valid " + name + ", please choose from: " + values;
            throw new InvalidSauceOptionsArgumentException(message);
        }
    }
}
