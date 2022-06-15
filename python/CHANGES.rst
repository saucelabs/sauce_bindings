Changelog
==========================

1.3.0 - Jun 15, 2022
--------------------

* Fix links to work all data centers
* Manage credentials in Options instead of Session
* Deprecate using magic strings for stopping a session
* Require Selenium 4
* Implement pausing
* Implement commenting in Sauce command list
* Implement logging & network control
* Implement updating test information

1.2.1 - Mar 1, 2022
--------------------

* Support latest Windows and Mac platforms

1.2.0 - Jul 12, 2021
--------------------

* Accessibility functionality via Sa11y

1.1.1 - Jul 10, 2021
--------------------

* `SauceOptions` browser methods allow setting only valid options for the provided browser
* Support Selenium 4


1.1.0 - May 31, 2021
--------------------

* Build Name support for Github Actions
* APAC data center supported

1.0.0 - Apr 24, 2020
--------------------

* Link to job on Sauce UI in console

1.0.0b1 PRE-RELEASE - Mar 12, 2020
----------------------------------

* Add support for resolving data center IP

0.1.2 - Mar 5, 2020
--------------------

* Project renamed from "Simple Sauce"

0.1.1 - Mar 5, 2020
--------------------

* Bad Release

0.1.0 - Feb 14, 2020
--------------------

* `SauceOptions` class to construct capabilities
* `SauceSession` class to wrap driver instance
* `data_center` property with setter in `SauceSession` to choose data center
* `SauceOptions` automatically uses default value for `build` name
* `SauceOptions` class constructor accepts `seleniumOptions` classes as argument
* `SauceOptions` accepts serialized information via a `Hash`
* Sauce options for `user` and `access_key` must be obtained by environment variables

