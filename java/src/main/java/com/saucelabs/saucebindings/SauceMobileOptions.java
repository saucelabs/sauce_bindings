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
    private String app;
    private String deviceName;
    private String deviceOrientation = "portrait";
    private String platformVersion = "9";

    // Supported by Sauce
    private String appiumVersion = "1.15.0";

    public static final List<String> mobileW3COptions = List.of(
            "browserName",
            "platformName");

    public static final List<String> mobileSauceDefinedOptions = List.of(
            "appiumVersion");

    public static final List<String> appiumDefinedOptions = List.of(
            "app",
            "deviceName",
            "platformVersion",
            "deviceOrientation");

    public SauceMobileOptions() {
        this(new MutableCapabilities());
    }

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

        appiumCapabilities.setCapability("sauce:options", sauceCapabilities);
    }

    private void useJwpCapabilities(boolean to) {
        if (to) {
            appiumCapabilities.setCapability("testobject_api_key", getTestObjectKey());
            if (deviceName == null) {
                this.deviceName = "Google Pixel XL";
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

    String getTestObjectKey() {
        if (getSystemProperty("TEST_OBJECT_KEY") != null) {
            return getSystemProperty("TEST_OBJECT_KEY");
        } else if (getEnvironmentVariable("TEST_OBJECT_KEY") != null) {
            return getEnvironmentVariable("TEST_OBJECT_KEY");
        } else {
            throw new SauceEnvironmentVariablesNotSetException("Test Object API Key was not provided");
        }
    }
}
