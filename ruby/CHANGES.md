### 1.3.0 - June 15, 2022

* Fix links to work all data centers
* Manage credentials in Options instead of Session
* Deprecate using magic strings for stopping a session
* Implement pausing — `Session#pause`
* Implement commenting in Sauce command list — `Session#annotate` 
* Implement logging control — `Session#enable_logs`, `Session#disable_logs` 
* Implement network control — `Session#stop_network`, `Session#start_network`
* Implement updating test information — `Session#change_name`, `Session#tags=`
* Require Selenium 4 and Ruby 2.7

### 1.2.1 - March 1, 2022

* Added latest Windows and Mac platforms 

### 1.2.0 - July 12, 2021

* Accessibility functionality via Sa11y

### 1.1.1 - July 10, 2021

* `Options` class methods only allow setting valid options for the provided browser
* `Options` class supports Edge browser
* Support Selenium 4

### 1.1.0 - May 30, 2021

* Build Name support for Github Actions
* APAC data center tentatively supported
* Update `Options` to be constructed with class methods for browsers

### 1.0.0 - April 24, 2020

* Link to job on Sauce UI in STDOUT

### 1.0.0.beta1 - March 10, 2020

* `Options` class to construct capabilities
* `Session` class to wrap driver instance
* `Session#data_center=` to choose data center
* `Options` automatically uses default value for `build` name
* `Options` class accepts Selenium `Options` classes as argument
* `Options` accepts serialized information via a `Hash`
* `CapybaraSession` class for special Capybara support
* Sauce options for `user` and `access_key` must be obtained by ENV variables
