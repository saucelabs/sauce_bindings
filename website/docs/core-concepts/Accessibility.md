---
title: Accessibility Testing
---

As of version 1.2, Sauce Bindings supports the new Sauce Labs
[Deque axe™ Integration](https://docs.saucelabs.com/basics/integrations/deque/index.html)

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

<Tabs
defaultValue="java"
values={[
{ label: 'Java', value: 'java', }
]
}>

<TabItem value="java">

<Tabs
defaultValue="junit5"
values={[
{ label: 'JUnit 5', value: 'junit5', }
]
}>

<TabItem value="junit5">

```java reference
import com.deque.html.axecore.results.Results;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.junit5.SauceBindingsExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;

public class AccessibilityExample {
  WebDriver driver;
  SauceSession session;

  @RegisterExtension static SauceBindingsExtension sauceExtension = new SauceBindingsExtension();

  @BeforeEach
  public void storeVariables() {
    session = sauceExtension.getSession();
    driver = sauceExtension.getDriver();
  }

  @Test
  public void accessibilityExample() {
    driver.get("https://www.saucedemo.com/");
    Results accessibilityResults = session.getAccessibilityResults();
  }
}

```

</TabItem>
<TabItem value="junit4">

```java reference
https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/java/junit4/src/test/java/com/saucelabs/saucebindings/junit4/examples/AccessibilityTest.java
```

</TabItem>
</Tabs>

</TabItem>
</Tabs>
