package com.saucelabs.simplesauce.examples;

import com.saucelabs.simplesauce.SauceSession;
import com.saucelabs.simplesauce.TestResult;

import java.net.MalformedURLException;

public class BasicUsage {

    public static void main(String[] argv) throws MalformedURLException {
        SauceSession sauce = new SauceSession();

        sauce.start();

        sauce.getWebDriver().get("https://www.saucedemo.com/");

        sauce.stop(TestResult.PASS);
    }
}
