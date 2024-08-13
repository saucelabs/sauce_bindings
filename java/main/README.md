Welcome To The New Evolution In Sauce Labs Testing.
The goal of Sauce Bindings is to make your test automation and integration in Sauce Labs, simple :)
Our fluent API helps you to understand all of the possible browser/OS combinations at design time.
You no longer need to read extensive docs, just let your IDE guide you 😉

## Quick Start with a single line of code

```
WebDriver driver = new SauceSession().start();
//now use the driver to interact with your test in Sauce Labs
driver.findElement("id").click();
```

## Installation

[Installation instructions](https://opensource.saucelabs.com/sauce_bindings/getting-started)

## Building

Make sure you have the common Sauce
Bindings [prerequisites](https://github.com/saucelabs/sauce_bindings#getting-started-and-prerequisites)
set up, as well as

- Java 11+ JDK,
- Maven,
- Your favorite Java IDE ([IntelliJ](https://www.jetbrains.com/idea/download/index.html) preferred
  but not required).

The Java Sauce Bindings are designed as a standard Maven project and follow Maven conventions.

## Local development

First clone this project, either directly or from a fork. The following instructions will be based
on a local clone of the Sauce Bindings repository.

To create a `.jar` of the Java bindings in their current state in your local repository, run

```bash
mvn package
```

in the `java/` (root) directory. This will create a `.jar` file in the `java/target/` directory
which you can them use as you like.

If you'd like to make Sauce Bindings available to other local projects via Maven, run

```bash
mvn install
```

in the `java/` directory. This will allow you to import Sauce Bindings as a Maven dependency in
other local projects on your laptop as a snapshot of the state based on the commit that you build
from. To add this reference via Maven, add

```xml

<dependency>
  <groupId>com.saucelabs</groupId>
  <artifactId>sauce_bindings</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

to other projects' `pom.xml` file to access Sauce Bindings. Note that adding these coordinates to
other Maven projects will get the latest `1.0-SNAPSHOT` version that was built, and so using these
coordinates are recommended for development and not for general usage.

## Testing

To execute the test suite, run

```bash
mvn test
```

Tests will also be automatically executed as part of the building process.

## Release

Use the [release](https://github.com/saucelabs/sauce_bindings/actions/workflows/release.yml)
workflow to release the Java bindings.
