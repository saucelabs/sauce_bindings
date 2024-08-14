---
title: Existing Test Suite Setup
sidebar_position: 1
---

# Add Sauce Bindings to Your Existing Test Suite

Follow the instructions in the code examples.

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

<Tabs
defaultValue="junit5"
values={[
{ label: 'JUnit 5', value: 'junit5', },
{ label: 'JUnit 4', value: 'junit4', },
{ label: 'TestNG', value: 'testng', },
]
}>

<TabItem value="junit5">

```java
package com.saucelabs.saucebindings.junit5.examples;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.junit5.SauceBindingsExtension;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariOptions;

public class CapabilitiesExample {
  WebDriver driver;
  SauceSession session;

  //1. Wrap your capabilities creation in a static getCapabilities()
  private static Capabilities getCapabilities() {
    SafariOptions browserOptions = new SafariOptions();
    browserOptions.setPlatformName("macOS 12");
    browserOptions.setBrowserVersion("latest");
    Map<String, Object> sauceOptions = new HashMap<>();
    sauceOptions.put("idleTimeout", 30);
    browserOptions.setCapability("sauce:options", sauceOptions);
    return browserOptions;
  }

  //2. Register SauceBindingsExtension
  @RegisterExtension
  static SauceBindingsExtension sauceExtension = new SauceBindingsExtension(getCapabilities());

  //3. Store the session and driver in a @BeforeEach
  @BeforeEach
  public void storeVariables() {
    session = sauceExtension.getSession();
    driver = sauceExtension.getDriver();
  }

  //4. Interact with your test as before
  @Test
  public void capabilitiesExample() {
    // Use the session to interact with Sauce Labs functionality
    session.annotate("Navigating to Swag Labs");
    // Use the driver to interact with your browser
    driver.get("https://www.saucedemo.com/");
  }
}
```

</TabItem>
<TabItem value="junit4">

```java reference
Coming soon
```

</TabItem>
<TabItem value="testng">

```java reference
Coming soon
```

</TabItem>
</Tabs>
