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
public class SauceLabsOptions extends SauceOptions {
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


    public static final List<String> sauceLabsOptions = Arrays.asList(
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

    public MutableCapabilities toCapabilities() {
        MutableCapabilities sauceLabsCapabilities = addAuthentication();

        sauceLabsOptions.forEach((capability) -> {
            if ("prerunUrl".equals(capability)) {
                sauceLabsCapabilities.setCapability("prerun", getCapability("prerunUrl"));
            } else if ("jobVisibility".equals(capability)) {
                sauceLabsCapabilities.setCapability("public", getCapability("jobVisibility"));
            } else {
                addCapabilityIfDefined(sauceLabsCapabilities, capability);
            }
        });

        return sauceLabsCapabilities;
    }

    protected void setSauceCapabilities(Map<String, Object> sauceLabsCapabilities) {
        sauceLabsCapabilities.forEach(this::setSauceCapability);
    }

    protected void setSauceCapability(String key, Object value) {
        if ("jobVisibility".equals(key)) {
            enumValidator("JobVisibility", JobVisibility.keys(), (String) value);
            setJobVisibility(JobVisibility.valueOf(JobVisibility.fromString((String) value)));
        } else if ("prerun".equals(key)) {
            Map<Prerun, Object> prerunMap = new HashMap<>();
            ((Map) value).forEach((oldKey, val) -> {
                enumValidator("Prerun", Prerun.keys(), (String) oldKey);
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
        } else if (getEnvironmentVariable(knownCITools.get("Jenkins")) != null) {
            return getEnvironmentVariable("BUILD_NAME") + ": " + getEnvironmentVariable("BUILD_NUMBER");
        } else if (getEnvironmentVariable(knownCITools.get("Bamboo")) != null) {
            return getEnvironmentVariable("bamboo_shortJobName") + ": " + getEnvironmentVariable("bamboo_buildNumber");
        } else if (getEnvironmentVariable(knownCITools.get("Travis")) != null) {
            return getEnvironmentVariable("TRAVIS_JOB_NAME") + ": " + getEnvironmentVariable("TRAVIS_JOB_NUMBER");
        } else if (getEnvironmentVariable(knownCITools.get("Circle")) != null) {
            return getEnvironmentVariable("CIRCLE_JOB") + ": " + getEnvironmentVariable("CIRCLE_BUILD_NUM");
        } else if (getEnvironmentVariable(knownCITools.get("GitLab")) != null) {
            return getEnvironmentVariable("CI_JOB_NAME") + ": " + getEnvironmentVariable("CI_JOB_ID");
        } else if (getEnvironmentVariable(knownCITools.get("TeamCity")) != null) {
            return getEnvironmentVariable("TEAMCITY_PROJECT_NAME") + ": " + getEnvironmentVariable("BUILD_NUMBER");
        } else {
            return "Build Time: " + System.currentTimeMillis();
        }
    }

    public boolean isKnownCI() {
        return !knownCITools.keySet().stream().allMatch((key) -> getEnvironmentVariable(key) == null);
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

    private MutableCapabilities addAuthentication() {
        MutableCapabilities caps = new MutableCapabilities();
        caps.setCapability("username", getSauceUsername());
        caps.setCapability("accessKey", getSauceAccessKey());
        return caps;
    }

    protected String getSauceUsername() {
        return tryToGetVariable("SAUCE_USERNAME", "Sauce Username was not provided");
    }

    protected String getSauceAccessKey() {
        return tryToGetVariable("SAUCE_ACCESS_KEY", "Sauce Access Key was not provided");
    }
}
