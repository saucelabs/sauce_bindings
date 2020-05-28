package com.saucelabs.saucebindings;

import io.appium.java_client.android.AndroidOptions;
import io.appium.java_client.ios.IOSOptions;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.openqa.selenium.MutableCapabilities;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class SauceMobileOptionsTest {
    private SauceOptions sauceOptions;

    @Rule
    public MockitoRule initRule = MockitoJUnit.rule();

    @Test
    public void staticAndroidDefaultsToAndroid(){
        sauceOptions = SauceOptions.android();

        assertEquals(Browser.CHROME, sauceOptions.getBrowserName());
        assertEquals(SaucePlatform.ANDROID, sauceOptions.getPlatformName());
        assertEquals("Android GoogleAPI Emulator", sauceOptions.getDeviceName());
        assertEquals("8.1", sauceOptions.getPlatformVersion());
    }

    @Test
    public void staticIOSDefaultsToIOS(){
        sauceOptions = SauceOptions.ios();

        assertEquals(Browser.SAFARI, sauceOptions.getBrowserName());
        assertEquals(SaucePlatform.IOS, sauceOptions.getPlatformName());
        assertEquals("iPhone Simulator", sauceOptions.getDeviceName());
        assertEquals("13.2", sauceOptions.getPlatformVersion());
    }

    @Test
    public void acceptsMobileSpecificSettings() {
        sauceOptions = SauceOptions.android();

        sauceOptions.setApp("http://path.com/to/app");
        sauceOptions.setAppiumVersion("1.17.0");
        sauceOptions.setAppActivity(".MainActivity");
        sauceOptions.setAppPackage("com.example.android.myApp, com.android.settings");
        sauceOptions.setAutoAcceptAlerts(true);
        sauceOptions.setAutomationName(SauceAutomationName.UIAUTOMATOR2);
        sauceOptions.setDeviceName("Google Nexus 7 HD Emulator");
        sauceOptions.setDeviceOrientation(DeviceOrientation.LANDSCAPE);
        sauceOptions.setDeviceType(DeviceType.TABLET);
        sauceOptions.setPlatformVersion("9.1");

        assertEquals("http://path.com/to/app", sauceOptions.getApp());
        assertEquals("1.17.0", sauceOptions.getAppiumVersion());
        assertEquals(".MainActivity", sauceOptions.getAppActivity());
        assertEquals("com.example.android.myApp, com.android.settings", sauceOptions.getAppPackage());
        assertTrue(sauceOptions.getAutoAcceptAlerts());
        assertEquals(SauceAutomationName.UIAUTOMATOR2, sauceOptions.getAutomationName());
        assertEquals("Google Nexus 7 HD Emulator", sauceOptions.getDeviceName());
        assertEquals(DeviceOrientation.LANDSCAPE, sauceOptions.getDeviceOrientation());
        assertEquals(DeviceType.TABLET, sauceOptions.getDeviceType());
        assertEquals("9.1", sauceOptions.getPlatformVersion());
    }

    @Test
    public void acceptsAndroidOptionsClass() {
        AndroidOptions androidOptions = new AndroidOptions();
        androidOptions.setEnablePerformanceLogging();

        sauceOptions = SauceOptions.android(androidOptions);

        assertEquals(SaucePlatform.ANDROID, sauceOptions.getPlatformName());
        assertEquals(androidOptions, sauceOptions.getSeleniumCapabilities());
    }

    @Test
    public void acceptsIOSOptionsClass() {
        IOSOptions iOSOptions = new IOSOptions();
        iOSOptions.setEnablePerformanceLogging();

        sauceOptions = SauceOptions.ios(iOSOptions);

        assertEquals(SaucePlatform.IOS, sauceOptions.getPlatformName());
        assertEquals(iOSOptions, sauceOptions.getSeleniumCapabilities());
    }

    // This is important to make sure serialized values can be converted into type safe option keys
    @Test
    public void setsCapabilitiesFromMap() throws FileNotFoundException {
        InputStream input = new FileInputStream(new File("src/test/java/com/saucelabs/saucebindings/options.yml"));
        Yaml yaml = new Yaml();
        Map<String, Object> data = yaml.load(input);
        Map<String, Object> map = (Map<String, Object>) data.get("mobileValues");

        sauceOptions = SauceOptions.android();
        sauceOptions.mergeCapabilities(map);

        assertEquals("http://path.com/to/app", sauceOptions.getApp());
        assertEquals("1.17.0", sauceOptions.getAppiumVersion());
        assertEquals(".MainActivity", sauceOptions.getAppActivity());
        assertEquals("com.example.android.myApp, com.android.settings", sauceOptions.getAppPackage());
        assertTrue(sauceOptions.getAutoAcceptAlerts());
        assertEquals(SauceAutomationName.UIAUTOMATOR2, sauceOptions.getAutomationName());
        assertEquals("Google Nexus 7 HD Emulator", sauceOptions.getDeviceName());
        assertEquals(DeviceOrientation.LANDSCAPE, sauceOptions.getDeviceOrientation());
        assertEquals(DeviceType.TABLET, sauceOptions.getDeviceType());
        assertEquals("9.1", sauceOptions.getPlatformVersion());
    }

    @Test
    public void parsesMobileCapabilities() {
        MutableCapabilities androidCaps = new MutableCapabilities();
        androidCaps.setCapability("autoWebView", true);
        AndroidOptions androidOptions = new AndroidOptions(androidCaps);
        androidOptions.setEnablePerformanceLogging();
        androidOptions.setOtherApps("path/to/app.apk");

        sauceOptions = spy(SauceOptions.android(androidOptions));

        doReturn("test-name").when(sauceOptions).getEnvironmentVariable("SAUCE_USERNAME");
        doReturn("test-accesskey").when(sauceOptions).getEnvironmentVariable("SAUCE_ACCESS_KEY");

        sauceOptions.setBuild("CUSTOM BUILD: 12");

        sauceOptions.setApp("http://path.com/to/app");
        sauceOptions.setAppiumVersion("1.17.0");
        sauceOptions.setAppActivity(".MainActivity");
        sauceOptions.setAppPackage("com.example.android.myApp, com.android.settings");
        sauceOptions.setAutoAcceptAlerts(true);
        sauceOptions.setAutomationName(SauceAutomationName.UIAUTOMATOR2);
        sauceOptions.setDeviceName("Google Nexus 7 HD Emulator");
        sauceOptions.setDeviceOrientation(DeviceOrientation.LANDSCAPE);
        sauceOptions.setDeviceType(DeviceType.TABLET);
        sauceOptions.setPlatformVersion("9.1");

        MutableCapabilities sauceCapabilities = new MutableCapabilities();
        sauceCapabilities.setCapability("username", "test-name");
        sauceCapabilities.setCapability("accessKey", "test-accesskey");
        sauceCapabilities.setCapability("build", "CUSTOM BUILD: 12");
        sauceCapabilities.setCapability("appiumVersion", "1.17.0");
        sauceCapabilities.setCapability("deviceType", "tablet");

        MutableCapabilities expectedCapabilities = new MutableCapabilities();
        expectedCapabilities.setCapability("browserName", "chrome");
        expectedCapabilities.setCapability("platformName", "android");
        expectedCapabilities.setCapability("sauce:options", sauceCapabilities);
        expectedCapabilities.setCapability("appium:deviceName", "Android GoogleAPI Emulator");
        expectedCapabilities.setCapability("appium:platformVersion", "8.1");
        expectedCapabilities.setCapability("appium:app", "http://path.com/to/app");
        expectedCapabilities.setCapability("appium:appActivity", ".MainActivity");
        expectedCapabilities.setCapability("appium:appPackage", "com.example.android.myApp, com.android.settings");
        expectedCapabilities.setCapability("appium:autoAcceptAlerts", true);
        expectedCapabilities.setCapability("appium:automationName", "UiAutomator2");
        expectedCapabilities.setCapability("appium:deviceName", "Google Nexus 7 HD Emulator");
        expectedCapabilities.setCapability("appium:deviceOrientation", "landscape");
        expectedCapabilities.setCapability("appium:platformVersion", "9.1");

        expectedCapabilities.setCapability("appium:autoWebView", true);
        expectedCapabilities.setCapability("appium:enablePerformanceLogging", true);
        expectedCapabilities.setCapability("appium:otherApps", "path/to/app.apk");

        MutableCapabilities actualCapabilities = sauceOptions.toCapabilities();

        // toString() serializes the enums
        assertEquals(expectedCapabilities.asMap().toString(), actualCapabilities.asMap().toString());
    }
}
