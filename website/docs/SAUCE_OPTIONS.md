---
id: sauce-options
title: Setting Sauce Options
sidebar_label: Sauce Options
---

Sauce Labs provides dozens of ways to configure your tests on our platform.
Here is a [full list with exhaustive descriptions](https://wiki.saucelabs.com/display/DOCS/Test+Configuration+Options)

All of these configurations can now be easily set with the `SauceOptions` class

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
https://github.com/saucelabs/sauce_bindings/commit/e9e56f0/java/src/main/java/com/saucelabs/saucebindings/examples/SauceLabsOptionsTest.java
```

</TabItem>
<TabItem value="python">

```python reference
https://github.com/saucelabs/sauce_bindings/commit/e9e56f0/python/tests/examples/test_sauce_options.py
```

</TabItem>
<TabItem value="ruby">

```ruby reference
https://github.com/saucelabs/sauce_bindings/commit/e9e56f0/ruby/spec/examples/sauce_options_spec.rb
```

</TabItem>
<TabItem value="csharp">

**C# bindings are coming soon...**

</TabItem>
</Tabs>
