---
id: data-center
title: Setting Data Center
sidebar_label: Data Center
---

While the Sauce Options class allows you to specify how you want your tests to run,
you may also want to adjust where your tests are run with the Sauce Session class. 
By default, tests are executed on our US West Coast Data Center. 
You can Specify US East, Central EU, or APAC data centers with the Session class:

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

```java reference
https://github.com/saucelabs/sauce_bindings/tree/java-1.2.0/java/main/src/main/java/com/saucelabs/saucebindings/examples/DataCenterTest.java
```

</TabItem>
<TabItem value="python">

```python reference
https://github.com/saucelabs/sauce_bindings/tree/java-1.2.0/python/tests/examples/test_data_center.py
```

</TabItem>
<TabItem value="ruby">

```ruby reference
https://github.com/saucelabs/sauce_bindings/tree/java-1.2.0/ruby/spec/examples/data_center_spec.rb
```

</TabItem>
<TabItem value="csharp">

**C# bindings are coming soon...**

</TabItem>
</Tabs>
