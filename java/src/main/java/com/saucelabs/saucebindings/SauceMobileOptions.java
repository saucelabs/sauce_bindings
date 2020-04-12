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
    private String deviceName = "Android GoogleAPI Emulator";
    private String deviceOrientation = "portrait";
    private String platformVersion = "8.1";

    // Supported by Sauce
    private String appiumVersion = "1.15.0";

    public static final List<String> mobileW3COptions = List.of(
            "browserName",
            "platformName");

    public static final List<String> mobileSauceDefinedOptions = List.of(
            "appiumVersion");

    public static final List<String> appiumDefinedOptions = List.of(
            "deviceName",
            "platformVersion",
            "deviceOrientation");

    public SauceMobileOptions() {
        this(new MutableCapabilities());
    }

    private SauceMobileOptions(MutableCapabilities options) {
        appiumCapabilities = new MutableCapabilities(options.asMap());
    }

    public MutableCapabilities toCapabilities(boolean w3c) {
        mobileW3COptions.forEach((capability) -> {
            addCapabilityIfDefined(appiumCapabilities, capability);
        });

        if (w3c) {
            useW3cCapabilities();
        } else {
            useJwpCapabilities();
        }
        return appiumCapabilities;
    }

    private void useW3cCapabilities() {
        MutableCapabilities sauceCapabilities = new MutableCapabilities();
        addAuthentication(sauceCapabilities);

        appiumDefinedOptions.forEach((capability) -> {
            addAppiumCapabilityIfDefined(appiumCapabilities, capability);
        });

        mobileSauceDefinedOptions.forEach((capability) -> {
            addCapabilityIfDefined(sauceCapabilities, capability);
        });

        appiumCapabilities.setCapability("sauce:options", sauceCapabilities);
    }

    private void useJwpCapabilities() {
        addAuthentication(appiumCapabilities);

        appiumDefinedOptions.forEach((capability) -> {
            addCapabilityIfDefined(appiumCapabilities, capability);
        });

        mobileSauceDefinedOptions.forEach((capability) -> {
            addCapabilityIfDefined(appiumCapabilities, capability);
        });
    }

    private void addAppiumCapabilityIfDefined(MutableCapabilities capabilities, String capability) {
        Object value = getCapability(capability);
        if (value != null) {
            capabilities.setCapability("appium:" + capability, value);
        }
    }
}
