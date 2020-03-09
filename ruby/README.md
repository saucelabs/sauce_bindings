# SimpleSauce 
## Ruby's Sauce Labs Bindings!

This gem is intended as a way to easily interact with Sauce Labs in an obvious and straightforward way. 

## Installation

Add this line to your application's Gemfile:

```ruby
gem 'sauce_bindings'
```

And then execute:

    $ bundle

Or install it yourself as:

    $ gem install sauce_bindings

## Running Tests

To run tests for the Ruby Sauce Bindings, execute

    $ bundle exec rake

## Usage

`SimpleSauce` is broken into two main components, `Options` and `Session`

### Options class
`Options` provides an easy interface to the Sauce Labs specific settings you want in your tests.

If you want the default settings (the latest Chrome version on Windows 10) with no other settings,
this is all the code you need:
```
sauce_opts = SimpleSauce::Options.new
```
To specify additional 
[Sauce Labs supported settings](https://wiki.saucelabs.com/display/DOCS/Test+Configuration+Options),
simply pass these in as a `Hash`, or use an accessor:
```
sauce_options = {screen_resolution: '1080x720',
                 browser_name: 'Firefox',
                 platform_name: 'Mac OS'}
sauce_opts = SimpleSauce::Options.new(sauce_options)
sauce_opts.idle_timeout = 100
```
if you have additional browser specific settings, or 
[webdriver w3c spec compliant settings](http://w3c.github.io/webdriver/webdriver-spec.html#capabilities), in 
Selenium 3.x you need to generate each of these separately; see the `Session` class below for how to use it:
```
sauce_options = SimpleSauce::Options.new(screen_resolution: '1080x720',
                                         browser_name: 'Firefox',
                                         platform_name: 'Mac OS')

selenium_options = Selenium::WebDriver::Remote::Capabilities.new(accept_insecure_certs: false,
                                                                 page_load_strategy: 'eager'}

browser_options = Selenium::WebDriver::Firefox::Options.new('args' => ['-foo'])
```

### Session class
#### Intializing a Session
`Session` class gets initialized with an `Options` instance. If you want the
default settings (latest Chrome version on Windows 10), you don't even need to use an `Options` instance:
```ruby
@session = SauceBindings::Session.new
```
If you want something other than the default, create a Sauce `Option` instance (as generated in the section above,
then pass it into the constructor:
```ruby
@session = SauceBindings::Session.new(sauce_opts)
```
If you also have Selenium or Browser options as described above, then you pass them in with an `Array` like so:
```ruby
@session = SauceBindings::Session.new([sauce_opts, se_opts, browser_opts])
```

#### Creating a driver instance
The `#start` method is required, and will return a `Selenium::WebDriver::<Browser>::Driver` instance,
and the `#stop` method will automatically quit the driver as well as ending the Sauce Labs session.
```ruby
session = SauceBindings::Session.new
driver = session.start
# Use the driver
session.stop
```
Note that the `Driver` instance can also be obtained at any time with the `#driver` method:
```ruby
session = SauceBindings::Session.new
session.start
driver = session.driver
# Use the driver
session.stop
```
#### Additional Session methods
We have a number of features we plan to add; stay tuned to see what additional features are made available. If you
have any request, please contact the Sauce Labs Solution Architect team.

## Contributing

Bug reports and pull requests are welcome on GitHub at https://github.com/sauce/sauce_bindings. 
This project is intended to be a safe, welcoming space for collaboration, 
and contributors are expected to adhere to the 
[Contributor Covenant](http://contributor-covenant.org) code of conduct.

## License

The gem is available as open source under the terms of the [MIT License](https://opensource.org/licenses/MIT).

## Code of Conduct

Everyone interacting in the SimpleSauce project’s codebases, issue trackers, chat rooms and mailing lists 
is expected to follow the [code of conduct](https://github.com/saucelabs/sauce_bindings/blob/master/CODE_OF_CONDUCT.md).
