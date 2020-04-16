---
id: data-center
title: Setting Data Center
sidebar_label: Data Center
---

While the Sauce Options class allows you to specify how you want your tests to run,
you may also want to adjust where your tests are run with the Sauce Session class. 
By default tests are executed on our US West Coast Data Center. 
You can Specify US East or Central EU on the Session class:

<!--DOCUSAURUS_CODE_TABS-->
<!--Java-->

```java
import com.saucelabs.saucebindings.session;

public class HelloSauce {
    public static void main(String[] args) {
        SauceSession sauceSession = new SauceSession();
        sauceSession.setDataCenter(DataCenter.US_EAST);

        RemoteWebDriver driver = sauceSession.start();
        // use the driver to drive the browser as desired
        sauceSession.stop(True);
    }
}
```

<!--Python-->
```python
from saucebindings.session import SauceSession

sauceSession = SauceSession(data_center='eu')

driver = sauceSession.start()
# use the driver to drive the browser as desired
session.stop(True)
```
<!--Ruby-->
```ruby
require 'sauce_bindings'

sauceSession = SauceSession.new(data_center: :EU_VDC)

driver = sauceSession.start
# use the driver to drive the browser as desired
sauceSession.stop(true)
```
<!--C#-->
<br />

**C# bindings are coming soon...**

<!--END_DOCUSAURUS_CODE_TABS-->

___
