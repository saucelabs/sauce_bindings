# Simple Sauce .NET
[![Build Status For .NET Only](https://dev.azure.com/nikolayadvolodkin/SimpleSauce/_apis/build/status/saucelabs.simple_sauce?branchName=master)](https://dev.azure.com/nikolayadvolodkin/SimpleSauce/_build/latest?definitionId=18&branchName=master)
[![.NET on MacOS-latest](https://dev.azure.com/nikolayadvolodkin/SimpleSauce/_apis/build/status/.NET%20on%20macOS-latest?branchName=master)](https://dev.azure.com/nikolayadvolodkin/SimpleSauce/_build/latest?definitionId=20&branchName=master)


## Migrating to Simple Sauce
Simple Sauce uses Selenium 4.0+ which introduces a slightly different API than previous versions.
However, you don't need to care about that with Simple Sauce. Rather, you need to update your Selenium version.

Can I force the installation of the latest Selenium to happen with a Nuget install?
1. ```Install-Package Selenium.WebDriver -Version 4.0.0-alpha03```
2. 

### Issues that you might face migrating to Simple Sauce
1. DesiredCapabilities is depracated in Selenium 4.0+.
However, this is not a big deal as we need to replace this with Simple Sauce anyways
