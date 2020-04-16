---
id: browser-options
title: Setting Browser Options
sidebar_label: Browser Options
---

Selenium provides options classes for each of the supported browsers with all of their custom settings
Rather than re-implement these, the `SauceOptions` class can be constructed with an instance of
one of these objects:

<!--DOCUSAURUS_CODE_TABS-->
<!--Java-->

```java
import com.saucelabs.saucebindings.options;
import com.saucelabs.saucebindings.session;
import org.openqa.selenium.firefox.FirefoxOptions;

public class HelloSauce {
    public static void main(String[] args) {

        FirefoxOptions browserOptions = new FirefoxOptions();
        browserOptions.addArguments("--foo");

        SauceOptions sauceOptions = new SauceOptions(browserOptions);
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
from selenium.webdriver.firefox.options import Options as FirefoxOptions

browserOptions = FirefoxOptions()
browserOptions.add_argument('--foo')

sauceOptions = SauceOptions(browserOptions)
sauceSession = SauceSession(sauceOptions)

driver = sauceSession.start()
# use the driver to drive the browser as desired
session.stop(True)
```
<!--Ruby-->
```ruby
require 'sauce_bindings'

browser_options = Selenium::WebDriver::Firefox::Options.new(args: ['--foo'])

sauce_options = SauceOptions.new(browser_options)
sauce_session = SauceSession.new(sauceOptions)

driver = sauceSession.start
# use the driver to drive the browser as desired
sauceSession.stop(true)
```

<!--C#-->
<br />

**C# bindings are coming soon...**

<!--END_DOCUSAURUS_CODE_TABS-->

___
