package com.saucelabs.simplesauce;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;

import java.util.HashMap;

public class SauceOptions {
    @Getter private MutableCapabilities seleniumCapabilities;

    @Getter @Setter private String browserName = BrowserType.CHROME;
    @Getter @Setter private String browserVersion = "latest";
    @Getter @Setter private String platformName = "Windows 10";
    @Setter private String build;

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
        seleniumCapabilities.setCapability(CapabilityType.BROWSER_NAME, browserName);
        seleniumCapabilities.setCapability(CapabilityType.PLATFORM_NAME, platformName);
        seleniumCapabilities.setCapability(CapabilityType.BROWSER_VERSION, browserVersion);
        seleniumCapabilities.setCapability("sauce:options", new HashMap<>());
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

    protected String getEnvironmentVariable(String key) {
        return System.getenv(key);
    }

}
