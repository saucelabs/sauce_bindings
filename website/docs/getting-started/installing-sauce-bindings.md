---
title: Installing Sauce Bindings
sidebar_position: 1
---

## Universal Prerequisites

1. Get your [Sauce Labs](https://saucelabs.com/sign-up) account

2. Set up your Sauce Credentials
   Since credentials should never be stored in code that might get added to a version control system,
   we have decided to require users of Sauce Bindings to store these values in environment variables:

```bash
SAUCE_USERNAME='valid.username'
SAUCE_ACCESS_KEY='valid.key'
```

How to set environment variables on each Operating System:

- [Windows 10](https://www.architectryan.com/2018/08/31/how-to-change-environment-variables-on-windows-10/)
- [MacOS](https://apple.stackexchange.com/questions/106778/how-do-i-set-environment-variables-on-os-x)
- [Linux](https://askubuntu.com/questions/58814/how-do-i-add-environment-variables)

## Language Specific Prerequisites

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

<Tabs
defaultValue="java"
values={[
{ label: 'Java', value: 'java', }
]
}>

<TabItem value="java">

1. [Install **_Java version 11_** or greater](https://www.java.com/en/download/help/download_options.html)
2. Install your favorite Java IDE (IntelliJ is 🔥 and the [Community Edition is free](https://www.jetbrains.com/idea/)).
3. Choose your [test runner](../core-concepts/test-runners.md) below:

<Tabs
defaultValue="junit5"
values={[
{ label: 'JUnit 5', value: 'junit5', },
{ label: 'JUnit 4', value: 'junit4', },
{ label: 'TestNG', value: 'testng', },
]
}>

<TabItem value="junit5">

1. Add the following to your `.pom` file:

```xml
<!-- https://mvnrepository.com/artifact/com.saucelabs/saucebindings-junit4/latest -->
<dependency>
    <groupId>com.saucelabs</groupId>
    <artifactId>saucebindings-junit5</artifactId>
    <version>1.5.0</version>
</dependency>
```

</TabItem>
<TabItem value="junit4">

3. Add the following to your `.pom` file:

```xml
<!-- https://mvnrepository.com/artifact/com.saucelabs/saucebindings-junit4/latest -->
<dependency>
    <groupId>com.saucelabs</groupId>
    <artifactId>saucebindings-junit4</artifactId>
    <version>1.5.0</version>
</dependency>
```

</TabItem>
<TabItem value="testng">

3. Add the following to your `.pom` file:

```xml
<!-- https://mvnrepository.com/artifact/com.saucelabs/saucebindings-testng/latest -->
<dependency>
    <groupId>com.saucelabs</groupId>
    <artifactId>saucebindings-testng</artifactId>
    <version>1.5.0</version>
</dependency>
```

</TabItem>

</Tabs>

</TabItem>
</Tabs>
