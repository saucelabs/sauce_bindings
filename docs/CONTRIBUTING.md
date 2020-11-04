---
id: contributing
title: Become a Contributor
sidebar_label: Contribute
---
Sauce Labs proudly supports open source technologies and encourages open source projects like Sauce Bindings. 
If you would like to contribute there are several ways of doing so.

## Ways To Contribute

The project offers a variety of ways to contribute:

* submit code features
* improve documentation (the code for this website is 
[on github](https://github.com/saucelabs/sauce_bindings/tree/master/docs))
* create educational content (blog posts, tutorials, videos, etc.)
* spread the good word about Sauce Bindings (e.g. via Twitter)
* [report bugs](https://github.com/saucelabs/sauce_bindings/issues) if you discover them while using Sauce Bindings
* See something you'd like fixed? Have a good idea for how to improve something? 
[Create an issue](https://github.com/saucelabs/sauce_bindings/issues) or add to an existing issue. 

### Contributing Docs

A great way to start working with any open source project is through improving documentation. 
To get started, follow [instructions](https://github.com/saucelabs/sauce_bindings/blob/master/website/README.md).

### Contributing Code

We love and welcome contributions from the community! We have several language bindings that we support. 
There are also [good first issues](https://github.com/saucelabs/sauce_bindings/issues?q=is%3Aissue+is%3Aopen+label%3A%22good+first+issue%22) labeled if you'd like to get started on development but don't know how.
For all of the bindings you need to do the following first

1. Fork the latest code to your account

2. Clone the code onto your local computer

```bash
git clone https://github.com/saucelabs/sauce_bindings.git
``` 

3. Navigate to project directory (root/sauce_bindings)

4. Run tests
<!--DOCUSAURUS_CODE_TABS-->
<!--Java-->

```bash
make java_tests
```

<!--Python-->

```python
make python_tests
```

<!--Ruby-->

```ruby
make ruby_tests
```

<!--C#-->

```c#
make dotnet_tests
```

<!--END_DOCUSAURUS_CODE_TABS-->

5. Implement your feature (make sure you add tests!)
    * Note: for Java, you need to install the [Lombok plugin](https://plugins.jetbrains.com/plugin/6317-lombok)

6. Submit a pull request
