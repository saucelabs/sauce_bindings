---
id: browser-options
title: Setting Browser Options
sidebar_label: Browser Options
---

Selenium provides options classes for each of the supported browsers with all of their custom settings.
Rather than re-implement these, the `SauceOptions` class can be constructed with an instance of
one of these objects:

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
https://github.com/saucelabs/sauce_bindings/commit/e9e56f0/java/src/main/java/com/saucelabs/saucebindings/examples/BrowserOptionsTest.java
```

</TabItem>
<TabItem value="python">

```python reference
https://github.com/saucelabs/sauce_bindings/commit/e9e56f0/python/tests/examples/test_browser_options.py
```

</TabItem>
<TabItem value="ruby">

```ruby reference
https://github.com/saucelabs/sauce_bindings/commit/e9e56f0/ruby/spec/examples/browser_options_spec.rb
```

</TabItem>
<TabItem value="csharp">

**C# bindings are coming soon...**

</TabItem>
</Tabs>

