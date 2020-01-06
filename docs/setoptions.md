---
id: setoptions
title: Setting Options
sidebar_label: Set Options
---

## Setting Basic Options

Simple Sauce supports setting all of the [W3C WebDriver Standard](https://www.w3.org/TR/webdriver/)
capabilities. The three that matter most to Sauce Labs users are: 
* `browserName`
* `browserVersion`
* `platformName`

To see what values are supported by Sauce Labs for these capabilities, take a look at our
[Platform Configurator](https://wiki.saucelabs.com/display/DOCS/Platform+Configurator#/)
Here's how to set these values with Simple Sauce:

<!--DOCUSAURUS_CODE_TABS-->
<!--Java-->

```java
import com.saucelabs.simplesauce.options;
import com.saucelabs.simplesauce.session;

public class HelloSauce {
    public static void main(String[] args) {
        SauceOptions sauceOptions = new SauceOptions();
        sauceOptions.setBrowserName(Browser.FIREFOX);
        sauceOptions.setBrowserVersion("77");
        sauceOptions.setPlatformName(SaucePlatform.WINDOWS_8);

        // Then you can pass the Options object into the Session object:
        SauceSession sauceSession = new SauceSession(sauceOptions);
        sauceSession.start()
        session.stop(True)
    }
}
```

<!--Python-->
```python
from simplesauce.options import SauceOptions
from simplesauce.session import SauceSession

sauceOptions = SauceOptions(browserName='firefox', 
                            browserVersion='77.0', 
                            platformName='Windows 8.0')

// Then you can pass the Options object into the Session object:
sauceSession = SauceSession(sauceOptions)
sauceSession.start()
sauceSession.stop(True)
```
<!--C#-->

```c#
using Simple.Sauce;

var sauceOptions = new SauceOptions();
sauceOptions.BrowserName = "firefox";
sauceOptions.BrowserVersion = "77";
sauceOptions.PlatformName = "Windows 8.0";

// Then you can pass the Options object into the Session object:
SauceSession = new SauceSession(sauceOptions);
SauceSession.Start();
SauceSession.Stop(true);
```
<!--Ruby-->
```ruby
require 'simple_sauce'

sauceOptions = SauceOptions.new(browser_name: 'firefox',
                                browser_version: '77',
                                platform_name: 'Windows 8.0)

# Then you can pass the Options object into the Session object:
sauceSession = SauceSession.new(sauceOptions)
sauceSession.start
sauceSession.stop(true);
```
<!--END_DOCUSAURUS_CODE_TABS-->

___

## Setting Sauce Labs Options

Sauce Labs provides dozens of ways to configure your tests on our platform.
Here is a [full list with exhaustive descriptions](https://wiki.saucelabs.com/display/DOCS/Test+Configuration+Options)

All of these configurations can now be easily set with the `SauceOptions` class

<!--DOCUSAURUS_CODE_TABS-->
<!--Java-->

```java
import com.saucelabs.simplesauce.options;
import com.saucelabs.simplesauce.session;


public class HelloSauce {
    public static void main(String[] args) {
        SauceOptions sauceOptions = new SauceOptions();
        sauceOptions.setExtendedDebugging(true);
        sauceOptions.setParentTunnel("Mommy");
        sauceOptions.setIdleTimeout(100);
        sauceOptions.setTimeZone("Alaska");

        SauceSession sauceSession = new SauceSession(sauceOptions);
        sauceSession.start()
        session.stop(True)
    }
}
```

<!--Python-->
```python
from simplesauce.options import SauceOptions
from simplesauce.session import SauceSession

sauceOptions = SauceOptions(extendedDebugging=true, 
                            parentTunnel='Mommy', 
                            idleTimeout=100,
                            timeZone='Alaska')

sauceSession = SauceSession(sauceOptions)
sauceSession.start()
sauceSession.stop(True)
```
<!--C#-->

```c#
using Simple.Sauce;

var sauceOptions = new SauceOptions();
sauceOptions.ExtendedDebugging = true;
sauceOptions.ParentTunnel = "Mommy";
sauceOptions.IdleTimeout = 100;
sauceOptions.TimeZone = "Alaska";

SauceSession = new SauceSession(sauceOptions);
SauceSession.Start();
SauceSession.Stop(true);
```
<!--Ruby-->
```ruby
require 'simple_sauce'

sauceOptions = SauceOptions.new(extended_debugging: true,
                                parent_tunnel: 'Mommy',
                                idle_timeout: 100,
                                time_zone: 'Alaska')

sauceSession = SauceSession.new(sauceOptions)
sauceSession.start
sauceSession.stop(true);
```
<!--END_DOCUSAURUS_CODE_TABS-->

___

## Setting Browser Options

Selenium provides options classes for each of the supported browsers with all of their custom settings
Rather than re-implement these, the `SauceOptions` class can be constructed with an instance of
one of these objects:

<!--DOCUSAURUS_CODE_TABS-->
<!--Java-->

```java
import com.saucelabs.simplesauce.options;
import com.saucelabs.simplesauce.session;
import org.openqa.selenium.firefox.FirefoxOptions;

public class HelloSauce {
    public static void main(String[] args) {

        FirefoxOptions browserOptions = new FirefoxOptions();
        browserOptions.addArguments("--foo");

        SauceOptions sauceOptions = new SauceOptions(browserOptions);

        SauceSession sauceSession = new SauceSession(sauceOptions);
        sauceSession.start()
        session.stop(True)
    }
}
```

<!--Python-->
```python
from simplesauce.options import SauceOptions
from simplesauce.session import SauceSession
from selenium.webdriver.firefox.options import Options as FirefoxOptions

browserOptions = FirefoxOptions()
browserOptions.add_argument('--foo')

sauceOptions = SauceOptions(browserOptions)

sauceSession = SauceSession(sauceOptions)
sauceSession.start()
sauceSession.stop(True)
```
<!--C#-->

```c#
using Simple.Sauce;
using OpenQA.Selenium.Firefox;

var browserOptions = new FirefoxOptions();
browserOptions.withArgs("-foo");

SauceOptions = new SauceOptions(options);

SauceSession = new SauceSession(sauceOptions);
SauceSession.Start();
SauceSession.Stop(true);
```
<!--Ruby-->
```ruby
require 'simple_sauce'

browser_options = Selenium::WebDriver::Firefox::Options.new(args: ['--foo'])

sauce_options = SauceOptions.new(browser_options)

sauce_session = SauceSession.new(sauceOptions)
sauce_session.start
sauce_session.stop(true);
```
<!--END_DOCUSAURUS_CODE_TABS-->

___
