---
title: Cross Browser and Platform
sidebar_position: 2
---

## Introduction

Sauce Bindings provides reasonable defaults to make your developer experience easier 👍

### Run a test on latest Chrome

```java reference

https://github.com/saucelabs/sauce_bindings/blob/main/java/junit5/src/test/java/com/saucelabs/saucebindings/junit5/examples/SessionTest.java

```

### Run a test on Safari on Mac

```java reference

https://github.com/saucelabs/sauce_bindings/blob/main/java/junit5/src/test/java/com/saucelabs/saucebindings/junit5/examples/SessionTest.java

```

Numerous configuration options are available for your tests.

See [Platform Configurator](https://saucelabs.com/platform/platform-configurator#/)

## Browser Capabilities

There are many browser capabilities that can be configured to define a specific behavior for your tests. For example,

### Set UnhandledPromptBehavior

```java reference

https://github.com/saucelabs/sauce_bindings/blob/main/java/junit5/src/test/java/com/saucelabs/saucebindings/junit5/examples/CommonOptionsTest.java

```

The test above will run on X, Y, Z.

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
