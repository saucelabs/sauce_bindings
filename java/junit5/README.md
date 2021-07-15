# SauceBindings JUnit5

[SauceBindings](https://opensource.saucelabs.com/sauce_bindings/) exists to provide the simplest way to get up and
going with executing your tests on Sauce Labs. 

* If you use JUnit 5, you can use this package to remove even more boilerplate code.
* If you use JUnit 4, [go here](https://github.com/saucelabs/sauce_bindings/tree/main/java/junit4)
* If you use TestNG, [go here](https://github.com/saucelabs/sauce_bindings/tree/main/java/testng)

### Advantages

* Automatically creates a test name based on the method name of the test
* Automatically sends correct pass/fail information to Sauce Labs at the end of the test
* Automatically sends error message and stack trace information to your Sauce Labs log when a test fails
* Completely customizable

### Requirements
Just add saucebindings-junit5 to your pom file:

```xml
<dependency>
    <groupId>com.saucelabs</groupId>
    <artifactId>saucebindings-junit5</artifactId>
    <version>1.0.0</version>
    <scope>test</scope>
</dependency>
```

### Usage
Extend your test class with `SauceBaseTest` and this library takes care of the rest!
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

### Customizations

#### Sauce Options
Very few people run with only the default options for everything, so there is a simple way
to specify your `SauceOptions`. Just add a `createSauceOptions` method to your test class that
extends `SauceBaseTest`:
```java
@Override
public SauceOptions createSauceOptions() {
    return SauceOptions.firefox()
            .setMaxDuration(Duration.ofMinutes(30))
            .setJobVisibility(JobVisibility.TEAM)
            .setPlatformName(SaucePlatform.MAC_CATALINA)
            .build();
}
```

#### Subclassing
You can subclass `SauceBaseTest` and `SauceTestWatcher` as necessary to get the most out of your tests:

Here's an example of how you can inherit from these classes and toggle between running locally and on Sauce Labs.
Note that you have to create a new rule for your subclassed test watcher in order for it to be recognized:
```java
public class LocalToggleTest extends SauceBaseTest {
    @Rule
    public MyTestWatcher watcher = new MyTestWatcher();

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
                driver.quit();
            } else {
                super.succeeded(description);
            }
        }

        @Override
        protected void failed(Throwable e, Description description) {
            if (session == null) {
                driver.quit();
            } else {
                super.failed(e, description);
            }
        }
    }
```

#### Parameterization
You don't need to do anything special to use Parameterization with `SauceBaseTest`, just add the `@RunWith` notation:
```java
@RunWith(Parameterized.class)
public class ParameterizedAdjustOptionsTest extends SauceBaseTest {
```

Then just define your `@Parameterized.Parameter` types and your `@Parameterized.Parameters()` collection as you
normally would.