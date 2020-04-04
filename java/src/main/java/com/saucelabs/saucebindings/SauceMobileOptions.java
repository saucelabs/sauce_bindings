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

    private Browser browserName = Browser.CHROME;
    private String appiumVersion = "1.15.0";
    private String deviceName = "Android GoogleAPI Emulator";
    private String deviceOrientation = "portrait";
    private String platformVersion = "8.1";
    private SaucePlatform platformName = SaucePlatform.ANDROID;

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

    public MutableCapabilities toCapabilities() {
        MutableCapabilities sauceCapabilities = addAuthentication();

        mobileW3COptions.forEach((capability) -> {
            addCapabilityIfDefined(appiumCapabilities, capability);
        });

        appiumDefinedOptions.forEach((capability) -> {
            addAppiumCapabilityIfDefined(appiumCapabilities, capability);
        });

        mobileSauceDefinedOptions.forEach((capability) -> {
            addCapabilityIfDefined(sauceCapabilities, capability);
        });

        appiumCapabilities.setCapability("sauce:options", sauceCapabilities);

        return appiumCapabilities;
    }

    void addAppiumCapabilityIfDefined(MutableCapabilities capabilities, String capability) {
        Object value = getCapability(capability);
        if (value != null) {
            capabilities.setCapability("appium:" + capability, value);
        }
    }
}
