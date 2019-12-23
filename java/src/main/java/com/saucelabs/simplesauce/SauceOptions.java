package com.saucelabs.simplesauce;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Proxy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SauceOptions {
    @Getter private MutableCapabilities seleniumCapabilities;

    // w3c Settings
    @Getter @Setter private String browserName = "chrome";
    @Getter @Setter private String browserVersion = "latest";
    @Getter @Setter private String platformName = "Windows 10";
    @Getter @Setter private String pageLoadStrategy;
    @Getter @Setter private Boolean acceptInsecureCerts = null;
    @Getter @Setter private Proxy proxy;
    @Getter @Setter private Boolean setWindowRect = null;
    @Getter @Setter private Map<String, Integer> timeouts;
    @Getter @Setter private Boolean strictFileInteractability = null;
    @Getter @Setter private String unhandledPromptBehavior;

    // Sauce Settings
    @Getter @Setter private Boolean avoidProxy = null;
    @Setter private String build;
    @Getter @Setter private Boolean capturePerformance = null;
    @Getter @Setter private String chromedriverVersion;
    @Getter @Setter private Integer commandTimeout = null;
    @Getter @Setter private Map<String, Object> customData = null;
    @Getter @Setter private Boolean extendedDebugging = null;
    @Getter @Setter private Integer idleTimeout = null;
    @Getter @Setter private String iedriverVersion;
    @Getter @Setter private Integer maxDuration = null;
    @Getter @Setter private String name;
    @Getter @Setter private String parentTunnel;
    @Getter @Setter private Map<String, Object> prerun;
    @Getter @Setter private String prerunUrl;
    @Getter @Setter private Integer priority = null;
    @Getter @Setter private String publicRestricted;
    @Getter @Setter private Boolean recordLogs = null;
    @Getter @Setter private Boolean recordScreenshots = null;
    @Getter @Setter private Boolean recordVideo = null;
    @Getter @Setter private String screenResolution;
    @Getter @Setter private String seleniumVersion;
    @Getter @Setter private List<String> tags = null;
    @Getter @Setter private String timeZone;
    @Getter @Setter private String tunnelIdentifier;
    @Getter @Setter private Boolean videoUploadOnPass = null;

    public SauceOptions() {
        this(new MutableCapabilities());
    }

    public SauceOptions(Capabilities capabilities) {
        seleniumCapabilities = new MutableCapabilities(capabilities);
        if (capabilities.getCapability("browserName") != null) {
            browserName = (String) capabilities.getCapability("browserName");
        }
    }

    public MutableCapabilities toCapabilities() {
        MutableCapabilities w3cCapabilities = getSeleniumCapabilities();
        MutableCapabilities sauceCapabilities = new MutableCapabilities();

        for (Field field : this.getClass().getDeclaredFields()) {
            try {
                String fieldName = field.getName();
                if (!"seleniumCapabilities".equals(fieldName)) {
                    Object value = getCapability(fieldName);
                    String key = "prerunUrl".equals(fieldName) ? "prerun" : fieldName;
                    if (value == null) {
                        continue;
                    } else if (Options.w3c.contains(key)) {
                        w3cCapabilities.setCapability(fieldName, value);
                    } else if (Options.sauce.contains(key)) {
                        sauceCapabilities.setCapability(fieldName, value);
                    } else if ("publicRestricted".equals(key)) {
                        sauceCapabilities.setCapability("public", value);
                    }
                }
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        w3cCapabilities.setCapability("sauce:options", sauceCapabilities);
        return w3cCapabilities;
    }

    public String getBuild() {
        if (build != null) {
            return build;
            // Jenkins
        } else if (System.getenv("BUILD_TAG") != null) {
            return System.getenv("BUILD_NAME") + ": " + System.getenv("BUILD_NUMBER");
            // Bamboo
        } else if (System.getenv("bamboo_agentId") != null) {
            return System.getenv("bamboo_shortJobName") + ": " + System.getenv("bamboo_buildNumber");
            // Travis
        } else if (System.getenv("TRAVIS_JOB_ID") != null) {
            return System.getenv("TRAVIS_JOB_NAME") + ": " + System.getenv("TRAVIS_JOB_NUMBER");
            // CircleCI
        } else if (System.getenv("CIRCLE_JOB") != null) {
            return System.getenv("CIRCLE_JOB") + ": " + System.getenv("CIRCLE_BUILD_NUM");
            // Gitlab
        } else if (System.getenv("CI") != null) {
            return System.getenv("CI_JOB_NAME") + ": " + System.getenv("CI_JOB_ID");
            // Team City
        } else if (System.getenv("TEAMCITY_PROJECT_NAME") != null) {
            return System.getenv("TEAMCITY_PROJECT_NAME") + ": " + System.getenv("BUILD_NUMBER");
            // Default
        } else {
            return "Build Time: " + System.currentTimeMillis();
        }
    }

    public void setCapabilities(Map<String, Object> capabilities) {
        capabilities.forEach(this::setCapability);
    }

    // TODO: Consider making this public
    private void setCapability(String key, Object value) {
        String setter = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
        try {
            Class<?> type = SauceOptions.class.getDeclaredField(key).getType();
            Method method = SauceOptions.class.getDeclaredMethod(setter, type);
            method.invoke(this, value);
        } catch (NoSuchMethodException | NoSuchFieldException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    // TODO: Consider making this public
    private Object getCapability(String capability) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String getter = "get" + capability.substring(0, 1).toUpperCase() + capability.substring(1);
        Method declaredMethod = SauceOptions.class.getDeclaredMethod(getter);
        return declaredMethod.invoke(this);
    }
}
