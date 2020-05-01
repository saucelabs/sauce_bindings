---
id: create-session
title: Creating a Session
sidebar_label: Create Session
---

Sauce Bindings allows you to configure numerous capabilities, but it also provides reasonable defaults.
If you want to start and stop a session on a Windows 10 machine with the latest version of Chrome, 
this is all you need to do:

<!--DOCUSAURUS_CODE_TABS-->
<!--Java-->

```java
import com.saucelabs.saucebindings.*;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

public class StartSession {

    @Test
    public void startSession() {
        // 1. Create Session object with the defaults
        SauceSession session = new SauceSession();

        // 2. Start Session to get the Driver
        RemoteWebDriver driver = session.start();

        // 3. Use the driver in your tests just like normal
        driver.get("https://www.saucedemo.com/");

        // 4. Stop the Session with whether the test passed or failed
        session.stop(true);
    }
}
```
<!--Python-->
```python
from saucebindings.session import SauceSession


class TestCreateSession(object):

    def test_creates_session(self):
        # 1. Create Session object with the defaults
        session = SauceSession()

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
    # 1. Create Session object with the defaults
    session = SauceBindings::Session.new

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
