package com.saucelabs.saucebindings.options;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** Manages processing capabilities on behalf of the options class. */
public class CapabilityManager {
    private final BaseOptions options;

    /**
     * Class constructor created for a specific SauceOptions instance.
     *
     * @param options the SauceOptions instance using this capabilities manager
     */
    public CapabilityManager(BaseOptions options) {
        this.options = options;
    }

    /**
     * Add values of option class's valid capabilities to the option class's capabilities object.
     */
    public void addCapabilities() {
        options.getValidOptions().forEach((capability) -> {
            Object value = getCapability(capability);
            if (value != null
                    && !(value instanceof Map && ((Map<String, Object>) value).isEmpty())
                    && !(value instanceof List && ((List<Object>) value).isEmpty())) {
                options.capabilities.setCapability(capability, value);
            }
        });
    }

    /**
     * Dynamically sets the provided value onto the associated options instance.
     *
     * @param key   Name of the capability to set on the options instance
     * @param value Value of the capability to set on the options instance
     */
    public void setCapability(String key, Object value) {
        try {
            Class<?> type = getField(options.getClass(), key).getType();
            String setter = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
            Method method = options.getClass().getMethod(setter, type);
            method.invoke(options, value);
        } catch (NoSuchFieldException e) {
            throw new InvalidSauceOptionsArgumentException(key + " isn't a valid capability for " + options.getClass());
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("Unable to set capability for " + key, e);
        }
    }

    /**
     * Dynamically obtain the value of the Field set on the options instance.
     *
     * @param capability    the name of the field to get the value from
     * @return              the value of the provided field name
     */
    public Object getCapability(String capability) {
        try {
            String getter = "get" + capability.substring(0, 1).toUpperCase() + capability.substring(1);
            Method method = options.getClass().getMethod(getter);
            return method.invoke(options);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Unable to get capability for " + capability, e);
        }
    }

    /**
     * This ensures that a parameter with an enum Type properly matches.
     *
     * @param name      Which enum we are working with for better error message
     * @param values    Valid options for the provided capability
     * @param value     Value of the option we want to add to SauceOptions
     */
    public void validateCapability(String name, Set<String> values, String value) {
        if (!values.contains(value)) {
            String message = "\"" + value + "\" is not a valid " + name + ", please choose from: " + values;
            throw new InvalidSauceOptionsArgumentException(message);
        }
    }

    /**
     * Recursively searches superclasses to find desired Field even when private.
     */
    private Field getField(Class<?> optionsClass, String fieldName) throws NoSuchFieldException {
        try {
            return optionsClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class<?> superclass = optionsClass.getSuperclass();
            if (superclass == null) {
                throw e;
            } else {
                return getField(optionsClass.getSuperclass(), fieldName);
            }
        }
    }
}
