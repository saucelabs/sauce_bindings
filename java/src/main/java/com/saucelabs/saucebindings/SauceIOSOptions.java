package com.saucelabs.saucebindings;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.openqa.selenium.MutableCapabilities;

@Accessors(chain = true)
@Setter @Getter
public class SauceIOSOptions extends SauceOptions {

    // minimal configuration
    private DeviceOrientation deviceOrientation;
    private String deviceName;
    private String platformVersion;

    public SauceIOSOptions(String deviceName, String platformVersion, DeviceOrientation deviceOrientation){
        super();
        this.platformName = SaucePlatform.IOS;
        this.browserName = Browser.NONE;
        this.platformVersion = "";
        this.deviceName = deviceName;
        this.platformVersion = platformVersion;
        this.deviceOrientation = deviceOrientation;
    }

    public SauceIOSOptions(MutableCapabilities capabilities){
        super(capabilities);
    }
}
