---
id: accessibility
title: Accessibility
sidebar_label: Accessibility
---

As of version 1.2, Sauce Bindings supports the new Sauce Labs 
[Deque axe™ Integration](https://docs.saucelabs.com/basics/integrations/deque/index.html)

* The Session classes now have a method to obtain accessibility results, which does two basic things:
    * Populates the new Accessibility tab in the Sauce Labs UI for a given job 
      with the accessibility violations found during the test run.
    * Returns the specific violations found in the code. The format of these results varies by language, and it is left to the user 
      to determine how best to make use of them in their code if so desired.
* The Java code is implemented using Deque's [Axe Core Maven HTML Jar](https://github.com/dequelabs/axe-core-maven-html)
    * This implementation provides access to all the advanced features.
    * As a wrapper, Sauce bindings provides 3 method signatures for getting accessibility results, as outlined below.
* The Ruby and Python code is implemented with the new [sa11y](https://github.com/saucelabs/sa11y) project
    * Sa11y is a minimalist implementation of Deque's axe™ functionality.
    * The accessibility results method allows you to specify a different js library, the ability to turn off frame support,
      and the ability to turn on cross-origin frame support. Examples are provided below.

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
https://github.com/saucelabs/sauce_bindings/tree/java-1.2.0/java/junit5/src/test/java/com/saucelabs/saucebindings/junit5/examples/AccessibilityTest.java
```

</TabItem>
<TabItem value="junit4">

```java reference
https://github.com/saucelabs/sauce_bindings/tree/java-1.2.0/java/junit4/src/test/java/com/saucelabs/saucebindings/junit4/examples/AccessibilityTest.java
```

</TabItem>
<TabItem value="testng">

```java reference
https://github.com/saucelabs/sauce_bindings/tree/java-1.2.0/java/testng/src/test/java/com/saucelabs/saucebindings/testng/examples/AccessibilityTest.java
```

</TabItem>
<TabItem value="direct">

```java reference
https://github.com/saucelabs/sauce_bindings/tree/java-1.2.0/java/main/src/test/java/com/saucelabs/saucebindings/examples/AccessibilityTest.java
```

</TabItem>
</Tabs>

</TabItem>
<TabItem value="python">

```python reference
https://github.com/saucelabs/sauce_bindings/tree/java-1.2.0/python/tests/examples/test_accessibility.py
```

</TabItem>
<TabItem value="ruby">

```ruby reference
https://github.com/saucelabs/sauce_bindings/tree/java-1.2.0/ruby/spec/examples/accessibility_spec.rb
```

</TabItem>
<TabItem value="csharp">

**C# bindings are coming soon...**

</TabItem>
</Tabs>
