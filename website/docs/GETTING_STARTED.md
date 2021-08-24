---
id: getting-started
title: Getting Started
sidebar_label: Getting Started
---

## Universal Prerequisites

To start with make sure you have a valid [Sauce Labs](https://app.saucelabs.com/) account 

Since credentials should never be stored in code that might get added to a version control system, 
we have decided to require users of Sauce Bindings to store these values in environment variables:

```bash
SAUCE_USERNAME='valid.username'
SAUCE_ACCESS_KEY='valid.key'
```

Here are instructions for setting environment variables on each Operating System: 
* [Windows 10](https://www.architectryan.com/2018/08/31/how-to-change-environment-variables-on-windows-10/) 
* [MacOS](https://apple.stackexchange.com/questions/106778/how-do-i-set-environment-variables-on-os-x)
* [Linux](https://askubuntu.com/questions/58814/how-do-i-add-environment-variables)

## Language Specific  Prerequisites

import Tabs from '@theme/Tabs';
import TabItem from '@theme/TabItem';

<Tabs
defaultValue="java"
values={[
{ label: 'Java', value: 'java', },
{ label: 'Python', value: 'python', },
{ label: 'Ruby', value: 'ruby', },
{ label: 'C#', value: 'csharp', },
]
}>

<TabItem value="java">

1. Install ***Java version 8*** or greater
2. Install your favorite Java IDE (we really like IntelliJ and the Community Edition is free).
3. Choose your [test runner](test-runners/) below, or use the Sauce Bindings directly without test runner support:

<Tabs
defaultValue="junit5"
values={[
{ label: 'JUnit 5', value: 'junit5', },
{ label: 'JUnit 4', value: 'junit4', },
{ label: 'TestNG', value: 'testng', },
{ label: 'Direct', value: 'direct', },
]
}>

<TabItem value="junit5">

3. Add the following to your `.pom` file:

```xml
<!-- https://mvnrepository.com/artifact/com.saucelabs/saucebindings-junit4/latest -->
<dependency>
    <groupId>com.saucelabs</groupId>
    <artifactId>saucebindings-junit5</artifactId>
    <version>1.0.0</version>
</dependency>
```

</TabItem>
<TabItem value="junit4">

3. Add the following to your `.pom` file:

```xml
<!-- https://mvnrepository.com/artifact/com.saucelabs/saucebindings-junit4/latest -->
<dependency>
    <groupId>com.saucelabs</groupId>
    <artifactId>saucebindings-junit4</artifactId>
    <version>1.0.0</version>
</dependency>
```

</TabItem>
<TabItem value="testng">

3. Add the following to your `.pom` file:

```xml
<!-- https://mvnrepository.com/artifact/com.saucelabs/saucebindings-testng/latest -->
<dependency>
    <groupId>com.saucelabs</groupId>
    <artifactId>saucebindings-testng</artifactId>
    <version>1.0.0</version>
</dependency>
```

</TabItem>
<TabItem value="direct">

3. Add the following to your `.pom` file:

```xml
<!-- https://mvnrepository.com/artifact/com.saucelabs/sauce_bindings/latest -->
<dependency>
    <groupId>com.saucelabs</groupId>
    <artifactId>sauce_bindings</artifactId>
    <version>1.2.0</version>
</dependency>
```

</TabItem>
</Tabs>

</TabItem>
<TabItem value="python">

1. Install the package:
```bash
pip install saucebindings
```
2. Import it into your project
```python
from saucebindings.options import SauceOptions
from saucebindings.session import SauceSession
```

</TabItem>
<TabItem value="ruby">

1. Add it to your Gemfile:
```ruby
gem 'sauce_bindings'
```

2. Require it in your project:
```ruby
require 'sauce_bindings'
```

If you are using Capybara, you must also require this file:
```ruby
require 'sauce_bindings/capybara_session'
```

Examples on this site are written to use RSpec, so to execute them you must also add this to your Gemfile:
```ruby
gem 'rspec'
```

</TabItem>
<TabItem value="csharp">

**C# bindings are coming soon...**

</TabItem>
</Tabs>
