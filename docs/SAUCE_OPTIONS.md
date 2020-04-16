---
id: sauce-options
title: Setting Sauce Options
sidebar_label: Sauce Options
---

Sauce Labs provides dozens of ways to configure your tests on our platform.
Here is a [full list with exhaustive descriptions](https://wiki.saucelabs.com/display/DOCS/Test+Configuration+Options)

All of these configurations can now be easily set with the `SauceOptions` class

<!--DOCUSAURUS_CODE_TABS-->
<!--Java-->

```java
import com.saucelabs.saucebindings.options;
import com.saucelabs.saucebindings.session;


public class HelloSauce {
    public static void main(String[] args) {
        SauceOptions sauceOptions = new SauceOptions();
        sauceOptions.setExtendedDebugging(true);
        sauceOptions.setParentTunnel("Mommy");
        sauceOptions.setIdleTimeout(100);
        sauceOptions.setTimeZone("Alaska");

        SauceSession sauceSession = new SauceSession(sauceOptions);

        RemoteWebDriver driver = sauceSession.start();
        // use the driver to drive the browser as desired
        sauceSession.stop(True);
    }
}
```

<!--Python-->
```python
from saucebindings.options import SauceOptions
from saucebindings.session import SauceSession

sauceOptions = SauceOptions(extendedDebugging=true, 
                            parentTunnel='Mommy', 
                            idleTimeout=100,
                            timeZone='Alaska')

sauceSession = SauceSession(sauceOptions)

driver = sauceSession.start()
# use the driver to drive the browser as desired
session.stop(True)
```
<!--Ruby-->
```ruby
require 'sauce_bindings'

sauceOptions = SauceOptions.new(extended_debugging: true,
                                parent_tunnel: 'Mommy',
                                idle_timeout: 100,
                                time_zone: 'Alaska')

sauceSession = SauceSession.new(sauceOptions)

driver = sauceSession.start
# use the driver to drive the browser as desired
sauceSession.stop(true)
```
<!--C#-->
<br />

**C# bindings are coming soon...**

<!--END_DOCUSAURUS_CODE_TABS-->

___
