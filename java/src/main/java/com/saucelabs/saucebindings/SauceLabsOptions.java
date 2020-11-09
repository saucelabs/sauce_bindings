package com.saucelabs.saucebindings;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.openqa.selenium.MutableCapabilities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Accessors(chain = true) @Setter @Getter
public class SauceLabsOptions extends Options {
    // https://wiki.saucelabs.com/display/DOCS/Test+Configuration+Options
    private Boolean avoidProxy = null;
    private String build;
    private Boolean capturePerformance = null;
    private String chromedriverVersion;
    private Integer commandTimeout = null;
    private Map<String, Object> customData = null;
    private Boolean extendedDebugging = null;
    private Integer idleTimeout = null;
    private String iedriverVersion;
    private Integer maxDuration = null;
    private String name;
    private String parentTunnel;
    private Map<Prerun, Object> prerun;
    private URL prerunUrl;
    private Integer priority = null;
    private JobVisibility jobVisibility; // the actual key for this is a Java reserved keyword "public"; uses enum
    private Boolean recordLogs = null;
    private Boolean recordScreenshots = null;
    private Boolean recordVideo = null;
    private String screenResolution;
    private String seleniumVersion;
    private List<String> tags = null;
    private String timeZone;
    private String tunnelIdentifier;
    private Boolean videoUploadOnPass = null;


    public final List<String> validOptions = Arrays.asList(
            "avoidProxy",
            "build",
            "capturePerformance",
            "chromedriverVersion",
            "commandTimeout",
            "customData",
            "extendedDebugging",
            "idleTimeout",
            "iedriverVersion",
            "jobVisibility",
            "maxDuration",
            "name",
            "parentTunnel",
            "prerun",
            "prerunUrl",
            "priority",
            // public, do not use, reserved keyword, using jobVisibility with enum
            "recordLogs",
            "recordScreenshots",
            "recordVideo",
            "screenResolution",
            "seleniumVersion",
            "tags",
            "timeZone",
            "tunnelIdentifier",
            "videoUploadOnPass");

    public SauceLabsOptions() {
        capabilityManager = new CapabilityManager(this);
        systemManager = new SystemManager();
    }

    public MutableCapabilities toCapabilities() {
        capabilities.setCapability("username", getSauceUsername());
        capabilities.setCapability("accessKey", getSauceAccessKey());

        Object visibilityValue = capabilityManager.getCapability("jobVisibility");
        if (visibilityValue != null) {
            capabilities.setCapability("public", visibilityValue);
            setJobVisibility(null);
        }

        Object prerunValue = capabilityManager.getCapability("prerunUrl");
        if (prerunValue != null) {
            capabilities.setCapability("prerun", prerunValue);
            setPrerunUrl(null);
        }

        capabilityManager.addCapabilities();
        return capabilities;
    }

    protected void setSauceCapabilities(Map<String, Object> sauceLabsCapabilities) {
        sauceLabsCapabilities.forEach(this::setSauceCapability);
    }

    protected void setSauceCapability(String key, Object value) {
        if ("jobVisibility".equals(key)) {
            capabilityManager.capabilityValidator("JobVisibility", JobVisibility.keys(), (String) value);
            setJobVisibility(JobVisibility.valueOf(JobVisibility.fromString((String) value)));
        } else if ("prerun".equals(key)) {
            Map<Prerun, Object> prerunMap = new HashMap<>();
            ((Map) value).forEach((oldKey, val) -> {
                capabilityManager.capabilityValidator("Prerun", Prerun.keys(), (String) oldKey);
                String keyString = Prerun.fromString((String) oldKey);
                prerunMap.put(Prerun.valueOf(keyString), val);
            });
            setPrerun(prerunMap);
        } else {
            try {
                Class<?> type = SauceLabsOptions.class.getDeclaredField(key).getType();
                String setter = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
                Method method = SauceLabsOptions.class.getDeclaredMethod(setter, type);
                method.invoke(this, value);
            } catch (NoSuchFieldException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBuild() {
        if (build != null) {
            return build;
        } else if (systemManager.getEnv(knownCITools.get("Jenkins")) != null) {
            return systemManager.getEnv("BUILD_NAME") + ": " + systemManager.getEnv("BUILD_NUMBER");
        } else if (systemManager.getEnv(knownCITools.get("Bamboo")) != null) {
            return systemManager.getEnv("bamboo_shortJobName") + ": " + systemManager.getEnv("bamboo_buildNumber");
        } else if (systemManager.getEnv(knownCITools.get("Travis")) != null) {
            return systemManager.getEnv("TRAVIS_JOB_NAME") + ": " + systemManager.getEnv("TRAVIS_JOB_NUMBER");
        } else if (systemManager.getEnv(knownCITools.get("Circle")) != null) {
            return systemManager.getEnv("CIRCLE_JOB") + ": " + systemManager.getEnv("CIRCLE_BUILD_NUM");
        } else if (systemManager.getEnv(knownCITools.get("GitLab")) != null) {
            return systemManager.getEnv("CI_JOB_NAME") + ": " + systemManager.getEnv("CI_JOB_ID");
        } else if (systemManager.getEnv(knownCITools.get("TeamCity")) != null) {
            return systemManager.getEnv("TEAMCITY_PROJECT_NAME") + ": " + systemManager.getEnv("BUILD_NUMBER");
        } else {
            return "Build Time: " + System.currentTimeMillis();
        }
    }

    public boolean isKnownCI() {
        return !knownCITools.keySet().stream().allMatch((key) -> systemManager.getEnv(key) == null);
    }

    public static final Map<String, String> knownCITools;

    static {
        knownCITools = new HashMap<>();
        knownCITools.put("Jenkins", "BUILD_TAG");
        knownCITools.put("Bamboo", "bamboo_agentId");
        knownCITools.put("Travis", "TRAVIS_JOB_ID");
        knownCITools.put("Circle", "CIRCLE_JOB");
        knownCITools.put("GitLab", "CI");
        knownCITools.put("TeamCity", "TEAMCITY_PROJECT_NAME");
    }

    protected String getSauceUsername() {
        return systemManager.get("SAUCE_USERNAME", "Sauce Username was not provided");
    }

    protected String getSauceAccessKey() {
        return systemManager.get("SAUCE_ACCESS_KEY", "Sauce Access Key was not provided");
    }
}
