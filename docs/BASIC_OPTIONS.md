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
import com.saucelabs.saucebindings.*;
import com.saucelabs.saucebindings.options.SauceOptions;
import com.saucelabs.saucebindings.options.capabilities.Browser;
import com.saucelabs.saucebindings.options.capabilities.SaucePlatform;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BasicOptionsTest {

 @Test
 public void basicOptions() {
  // 1. Specify the 3 basic parameters of a SauceOptions instance
  SauceOptions sauceOptions = new SauceOptions();
  sauceOptions.setBrowserName(Browser.FIREFOX);
  sauceOptions.setBrowserVersion("73.0");
  sauceOptions.setPlatformName(SaucePlatform.WINDOWS_8);

  // 2. Create Session object with the Options object instance
  SauceSession session = new SauceSession(sauceOptions);

  // 3. Start Session to get the Driver
  RemoteWebDriver driver = session.start();

  // 4. Use the driver in your tests just like normal
  driver.get("https://www.saucedemo.com/");

  // 5. Stop the Session with whether the test passed or failed
  session.stop(true);
 }
}
```

<!--Python-->
```python
from saucebindings.options import SauceOptions
from saucebindings.session import SauceSession


class TestBasicOptions(object):

    def test_creates_session(self):
        # 1. Create a SauceOptions instance with the 3 primary parameters
        sauceOptions = SauceOptions(browserName='firefox',
                                    browserVersion='73.0',
                                    platformName='Windows 8')

        # 2. Create Session object with SauceOptions object instance
        session = SauceSession(sauceOptions)

        # 3. Start Session to get the Driver
        driver = session.start()

        # 4. Use the driver in your tests just like normal
        driver.get('https://www.saucedemo.com/')

        # 5. Stop the Session with whether the test passed or failed
        session.stop(True)
```
<!--Ruby-->
```ruby
require 'sauce_bindings'
require 'rspec'

describe 'Basic Options' do
  it 'creates session' do
    # 1. Create a SauceOptions instance with the 3 primary parameters
    sauce_options = SauceBindings::Options.new(browser_name: 'firefox',
                                               browser_version: '73.0',
                                               platform_name: 'Windows 8')

    # 2. Create Session object with SauceOptions object instance
    session = SauceBindings::Session.new(sauce_options)

    # 3. Start Session to get the Driver
    driver = session.start

    # 4. Use the driver in your tests just like normal
    driver.get('https://www.saucedemo.com/')

    # 5. Stop the Session with whether the test passed or failed
    session.stop(true)
  end
end
```
<!--C#-->
<br />

**C# bindings are coming soon...**

<!--END_DOCUSAURUS_CODE_TABS-->

___
