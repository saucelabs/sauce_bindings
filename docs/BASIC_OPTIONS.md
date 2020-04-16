---
id: basic-options
title: Setting Basic Options
sidebar_label: Basic Options
---

Sauce Bindings are designed to enforce compatibility with the [W3C WebDriver Standard](https://www.w3.org/TR/webdriver/).
 The three that matter most to Sauce Labs users are: 
* `browserName`
* `browserVersion`
* `platformName`

To see what values are supported by Sauce Labs for these capabilities, take a look at our
[Platform Configurator](https://wiki.saucelabs.com/display/DOCS/Platform+Configurator#/)

Here's an example of running a test on Firefox and Windows 8.0.

<!--DOCUSAURUS_CODE_TABS-->
<!--Java-->

```java
import com.saucelabs.saucebindings.options;
import com.saucelabs.saucebindings.session;

public class HelloSauce {
    public static void main(String[] args) {
        SauceOptions sauceOptions = new SauceOptions();
        sauceOptions.setBrowserName(Browser.FIREFOX);
        sauceOptions.setBrowserVersion("77");
        sauceOptions.setPlatformName(SaucePlatform.WINDOWS_8);

        // Then you can pass the Options object into the Session object:
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

sauceOptions = SauceOptions(browserName='firefox', 
                            browserVersion='77.0', 
                            platformName='Windows 8.0')

# Then you can pass the Options object into the Session object:
sauceSession = SauceSession(sauceOptions)

driver = sauceSession.start()
# use the driver to drive the browser as desired
session.stop(True)
```
<!--Ruby-->
```ruby
require 'sauce_bindings'

sauceOptions = SauceOptions.new(browser_name: 'firefox',
                                browser_version: '77',
                                platform_name: 'Windows 8.0')

# Then you can pass the Options object into the Session object:
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
