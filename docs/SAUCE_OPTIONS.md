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
import com.saucelabs.saucebindings.*;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SauceLabsOptionsTest {

    @Test
    public void sauceOptions() {
        // 1. Specify Sauce Specific Options
        SauceOptions sauceOptions = SauceOptions.firefox()
                .setExtendedDebugging()
                .setIdleTimeout(100)
                .setTimeZone("Alaska")
                .build();

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


class TestSauceOptions(object):

    def test_creates_session(self):
        # 1. Create a SauceOptions instance with Sauce Labs Specific Options
        sauceOptions = SauceOptions(extendedDebugging=True,
                                    idleTimeout=100,
                                    timeZone='Alaska')

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

describe 'Sauce Options' do
  it 'creates session' do
    # 1. Create a SauceOptions instance with Sauce Labs Specific Options
    sauce_options = SauceBindings::Options.new(extended_debugging: true,
                                               idle_timeout: 100,
                                               time_zone: 'Alaska')

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
