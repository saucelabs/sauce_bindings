# SauceBindings TestNG

[SauceBindings](https://opensource.saucelabs.com/sauce_bindings/) exists to provide the simplest way to get up and
going with executing your tests on Sauce Labs. 

If you use TestNG, you can use this package to remove even more boilerplate code.
(If you use JUnit, [go here](https://github.com/saucelabs/sauce_bindings/tree/main/java/junit4))

### Advantages

* Automatically creates a test name based on the method name of the test
* Automatically sends correct pass/fail information to Sauce Labs at the end of the test
* Automatically sends error message and stack trace information to your Sauce Labs log when a test fails
* Completely customizable

### Requirements
Just add sauce_bindings_testng to your pom file:

```xml
<dependency>
    <groupId>com.saucelabs</groupId>
    <artifactId>sauce_bindings_testng</artifactId>
    <version>1.0.0</version>
    <scope>test</scope>
</dependency>
```

### Usage
Extend your test class with `SauceBaseTest` and this library takes care of the rest!
```java
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class QuickstartTest extends SauceBaseTest {
    @Test
    public void useAllDefaults() {
        getDriver().navigate().to("https://www.saucedemo.com");
        assertEquals(getDriver().getTitle(), "Swagger Labs");
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
protected SauceOptions createSauceOptions() {
    return SauceOptions.firefox()
            .setMaxDuration(Duration.ofMinutes(30))
            .setJobVisibility(JobVisibility.TEAM)
            .setPlatformName(SaucePlatform.MAC_CATALINA)
            .build();
}
```

#### Subclassing
You can subclass `SauceBaseTest` as necessary to get the most out of your tests:

Here's an example of how you can toggle between running locally and on Sauce Labs:
```java
public class LocalToggleTest extends SauceBaseTest {
    private static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    @Override
    public RemoteWebDriver getDriver() {
        if (getSession() == null) {
            return driver.get();
        } else {
            return super.getDriver();
        }
    }

    @BeforeMethod
    protected void setup(Method method) {
        if (System.getProperty("SELENIUM_TARGET").equals("SAUCE_LABS")) {
            super.setup(method);
        } else {
            driver.set(new ChromeDriver());
        }
    }

    @AfterMethod
    protected void teardown(ITestResult result) {
        if (getSession() == null) {
            getDriver().quit();
        } else {
            super.teardown(result);
        }
    }
}
```

#### Parameterization
Using the `DataProvider` class requires having a special method signature, so this package offers an alternate
`SauceParameterizedBaseTest` class for you to subclass. Here's an example of what that looks like:

```java
public class ParameterizedOptionsTest extends SauceParameterizedBaseTest {

    @DataProvider(name = "sauceBrowsers", parallel = true)
    public static Object[][] sauceBrowserDataProvider() {
        return new Object[][]{
                new Object[]{SauceOptions.chrome(), "90.0", SaucePlatform.MAC_BIG_SUR},
                new Object[]{SauceOptions.firefox(), "89.0", SaucePlatform.MAC_BIG_SUR},
                new Object[]{SauceOptions.chrome(), "91.0", SaucePlatform.WINDOWS_10}};
    }

    @Override
    protected SauceOptions createSauceOptions(Method method, Object[] parameters) {
        return ((VDCConfigurations) parameters[0])
                .setBrowserVersion((String) parameters[1])
                .setPlatformName((SaucePlatform) parameters[2])
                .build();
    }

    @Test(dataProvider = "sauceBrowsers")
    public void useParameters(VDCConfigurations browser, String browserVersion, SaucePlatform saucePlatform) {
        getDriver().navigate().to("https://www.saucedemo.com");
        assertEquals(getDriver().getTitle(), "Swagger Labs");
    }
}
```