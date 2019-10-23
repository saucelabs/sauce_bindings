Welcome To The New Evolution In Sauce Labs Testing.
The goal of Simple Sauce is to make your test automation and integration in Sauce Labs, simple :)
Our fluent API helps you to understand all of the possible browser/OS combinations at design time.
You no longer need to read extensive docs, just let your IDE guide you 😉

## Quick Start: a test on all defaults:

```WebDriver driver = new SauceSession().start();```
```driver.findElement("id").click()```

* Defaults are Windows 10 for non-Safari
* latest Mac for Safari
## Run a test on:

### Latest Chrome
```        
SauceOptions options = new SauceOptions();
options.withSafari();
webDriver = new SauceSession(options).start();
```
### Latest Edge
```        
SauceOptions options = new SauceOptions();
options.withEdge();
webDriver = new SauceSession(options).start();
```
### Latest Firefox
```        
SauceOptions options = new SauceOptions();
options.withFirefox();
webDriver = new SauceSession(options).start();
```

### Latest Safari
```        
SauceOptions options = new SauceOptions();
options.withSafari();
webDriver = new SauceSession(options).start();
```

## If it's possible in Sauce, it's possible here:
### Run test on Linux with Firefox:
```        
SauceOptions options = new SauceOptions().
withLinux().
withFirefox();
webDriver = new SauceSession(options).start();
```
### Setting Browser versions:
```        
SauceOptions options = new SauceOptions().
withLinux().
withChrome("72");
webDriver = new SauceSession(options).start();
```
#### Edge and Safari browser versions are easy to know:
Mac OS Mojave
```        
SauceOptions options = new SauceOptions().withMacOsMojave();
webDriver = new SauceSession(options).start();
//Don't lose time trying to figure out that Mojave = "12.0"
//and that it needs to be paired with "macOS 10.14"
```
Edge version 15
```        
SauceOptions options = new SauceOptions().withEge15();
webDriver = new SauceSession(options).start();
```
