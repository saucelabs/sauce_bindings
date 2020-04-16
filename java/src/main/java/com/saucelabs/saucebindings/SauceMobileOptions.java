package com.saucelabs.saucebindings;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.openqa.selenium.MutableCapabilities;

import java.util.List;

@Accessors(chain = true)
@Setter @Getter
public class SauceMobileOptions extends SauceOptions {
    @Setter(AccessLevel.NONE) private MutableCapabilities appiumCapabilities;

    // Defined in W3C
    private Browser browserName = Browser.CHROME;
    private SaucePlatform platformName = SaucePlatform.ANDROID;

    // Defined in Appium
    // These are the only values that are handled by Sauce Labs by default
    // Additional values will be populated by Appium's IOSOptions & AndroidOptions class instances
    private String app;
    private String deviceName;
    private String deviceOrientation = "portrait";
    private String platformVersion = "10";
    private String automationName;

    // Supported by Sauce
    private String appiumVersion = "1.15.0";
    private String deviceType; // "table" or "phone"

    // Supported by Sauce for Real Devices
    private String testobject_app_id;
    private String privateDeviceOnly;
    private String tabletOnly;
    private String phoneOnly;
    private String carrierConnectivityOnly;
    private String recordDeviceVitals;
    private String cacheId;
    private String testobject_test_live_view_url;
    private String testobject_test_report_url;
    private String testobject_test_report_api_url;
    private String testobject_session_creation_timeout;
    private String commandTimeouts;
    private String crosswalkApplication;
    private String autoGrantPermissions;
    private String enableAnimations;
    private String name;

    public static final List<String> mobileW3COptions = List.of(
            "browserName",
            "platformName");

    public static final List<String> mobileSauceDefinedOptions = List.of(
            "appiumVersion",
            "deviceType");

    public static final List<String> appiumDefinedOptions = List.of(
            "app",
            "automationName",
            "deviceName",
            "platformVersion",
            "deviceOrientation");

    public static final List<String> realDeviceSauceDefinedOptions = List.of(
            "testobject_app_id",
            "privateDeviceOnly",
            "tabletOnly",
            "phoneOnly",
            "carrierConnectivityOnly",
            "recordDeviceVitals",
            "cacheId",
            "testobject_test_live_view_url",
            "testobject_test_report_url",
            "testobject_test_report_api_url",
            "testobject_session_creation_timeout",
            "commandTimeouts",
            "crosswalkApplication",
            "autoGrantPermissions",
            "enableAnimations",
            "name");

    public SauceMobileOptions() {
        this(new MutableCapabilities());
    }

    // TODO: require users to work with Appium's MobileOptions class similar to Selenium
    public SauceMobileOptions(MutableCapabilities options) {
        appiumCapabilities = new MutableCapabilities(options.asMap());
    }

    public MutableCapabilities toCapabilities(DataCenter dataCenter) {
        if (app != null) {
            browserName = null;
        }

        mobileW3COptions.forEach((capability) -> {
            addCapabilityIfDefined(appiumCapabilities, capability);
        });

        if (dataCenter.supportsW3C()) {
            useW3cCapabilities();
        } else {
            useJwpCapabilities(dataCenter.isTestObject());
        }
        return appiumCapabilities;
    }

    private void useW3cCapabilities() {
        MutableCapabilities sauceCapabilities = new MutableCapabilities();
        useSaucePlatform(sauceCapabilities);

        appiumDefinedOptions.forEach((capability) -> {
            addAppiumCapabilityIfDefined(appiumCapabilities, capability);
        });

        mobileSauceDefinedOptions.forEach((capability) -> {
            addCapabilityIfDefined(sauceCapabilities, capability);
        });

        sauceDefinedOptions.forEach((capability) -> {
            addCapabilityIfDefined(appiumCapabilities, capability);
        });

        appiumCapabilities.setCapability("sauce:options", sauceCapabilities);
    }

    private void useJwpCapabilities(boolean to) {
        if (to) {
            appiumCapabilities.setCapability("testobject_api_key", getTestObjectKey());
            if (deviceName == null) {
                this.deviceName = "Google Pixel 2";
            }
        } else {
            useSaucePlatform(appiumCapabilities);
        }

        appiumDefinedOptions.forEach((capability) -> {
            addCapabilityIfDefined(appiumCapabilities, capability);
        });

        mobileSauceDefinedOptions.forEach((capability) -> {
            addCapabilityIfDefined(appiumCapabilities, capability);
        });

        realDeviceSauceDefinedOptions.forEach((capability) -> {
            addCapabilityIfDefined(appiumCapabilities, capability);
        });
    }

    private void useSaucePlatform(MutableCapabilities capabilities) {
        if (deviceName == null) {
            this.deviceName = "Android GoogleAPI Emulator";
        }
        addAuthentication(capabilities);
    }

    private void addAppiumCapabilityIfDefined(MutableCapabilities capabilities, String capability) {
        Object value = getCapability(capability);
        if (value != null) {
            capabilities.setCapability("appium:" + capability, value);
        }
    }

    protected String getTestObjectKey() {
        if (getSystemProperty("TEST_OBJECT_KEY") != null) {
            return getSystemProperty("TEST_OBJECT_KEY");
        } else if (getEnvironmentVariable("TEST_OBJECT_KEY") != null) {
            return getEnvironmentVariable("TEST_OBJECT_KEY");
        } else {
            throw new SauceEnvironmentVariablesNotSetException("Test Object API Key was not provided");
        }
    }
}
