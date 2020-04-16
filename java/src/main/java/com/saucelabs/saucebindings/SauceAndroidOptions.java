package com.saucelabs.saucebindings;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.openqa.selenium.MutableCapabilities;

@Accessors(chain = true)
@Setter @Getter
public class SauceAndroidOptions extends SauceOptions {

    // minimal configuration
    private DeviceOrientation deviceOrientation;
    private String deviceName;
    private String platformVersion;

    public SauceAndroidOptions(String deviceName, String platformVersion, DeviceOrientation deviceOrientation){
        super();
        this.platformName = SaucePlatform.ANDROID;
        this.browserName = Browser.NONE;
        this.browserVersion = "";
        this.deviceName = deviceName;
        this.platformVersion = platformVersion;
        this.deviceOrientation = deviceOrientation;
    }

    public SauceAndroidOptions(MutableCapabilities capabilities){
        super(capabilities);
    }
}
