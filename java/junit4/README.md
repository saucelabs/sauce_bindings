# SauceBindings JUnit4

[SauceBindings](https://opensource.saucelabs.com/sauce_bindings/) exists to provide the simplest way to get up and
going with executing your tests on Sauce Labs. Except your tests still need to run using a test runner, and test runners require
their own boilerplate. SauceBindings JUnit4 makes writing Sauce Labs tests even easier:

* Automatically creates a test name based on the method name of the test
* Automatically sends pass/fail information to SauceLabs at the end of the test
* Automatically sends error message and stack trace information to your Sauce Labs log when a test fails

#### Requirements
Just add sauce_bindings_junit4 to your pom file. That's it!

```xml
<dependency>
    <groupId>com.saucelabs</groupId>
    <artifactId>sauce_bindings_junit4</artifactId>
    <version>1.0.0</version>
    <scope>test</scope>
</dependency>
```

### Example
This code is everything you need to run a test on Sauce Labs.
Just have your test class extend `SauceBaseTest` and this library takes care of the rest!
```java
import org.junit.Test;
import static org.junit.Assert.*;

public class QuickstartTest extends SauceBaseTest {
    @Test
    public void useAllDefaults() {
        driver.navigate().to("https://www.saucedemo.com");
        assertEquals(driver.getTitle(), "Swagger Labs");
    }
}
```

### Customize Options
To specify what kind of test to run on Sauce, simply add a `createSauceOptions` method to your test class
(or, ideally to your `BaseTest` class that your other test classes inherit from):
```java
public SauceOptions createSauceOptions() {
    return SauceOptions.firefox()
            .setMaxDuration(Duration.ofMinutes(30))
            .setJobVisibility(JobVisibility.TEAM)
            .setPlatformName(SaucePlatform.MAC_CATALINA)
            .build();
}
```

### Customize Away!
This code is intended as default options for getting the most out of Sauce Labs and JUnit right away.
Everything is extensible, so subclass `SauceBaseTest` and `SauceTestWatcher` as necessary to get the most out of your tests.

Here's a basic example of how you can inherit from these classes and toggle between running locally and on Sauce Labs:
```java
public class LocalTest extends SauceBaseTest {
    @Rule
    MyTestWatcher watcher = new MyTestWatcher();

    @Before
    public void setup() {
        if (System.getProperty("SELENIUM_TARGET").equals("SAUCE_LABS")) {
            super.setup();
        } else {
            driver = new ChromeDriver();
        }
    }

    public class MyTestWatcher extends SauceTestWatcher {
        @Override
        protected void succeeded(Description description) {
            if (session == null) {
                System.out.println("Yay, our test passed!");
            } else {
                super.succeeded(description);
            }
        }

        @Override
        protected void failed(Throwable e, Description description) {
            if (session == null) {
                System.out.println("Oh no, our test failed!");
            } else {
                super.failed(e, description);
            }
        }
    }
```