---
id: test-runners
title: Test Runners
sidebar_label: Test Runners
---

Every programming language allows you can write code in a standalone file where you could import the
Selenium library, take actions in a browser, and have it throw an exception if there is a problem. 
Except this doesn't scale very well, and there is so much functionality specific to the testing domain 
that this approach misses out on. 
This is why major testing initiatives leverage test runners to organize test code, create hooks for common behavior,
accommodate a large assertion library, provide tags for customization, 
allow plugins for parallelization, include reporting that differentiates failures from exceptions, etc.

To provide additional value, Sauce Bindings is releasing additional libraries to improve integrating with
Sauce Labs via specific test runners. 

### What You Get From Using a Sauce Bindings Test Runner Library
* Sauce Labs names your tests for you based on the test's method name
* Sauce Labs automatically marks your tests with pass / fail information at the end of the test
* Sauce Labs logs the failure message in the job's command list
* Sauce Labs logs a limited stack trace in the job's command list

### Specific Implementations
* JUnit5 - https://github.com/saucelabs/sauce_bindings/tree/main/java/junit5
* JUnit4 - https://github.com/saucelabs/sauce_bindings/tree/main/java/junit4
* TestNG - https://github.com/saucelabs/sauce_bindings/tree/main/java/testng
* PyTest - Coming Soon!
* RSpec - Coming Soon!
* Ruby Cucumber - Coming Soon!
