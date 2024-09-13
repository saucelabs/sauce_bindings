---
title: First Test
sidebar_position: 2
---

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

<Tabs
defaultValue="junit5"
values={[
{ label: 'JUnit 5', value: 'junit5', }
]
}>

<TabItem value="junit5">

```java reference
package com.saucelabs.saucebindings.junit5.examples;

import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.junit5.SauceBindingsExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;

public class QuickStartExample {
  WebDriver driver;
  SauceSession session;

  // Register SauceBindingsExtension
  @RegisterExtension static SauceBindingsExtension sauceExtension = new SauceBindingsExtension();

  // Store the session and driver objects
  @BeforeEach
  public void storeVariables() {
    session = sauceExtension.getSession();
    driver = sauceExtension.getDriver();
  }


  @Test
  public void quickStartExample() {
    // Use session to interact with Sauce Labs
    session.annotate("Navigating to Swag Labs");
    // Use the driver to interact with the web as normal
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
