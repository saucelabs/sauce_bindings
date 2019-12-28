package com.saucelabs.simplesauce;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.CapabilityType;

import java.util.HashMap;
import java.util.Map;

@Accessors(chain = true)
public class SauceOptions {
    @Getter private MutableCapabilities seleniumCapabilities;
    private MutableCapabilities sauceCapabilities = new MutableCapabilities();

    @Getter @Setter private Options.Browser browserName = Options.Browser.CHROME;
    @Getter @Setter private String browserVersion = "latest";
    @Getter @Setter private Options.Platform platformName = Options.Platform.WINDOWS_10;
    @Setter private String build;

    public SauceOptions() {
        this(new MutableCapabilities());
    }

    public SauceOptions(Capabilities capabilities) {
        seleniumCapabilities = new MutableCapabilities(capabilities);
        if (capabilities.getCapability("browserName") != null) {
           setCapability("browserName", capabilities.getCapability("browserName"));
        }
    }

    public MutableCapabilities toCapabilities() {
        seleniumCapabilities.setCapability(CapabilityType.BROWSER_NAME, browserName);
        seleniumCapabilities.setCapability(CapabilityType.PLATFORM_NAME, platformName);
        seleniumCapabilities.setCapability(CapabilityType.BROWSER_VERSION, browserVersion);

        sauceCapabilities.setCapability("build", getBuild());
        seleniumCapabilities.setCapability("sauce:options", sauceCapabilities);

        return seleniumCapabilities;
    }

    public String getBuild() {
        if (build != null) {
            return build;
            // Jenkins
        } else if (getEnvironmentVariable("BUILD_TAG") != null) {
            return getEnvironmentVariable("BUILD_NAME") + ": " + getEnvironmentVariable("BUILD_NUMBER");
            // Bamboo
        } else if (getEnvironmentVariable("bamboo_agentId") != null) {
            return getEnvironmentVariable("bamboo_shortJobName") + ": " + getEnvironmentVariable("bamboo_buildNumber");
            // Travis
        } else if (getEnvironmentVariable("TRAVIS_JOB_ID") != null) {
            return getEnvironmentVariable("TRAVIS_JOB_NAME") + ": " + getEnvironmentVariable("TRAVIS_JOB_NUMBER");
            // CircleCI
        } else if (getEnvironmentVariable("CIRCLE_JOB") != null) {
            return getEnvironmentVariable("CIRCLE_JOB") + ": " + getEnvironmentVariable("CIRCLE_BUILD_NUM");
            // Gitlab
        } else if (getEnvironmentVariable("CI") != null) {
            return getEnvironmentVariable("CI_JOB_NAME") + ": " + getEnvironmentVariable("CI_JOB_ID");
            // Team City
        } else if (getEnvironmentVariable("TEAMCITY_PROJECT_NAME") != null) {
            return getEnvironmentVariable("TEAMCITY_PROJECT_NAME") + ": " + getEnvironmentVariable("BUILD_NUMBER");
            // Default
        } else {
            return "Build Time: " + System.currentTimeMillis();
        }
    }

    // Use Case is pulling serialized information from JSON/YAML, converting it to a HashMap and passing it in
    // This is a preferred pattern as it avoids conditionals in code
    public void setCapabilities(Map<String, Object> capabilities) {
        capabilities.forEach(this::setCapability);
    }

    public SauceOptions setCapability(String key, Object value) {
        if ("browserName".equals(key) && value.getClass().equals(String.class)) {
            this.browserName = Options.Browser.valueOf(Options.Browser.fromString((String) value));
        } else if ("browserVersion".equals(key)) {
            this.browserVersion = (String) value;
        } else if ("platformName".equals(key) && value.getClass().equals(String.class)) {
            this.platformName = Options.Platform.valueOf(Options.Platform.fromString((String) value));
        } else if ("build".equals(key)) {
            this.build = (String) value;
        } else if ("pageLoadStrategy".equals(key) && value.getClass().equals(String.class)) {
            Options.PageLoadStrategy strategy = Options.PageLoadStrategy.valueOf(Options.PageLoadStrategy.fromString((String) value));
            seleniumCapabilities.setCapability("pageLoadStrategy", strategy);
        } else if ("unhandledPromptBehavior".equals(key) && value.getClass().equals(String.class)) {
            Options.UnhandledPromptBehavior behavior = Options.UnhandledPromptBehavior.valueOf(Options.UnhandledPromptBehavior.fromString((String) value));
            seleniumCapabilities.setCapability("unhandledPromptBehavior", behavior);
        } else if ("public".equals(key) && value.getClass().equals(String.class)) {
            Options.Public visiblity = Options.Public.valueOf(Options.Public.fromString((String) value));
            sauceCapabilities.setCapability("public", visiblity);
        } else if ("prerun".equals(key) && ((HashMap) value).keySet().toArray()[0].getClass().equals(String.class)) {
            Map<Options.PreRun, Object> prerunMap = new HashMap<>();
            ((HashMap) value).forEach((oldKey, val) -> {
                String keyString = Options.PreRun.fromString((String) oldKey);
                prerunMap.put(Options.PreRun.valueOf(keyString), val);
            });
            sauceCapabilities.setCapability("prerun", prerunMap);
        } else if ("timeouts".equals(key) && ((HashMap) value).keySet().toArray()[0].getClass().equals(String.class)) {
            Map<Options.Timeouts, Integer> timeoutMap = new HashMap<>();
            ((HashMap) value).forEach((oldKey, val) -> {
                String keyString = Options.Timeouts.fromString((String) oldKey);
                timeoutMap.put(Options.Timeouts.valueOf(keyString), (Integer) val);
            });
            seleniumCapabilities.setCapability("timeouts", timeoutMap);
        } else if (Options.w3c.contains(key)) {
            seleniumCapabilities.setCapability(key, value);
        } else if (Options.sauce.contains(key)) {
            sauceCapabilities.setCapability(key, value);
        } else {
            throw new InvalidArgumentException(key + " is not a known capability");
        }
        return this;
    }

    public Object getCapability(String key) {
        if (Options.w3c.contains(key)) {
            return seleniumCapabilities.getCapability(key);
        } else if (Options.sauce.contains(key)) {
            return sauceCapabilities.getCapability(key);
        } else {
            throw new InvalidArgumentException(key + " is not a known capability");
        }
    }

    protected String getEnvironmentVariable(String key) {
        return System.getenv(key);
    }
}
