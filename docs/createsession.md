---
id: createsession
title: Creating a Session
sidebar_label: Create Session
---

Simple Sauce allows you to configure numerous capabilities, but it also provides reasonable defaults.
If you want to start and stop a session on a Windows 10 machine with the latest version of Chrome, 
this is all you need to do:

<!--DOCUSAURUS_CODE_TABS-->
<!--Java-->

```java
import com.saucelabs.simplesauce.session;

public class HelloSauce {
    public static void main(String[] args) {
        SauceSession sauceSession = new SauceSession();
        RemoteWebDriver driver = sauceSession.start();
        // use the driver to drive the browser as desired
        sauceSession.stop("passed");
    }
}
```

<!--Python-->
```python
from simplesauce.session import SauceSession

sauceSession = SauceSession()
driver = sauceSession.start()
// use the driver to drive the browser as desired
session.stop(True)
```
<!--C#-->

```c#
using Simple.Sauce;

SauceSession = new SauceSession();
var driver = SauceSession.Start();
// use the driver to drive the browser as desired
SauceSession.Stop(true);
```
<!--Ruby-->
```ruby
require 'simple_sauce'

sauceSession = SauceSession.new
driver = sauceSession.start
# use the driver to drive the browser as desired
sauceSession.stop(true);
```
<!--END_DOCUSAURUS_CODE_TABS-->

___
