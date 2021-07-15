---
id: sauce-options
title: Setting Sauce Labs Options
sidebar_label: Sauce Options
---

Sauce Labs provides dozens of ways to configure your tests on our platform.
Here is a [full list of everything with exhaustive descriptions](https://docs.saucelabs.com/dev/test-configuration-options/index.html).
All of these configurations can now be easily set with the `SauceOptions` class

Here's an example of running a test on Firefox that sets extended debugging, changes the idle timeout to 45 seconds,
and sets the time zone to Alaska:

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

<Tabs
defaultValue="java"
values={[
{ label: 'Java', value: 'java', },
{ label: 'Python', value: 'python', },
{ label: 'Ruby', value: 'ruby', },
{ label: 'C#', value: 'csharp', },
]
}>

<TabItem value="java">

<Tabs
defaultValue="junit5"
values={[
{ label: 'JUnit 5', value: 'junit5', },
{ label: 'JUnit 4', value: 'junit4', },
{ label: 'TestNG', value: 'testng', },
{ label: 'Direct', value: 'direct', },
]
}>

<TabItem value="junit5">

```java reference
https://github.com/saucelabs/sauce_bindings/tree/java-1.2.0/java/junit5/src/test/java/com/saucelabs/saucebindings/junit5/examples/SauceLabsOptionsTest.java
```

</TabItem>
<TabItem value="junit4">

```java reference
https://github.com/saucelabs/sauce_bindings/tree/java-1.2.0/java/junit4/src/test/java/com/saucelabs/saucebindings/junit4/examples/SauceLabsOptionsTest.java
```

</TabItem>
<TabItem value="testng">

```java reference
https://github.com/saucelabs/sauce_bindings/tree/java-1.2.0/java/testng/src/test/java/com/saucelabs/saucebindings/testng/examples/SauceLabsOptionsTest.java
```

</TabItem>
<TabItem value="direct">

```java reference
https://github.com/saucelabs/sauce_bindings/tree/java-1.2.0/java/main/src/test/java/com/saucelabs/saucebindings/examples/SauceLabsOptionsTest.java
```

</TabItem>
</Tabs>

</TabItem>
<TabItem value="python">

```python reference
https://github.com/saucelabs/sauce_bindings/tree/java-1.2.0/python/tests/examples/test_sauce_options.py
```

</TabItem>
<TabItem value="ruby">

```ruby reference
https://github.com/saucelabs/sauce_bindings/tree/java-1.2.0/ruby/spec/examples/sauce_options_spec.rb
```

</TabItem>
<TabItem value="csharp">

**C# bindings are coming soon...**

</TabItem>
</Tabs>
