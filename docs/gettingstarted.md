---
id: gettingstarted
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
    <artifactId>simple-sauce</artifactId>
    <version>1.0.0</version>
</dependency>
```

Alternately, you can download the Jar from \<HERE\> and load it into your IDE.

<!--Python-->
1. Install the package:
```bash
pip install simplesauce
```
2. Import it into your project
```python
from simplesauce.options import SauceOptions
from simplesauce.session import SauceSession
```

To build from source:
```bash
git clone https://github.com/saucelabs/simple_sauce
cd simple_sauce
python setup.py install
```
<!--C#-->

1. Install the Simple Sauce Nuget package
```bash
Install-Package ????
```
2. Install the most recent version of Selenium 4
```bash
Install-Package Selenium.WebDriver -Version 4.0.0-alpha03
```
<!--Ruby-->

1. Add it to your Gemfile:
```ruby
gem 'simple_sauce'
```
2. Require it in your project:
```ruby
require 'simple_sauce'
```

Alternately, to try it out you can install it on your system:
```bash
gem install simple_sauce
```
<!--END_DOCUSAURUS_CODE_TABS-->

___
