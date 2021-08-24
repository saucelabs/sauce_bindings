---
id: configurations
title: Configuration Overview
sidebar_label: Overview
---

Sauce Bindings are designed to ensure sessions conform to the [W3C WebDriver Standard](https://www.w3.org/TR/webdriver/).
 
Talk about why this is an issue.

If you do not do any configuring, Sauce Bindings will give you a Chrome session with the latest version, on Windows 10
on the West Coast Data Center.

Things you may wish to configure:
1. [Common Options](BASIC_OPTIONS.md) - these include:
    * [Required W3C Capabilities](https://docs.saucelabs.com/dev/test-configuration-options/index.html#webdriver-w3c-capabilities--required)
   (browser name, browser version, platform name)
    * [Optional W3C Capabilities](https://docs.saucelabs.com/dev/test-configuration-options/index.html#browser-w3c-capabilities--optional)
   (e.g., accept insecure certificates, page load strategy, prompt behavior, etc)

2. [Sauce Labs Options](SAUCE_OPTIONS.md) - these include:
   * [Browser Capabilities](https://docs.saucelabs.com/dev/test-configuration-options/index.html#desktop-browser-capabilities-sauce-specific--optional)
     (e.g., driver versions, selenium server version, extended debugging, etc)
   * [Unified Platform Capabilities](https://docs.saucelabs.com/dev/test-configuration-options/index.html#desktop-and-mobile-capabilities-sauce-specific--optional) 
     (e.g., test information, artifact handling, sauce connect settings, etc)
   * [Virtual Device Capabilities](https://docs.saucelabs.com/dev/test-configuration-options/index.html#virtual-device-capabilities-sauce-specific--optional) 
     (e.g. timeouts, timezone, prerun executable, etc)

3. [Browser Specific Options](BROWSER_OPTIONS.md) - these are capabilities that are exclusive to specific browsers
    * [Chrome Capabilities](https://chromedriver.chromium.org/capabilities)
    * [Microsoft Edge Capabilities](https://docs.microsoft.com/en-us/microsoft-edge/webdriver-chromium/capabilities-edge-options)
    * [Firefox Capabilities](https://developer.mozilla.org/en-US/docs/Web/WebDriver/Capabilities/firefoxOptions)
    * [Internet Explorer Capabilities](https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/ie/InternetExplorerDriver.html)
    * [Safari Capabilities](https://developer.apple.com/documentation/webkit/about_webdriver_for_safari)

4. [Data Center](DATA_CENTER.md) - there are 4 different data centers to choose from:
    * US West ("https://ondemand.us-west-1.saucelabs.com/wd/hub")
    * US East ("https://ondemand.us-east-1.saucelabs.com/wd/hub")
    * EU Central ("https://ondemand.eu-central-1.saucelabs.com/wd/hub"),
    * Asia Pacific ("https://ondemand.apac-southeast-1.saucelabs.com/wd/hub");
