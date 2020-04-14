---
id: getting-started
title: Getting Started
sidebar_label: Getting Started
---

## Universal Prerequisites

To start with make sure you have a valid [Sauce Labs](https://app.saucelabs.com/) account 

Best practice is to set Sauce Labs username and access key values as environment variables:

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

1. Install Java version 9 or greater<br />
2. Install your favorite Java IDE (IntelliJ is recommended but not required).
2. The Simple Sauce Java bindings are designed as a standard Maven project and follow Maven conventions.</br>
Add the following to your .pom file:

```xml
<dependency>
    <groupId>com.saucelabs</groupId>
    <artifactId>sauce-bindings</artifactId>
    <version>1.0.0</version>
</dependency>
```

<!--Python-->
1. Install the package:
```bash
pip install saucebindings
```
2. Import it into your project
```python
from saucebindings.options import SauceOptions
from saucebindings.session import SauceSession
```

To build from source:
```bash
git clone https://github.com/saucelabs/sauce_bindings
cd sauce_bindings
python setup.py install
```
<!--C#-->

**C# bindings are coming soon...**

<!--Ruby-->

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
<!--END_DOCUSAURUS_CODE_TABS-->

___
