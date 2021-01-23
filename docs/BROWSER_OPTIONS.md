---
id: browser-options
title: Setting Browser Options
sidebar_label: Browser Options
---

Selenium provides options classes for each of the supported browsers with all of their custom settings.
Rather than re-implement these, the `SauceOptions` class can be constructed with an instance of
one of these objects:

<!--DOCUSAURUS_CODE_TABS-->
<!--Java-->

```java
import com.saucelabs.saucebindings.*;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserOptionsTest {

    @Test
    public void browserOptions() {
        // 1. Create Selenium Browser Options instance
        FirefoxOptions browserOptions = new FirefoxOptions();
        browserOptions.addArguments("--foo");

        // 2. Create Sauce Options object with the Browser Options object instance
        SauceOptions sauceOptions = SauceOptions.firefox(browserOptions);

        // 3. Create Session object with the Sauce Options object instance
        SauceSession session = new SauceSession(sauceOptions);

        // 4. Start Session to get the Driver
        RemoteWebDriver driver = session.start();

        // 5. Use the driver in your tests just like normal
        driver.get("https://www.saucedemo.com/");

        // 6. Stop the Session with whether the test passed or failed
        session.stop(true);
    }
}
```

<!--Python-->
```python
from saucebindings.options import SauceOptions
from saucebindings.session import SauceSession
from selenium.webdriver.firefox.options import Options as FirefoxOptions


class TestBrowserOptions(object):

    def test_creates_session(self):

        # 1. Create Selenium Browser Options instance
        browserOptions = FirefoxOptions()
        browserOptions.add_argument('--foo')

        # 2. Create Sauce Options object with the Browser Options object instance
        sauceOptions = SauceOptions(seleniumOptions=browserOptions)

        # 3. Create Session object with SauceOptions object instance
        session = SauceSession(sauceOptions)

        # 4. Start Session to get the Driver
        driver = session.start()

        # 5. Use the driver in your tests just like normal
        driver.get('https://www.saucedemo.com/')

        # 6. Stop the Session with whether the test passed or failed
        session.stop(True)
```

<!--Ruby-->
```ruby
require 'sauce_bindings'
require 'rspec'

describe 'Browser Options' do
  it 'creates session' do
    # 1. Create Selenium Browser Options instance
    browser_options = Selenium::WebDriver::Firefox::Options.new(args: ['--foo'])

    # 2. Create Sauce Options object with the Browser Options object instance
    sauce_options = SauceBindings::Options.new(browser_options)

    # 3. Create Session object with SauceOptions object instance
    session = SauceBindings::Session.new(sauce_options)

    # 4. Start Session to get the Driver
    driver = session.start

    # 5. Use the driver in your tests just like normal
    driver.get('https://www.saucedemo.com/')

    # 6. Stop the Session with whether the test passed or failed
    session.stop(true)
  end
end
```

<!--C#-->
<br />

**C# bindings are coming soon...**

<!--END_DOCUSAURUS_CODE_TABS-->

___
