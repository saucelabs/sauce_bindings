package com.saucelabs.saucebindings.examples;

import com.saucelabs.saucebindings.SauceSession;

public class BasicUsage {

    public static void main(String[] argv){
        SauceSession sauce = new SauceSession();

        sauce.start();

        sauce.getWebDriver().get("https://www.saucedemo.com/");

        sauce.stop(true);
    }
}
