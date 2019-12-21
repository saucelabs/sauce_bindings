package com.saucelabs.simplesauce;

import com.saucelabs.simplesauce.enums.MacVersion;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.remote.BrowserType;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

public class SauceOptions {
    @Getter
    @Setter
    private MutableCapabilities seleniumOptions;

    @Getter
    @Setter
    private String browserName = BrowserType.CHROME;
    @Getter
    @Setter
    private String browserVersion = "latest";
    @Getter
    @Setter
    private String platformName = "Windows 10";
    @Getter
    @Setter
    private PageLoadStrategy pageLoadStrategy;
    @Getter
    @Setter
    private Boolean acceptInsecureCerts = null;
    @Getter
    @Setter
    private Proxy proxy;
    @Getter
    @Setter
    private Boolean setWindowRect = null;
    @Getter
    @Setter
    private Map<String, Integer> timeouts;
    @Getter
    @Setter
    private Boolean strictFileInteractability = null;
    @Getter
    @Setter
    private UnexpectedAlertBehaviour unhandledPromptBehavior;

    @Getter
    @Setter
    private String accessKey;
    @Getter
    @Setter
    private String avoidProxy;
    @Setter
    private String build;
    @Getter
    @Setter
    private String captureHtml;
    @Getter
    @Setter
    private String capturePerformance;
    @Getter
    @Setter
    private String chromedriverVersion;
    @Getter
    @Setter
    private Integer commandTimeout = null;
    @Getter
    @Setter
    private String crmuxdriverVersion;
    @Getter
    @Setter
    private String customData;
    @Getter
    @Setter
    private String disablePopupHandler;
    @Getter
    @Setter
    private String extendedDebugging;
    @Getter
    @Setter
    private String firefoxAdapterVersion;
    @Getter
    @Setter
    private String firefoxProfileUrl;
    @Getter
    @Setter
    private String idleTimeout;
    @Getter
    @Setter
    private String iedriverVersion;
    @Getter
    @Setter
    private Integer maxDuration = null;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String parentTunnel;
    @Getter
    @Setter
    private String passed;
    @Getter
    @Setter
    private String prerun;
    @Getter
    @Setter
    private String preventRequeu;
    @Getter
    @Setter
    private String priority;
    @Getter
    @Setter
    private String proxyHost;
    // TODO - how do we handle this one?
    @Getter
    @Setter
    private String Public;
    @Getter
    @Setter
    private String recordLogs;
    @Getter
    @Setter
    private String recordStreenshots;
    @Getter
    @Setter
    private String recordVideo;
    @Getter
    @Setter
    private String restrictedPublicInfo;
    @Getter
    @Setter
    private String screenResolution;
    @Getter
    @Setter
    private String seleniumVersion;
    @Getter
    @Setter
    private String source;
    @Getter
    @Setter
    private String tags;
    @Getter
    @Setter
    private String timeZone;
    @Getter
    @Setter
    private String tunnelIdentifier;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String videoUploadOnPass;

    public static <E extends Enum<E>> boolean isInEnum(String value, Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants()).anyMatch(e -> e.name().equals(value));
    }

    public MutableCapabilities toCapabilities() {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        MutableCapabilities sauceOptions = new MutableCapabilities();
        MutableCapabilities sauceCapabilities = new MutableCapabilities();

        Arrays.stream(this.getClass().getDeclaredFields()).forEach(field -> {
            try {
                String fieldName = field.getName();
                if (!fieldName.equals("seleniumOptions")) {
                    String getter = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method declaredMethod = SauceOptions.class.getDeclaredMethod(getter);
                    Object result = declaredMethod.invoke(this);
                    if (result == null) {
                        // noop
                    } else if (isInEnum(fieldName, OptionsEnums.w3c.class)) {
                        mutableCapabilities.setCapability(fieldName, result);
                    } else if (isInEnum(fieldName, OptionsEnums.sauce.class)) {
                        sauceOptions.setCapability(fieldName, result);
                    }
                }
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        });

        sauceCapabilities.setCapability("sauce:options", sauceOptions);
        mutableCapabilities.merge(sauceCapabilities);
        mutableCapabilities.merge(getSeleniumOptions());

        return mutableCapabilities;
    }

    public String getBuild() {
        // Jenkins
        if (System.getenv("BUILD_TAG") != null) {
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
        } else if (System.getenv("TEAMCITY_VERSION") != null) {
            return System.getenv("TEAMCITY_PROJECT_NAME") + ": " + System.getenv("BUILD_NUMBER");
            // Default
        } else {
            return "Build Time: " + Long.toString(System.currentTimeMillis());
        }
    }

    public SauceOptions withChrome() {
        browserName = BrowserType.CHROME;
        return this;
    }

    public SauceOptions withSafari() {
        return withMac(MacVersion.Mojave);
    }

    public SauceOptions withSafari(SafariVersion version) {
        browserName = BrowserType.SAFARI;
        browserVersion = version.getVersion();
        return this;
    }

    public SauceOptions withSafari(final String version) {
        String _version = version;
        if (_version.isEmpty()) {
            _version = "latest";
        }
        browserName = BrowserType.SAFARI;
        browserVersion = _version;
        return this;
    }

    public SauceOptions withLinux() {
        platformName = "Linux";
        return this;
    }

    public SauceOptions withWindows10() {
        platformName = "windows 10";
        return this;
    }

    public SauceOptions withWindows8_1() {
        platformName = "Windows 8.1";
        return this;
    }

    public SauceOptions withWindows8() {
        platformName = "Windows 8";
        return this;
    }

    public SauceOptions withWindows7() {
        platformName = "Windows 7";
        return this;
    }

    public SauceOptions withEdge() {
        browserName = "Edge";
        browserVersion = "18.17763";
        return this;
    }

    //TODO notice the duplication below with edge.
    //Maybe could be moved to a separate class so we can do withEdge().16_16299();
    //Or withEdge().version(EdgeVersion.16_16299);
    public SauceOptions withEdge18() {
        withEdge();
        browserVersion = "18.17763";
        return this;
    }

    public SauceOptions withEdge17() {
        withEdge();
        browserVersion = "17.17134";
        return this;
    }

    public SauceOptions withEdge16() {
        withEdge();
        browserVersion = "16.16299";
        return this;
    }

    public SauceOptions withEdge15() {
        withEdge();
        browserVersion = "15.15063";
        return this;
    }

    public SauceOptions withEdge14() {
        withEdge();
        browserVersion = "14.14393";
        return this;
    }

    public SauceOptions withEdge13() {
        withEdge();
        browserVersion = "13.10586";
        return this;
    }

    public SauceOptions withFirefox() {
        browserName = "Firefox";
        return this;
    }

    public SauceOptions withIE(IEVersion version) {
        this.browserVersion = version.getVersion();
        return this;
    }

    public SauceOptions withIE(String version) {
        browserName = "IE";
        browserVersion = version;
        return this;
    }

    public SauceOptions withIE() {
        browserName = "IE";
        browserVersion = "latest";
        return this;
    }

    public SauceOptions withMac(MacVersion macVersion) {
        platformName = macVersion.label;
        browserName = "Safari";
        return this;
    }
}
