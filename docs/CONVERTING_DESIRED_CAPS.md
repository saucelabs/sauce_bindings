---
id: converting-desired-caps
title: Converting From Desired Capabilities
sidebar_label: Conversion
---

### The Amount of Work to Convert Existing Code Varies

Because SauceBindings is generating W3C compliant syntax, it can't just accept existing instances of 
DesiredCapabilities classes as inputs to SauceOptions. Additionally, existing Selenium classes
for Desired Capabilities are being deprecated in several of the bindings for the eventual 4.0 release. 

Depending on many factors, existing code bases can have relatively straightforward capability generation
or extremely complicated setups including any combination of property files, yaml, json, xml, csl, nested conditionals,
toggles, enums and strings. Some of these approaches are more common in some languages than others.

This Tutorial will walk through several examples for how to convert existing capabilities to use Sauce Bindings

### The Simple Conditional

<!--DOCUSAURUS_CODE_TABS-->
<!--Java-->

```java
```

<!--Python-->
```python
```
<!--Ruby-->
```ruby
```
<!--C#-->
<br />

**C# bindings are coming soon...**

<!--END_DOCUSAURUS_CODE_TABS-->

### Nested Conditionals

### Using Serialized File

### Using Data Provider (Java)
(For the record, we discourage this usage)

___
