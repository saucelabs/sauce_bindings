---
title: Test Configuration
sidebar_position: 1
---

## Introduction

 The [three capabilities](https://docs.saucelabs.com/dev/test-configuration-options/index.html#webdriver-w3c-capabilities--required) that matter most to Sauce Labs users are: 
* `browserName`
* `browserVersion`
* `platformName`

By default, Sauce Bindings provides the latest version of Google Chrome on Windows 10.

To see what values are supported by Sauce Labs for these 3 capabilities, take a look at our
[Platform Configurator](https://saucelabs.com/platform/platform-configurator#/)

Additionally, there are [settings that apply to all browser sessions](https://docs.saucelabs.com/dev/test-configuration-options/index.html#browser-w3c-capabilities--optional) 
that can be configured in `SauceOptions`

Here's an example of running a test on Firefox and Windows 8.0, that accepts insecure certificates and changes
the unhandled prompt behavior to `"ignore"`:

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';


<Tabs
defaultValue="junit5"
values={[
{ label: 'JUnit 5', value: 'junit5', },
{ label: 'JUnit 4', value: 'junit4', },
{ label: 'TestNG', value: 'testng', }
]
}>

<TabItem value="junit5">

```java reference
https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/java/junit5/src/test/java/com/saucelabs/saucebindings/junit5/examples/CommonOptionsTest.java
```

</TabItem>
<TabItem value="junit4">

```java reference
https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/java/junit4/src/test/java/com/saucelabs/saucebindings/junit4/examples/CommonOptionsTest.java
```

</TabItem>
<TabItem value="testng">

```java reference
https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/java/testng/src/test/java/com/saucelabs/saucebindings/testng/examples/CommonOptionsTest.java
```

</TabItem>
</Tabs>

