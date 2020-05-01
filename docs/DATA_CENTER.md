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
import com.saucelabs.saucebindings.*;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChangeDataCenter {

    @Test
    public void dataCenter() {
        // 1. Create Session object with the defaults
        SauceSession session = new SauceSession();

        // 2. Set Data Center
        session.setDataCenter(DataCenter.EU_CENTRAL);

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
from saucebindings.session import SauceSession


class TestDataCenter(object):

    def test_creates_session(self):
        # 1. Create Session object with the desired Data Center
        session = SauceSession(data_center='eu-central')

        # 2. Start Session to get the Driver
        driver = session.start()

        # 3. Use the driver in your tests just like normal
        driver.get('https://www.saucedemo.com/')

        # 4. Stop the Session with whether the test passed or failed
        session.stop(True)
```
<!--Ruby-->
```ruby
require 'sauce_bindings'
require 'rspec'

describe 'Create Session' do
  it 'starts session' do
    # 1. Create Session object with the desired Data Center
    session = SauceBindings::Session.new(data_center: :EU_CENTRAL)

    # 2. Start Session to get the Driver
    driver = session.start

    # 3. Use the driver in your tests just like normal
    driver.get('https://www.saucedemo.com/')

    # 4. Stop the Session with whether the test passed or failed
    session.stop(true)
  end
end
```
<!--C#-->
<br />

**C# bindings are coming soon...**

<!--END_DOCUSAURUS_CODE_TABS-->

___
