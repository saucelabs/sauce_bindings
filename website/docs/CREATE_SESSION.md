---
id: create-session
title: Creating a Session
sidebar_label: Create Session
---

Sauce Bindings allows you to configure numerous capabilities, but it also provides reasonable defaults.
If you want to start and stop a session on a Windows 10 machine with the latest version of Chrome, 
this is all you need to do:

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

Select the [test runner](test-runners/) tab based on which library you [added to your `.pom` file](getting-started#language-specific--prerequisites)

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
https://github.com/saucelabs/sauce_bindings/tree/java-1.2.0/java/junit5/src/test/java/com/saucelabs/saucebindings/junit5/examples/SessionTest.java
```

</TabItem>
<TabItem value="junit4">

```java reference
https://github.com/saucelabs/sauce_bindings/tree/java-1.2.0/java/junit4/src/test/java/com/saucelabs/saucebindings/junit4/examples/SessionTest.java
```

</TabItem>
<TabItem value="testng">

```java reference
https://github.com/saucelabs/sauce_bindings/tree/java-1.2.0/java/testng/src/test/java/com/saucelabs/saucebindings/testng/examples/SessionTest.java
```

</TabItem>
<TabItem value="direct">

```java reference
https://github.com/saucelabs/sauce_bindings/tree/java-1.2.0/java/main/src/test/java/com/saucelabs/saucebindings/examples/SessionTest.java
```

</TabItem>
</Tabs>


</TabItem>
<TabItem value="python">

```python reference
https://github.com/saucelabs/sauce_bindings/tree/java-1.2.0/python/tests/examples/test_create_session.py
```

</TabItem>
<TabItem value="ruby">

```ruby reference
https://github.com/saucelabs/sauce_bindings/tree/java-1.2.0/ruby/spec/examples/create_session_spec.rb
```

</TabItem>
<TabItem value="csharp">

**C# bindings are coming soon...**

</TabItem>
</Tabs>
