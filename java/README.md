Welcome To The New Evolution In Sauce Labs Testing.
The goal of Simple Sauce is to make your test automation and integration in Sauce Labs, simple :)

Start a test on all defaults:

```WebDriver driver = new SauceSession().start();```
```driver.findElement("id").click()```

Run test on latest Mac/Safari:

```WebDriver driver = new SauceSession().withSafari().start();```

Run test on Linux with Chrome:

```WebDriver driver = new SauceSession().withLinux().withChrome().start();```

More complicated configuration:

```WebDriver driver = new SauceSession().withLinux().withChrome("72.0").setIdleTimeout(100).start()```
