---
id: browser-options
title: Setting Browser Specific Options
sidebar_label: Browser Options
---

Selenium provides options classes for each of the supported browsers with all of their custom settings.
Rather than re-implement these, the Sauce Bindings Options classes can be constructed with an instance of
one of these Selenum browser object classes. Here is a Selenium Chrome Options class with an argument to start the browser
in Full Screen Mode:

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
https://github.com/saucelabs/sauce_bindings/tree/java-1.2.0/java/junit5/src/test/java/com/saucelabs/saucebindings/junit5/examples/BrowserOptionsTest.java
```

</TabItem>
<TabItem value="junit4">

```java reference
https://github.com/saucelabs/sauce_bindings/tree/java-1.2.0/java/junit4/src/test/java/com/saucelabs/saucebindings/junit4/examples/BrowserOptionsTest.java
```

</TabItem>
<TabItem value="testng">

```java reference
https://github.com/saucelabs/sauce_bindings/tree/java-1.2.0/java/testng/src/test/java/com/saucelabs/saucebindings/testng/examples/BrowserOptionsTest.java
```

</TabItem>
<TabItem value="direct">

```java reference
https://github.com/saucelabs/sauce_bindings/tree/java-1.2.0/java/main/src/test/java/com/saucelabs/saucebindings/examples/BrowserOptionsTest.java
```

</TabItem>
</Tabs>

</TabItem>
<TabItem value="python">

```python reference
https://github.com/saucelabs/sauce_bindings/tree/java-1.2.0/python/tests/examples/test_browser_options.py
```

</TabItem>
<TabItem value="ruby">

```ruby reference
https://github.com/saucelabs/sauce_bindings/tree/java-1.2.0/ruby/spec/examples/browser_options_spec.rb
```

</TabItem>
<TabItem value="csharp">

**C# bindings are coming soon...**

</TabItem>
</Tabs>

