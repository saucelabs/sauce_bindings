package com.saucelabs.simplesauce.examples;

import com.saucelabs.simplesauce.SauceSession;
import org.openqa.selenium.WebDriver;

public class BasicUsage {

    public static void main(String[] argv){
        SauceSession sauce = new SauceSession();

        sauce.start();

        sauce.getDriver().get("https://www.saucedemo.com/");

        sauce.stop();
    }
}
