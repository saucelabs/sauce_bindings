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

<!--DOCUSAURUS_CODE_TABS-->
<!--Java-->
<br />

1. Install Java version 9 or greater (we recommend Java 11)<br />
2. Install your favorite Java IDE (we really like IntelliJ and the Community Edition is free).
2. The project is designed as a standard Maven project and follow Maven conventions.</br>
Add the following to your .pom file:

```xml
<dependency>
    <groupId>com.saucelabs</groupId>
    <artifactId>sauce-bindings</artifactId>
    <version>1.0.0</version>
</dependency>
```

<!--Python-->
<br />

1. Install the package:
```bash
pip install saucebindings
```
2. Import it into your project
```python
from saucebindings.options import SauceOptions
from saucebindings.session import SauceSession
```

<!--Ruby-->
<br />

1. Add it to your Gemfile:
```ruby
gem 'sauce_bindings'
```
2. Require it in your project:
```ruby
require 'sauce_bindings'
```

Alternately, to try it out you can install it on your system:
```bash
gem install sauce_bindings
```

Note, for Capybara users, you must also require this file:
```ruby
require 'sauce_bindings/capybara_session'
```
<!--C#-->
<br />

**C# bindings are coming soon...**

<!--END_DOCUSAURUS_CODE_TABS-->

___
