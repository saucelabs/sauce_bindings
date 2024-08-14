---
id: data-center
title: Setting Data Center
sidebar_label: Data Center
---

While the Sauce Options class allows you to specify how you want your tests to run,
you may also want to adjust where your tests are run with the Sauce Session class. 
By default, tests are executed on our US West Coast Data Center. 
You can Specify US East (real mobile devices only), or Central EU data centers with the Session class.
Here's an example of setting the data center to EU:

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
https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/java/junit5/src/test/java/com/saucelabs/saucebindings/junit5/examples/DataCenterTest.java
```

</TabItem>
<TabItem value="junit4">

```java reference
https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/java/junit4/src/test/java/com/saucelabs/saucebindings/junit4/examples/DataCenterTest.java
```

</TabItem>
<TabItem value="testng">

```java reference
https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/java/testng/src/test/java/com/saucelabs/saucebindings/testng/examples/DataCenterTest.java
```

</TabItem>
<TabItem value="direct">

```java reference
https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/java/main/src/test/java/com/saucelabs/saucebindings/examples/DataCenterTest.java
```

</TabItem>
</Tabs>

</TabItem>
<TabItem value="python">

```python reference
https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/python/tests/examples/test_data_center.py
```

</TabItem>
<TabItem value="ruby">

```ruby reference
https://github.com/saucelabs/sauce_bindings/tree/website-1.2.0/ruby/spec/examples/data_center_spec.rb
```

</TabItem>
<TabItem value="csharp">

**C# bindings are coming soon...**

</TabItem>
</Tabs>
