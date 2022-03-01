# Sauce Bindings 

## A library for interacting with Sauce Labs using Ruby

This gem is intended as a way to interact with Sauce Labs in an obvious and straightforward way. 

## User Installation & Usage

Please see the [documentation on our website](https://opensource.saucelabs.com/sauce_bindings), 
and select the "Ruby" tab for code examples. 

## Running Tests

To run tests for the Ruby Sauce Bindings, execute

    $ bundle exec rake

## Building and Releasing

Use `gem` instead of `rake` so that it isn't auto-tagged on github

* Bump `SauceBindings::VERSION`
* `$ gem build sauce_bindings.gemspec`
* `$ gem push pkg/sauce_bindings-<VERSION>.gem`

## Contributing

Bug reports and pull requests are welcome on GitHub at https://github.com/sauce/sauce_bindings. 
This project is intended to be a safe, welcoming space for collaboration, 
and contributors are expected to adhere to the 
[Contributor Covenant](http://contributor-covenant.org) code of conduct.

## License

The gem is available as open source under the terms of the [MIT License](https://opensource.org/licenses/MIT).

## Code of Conduct

Everyone interacting in the SauceBindings project’s codebases, issue trackers, chat rooms and mailing lists 
is expected to follow the [code of conduct](https://github.com/saucelabs/sauce_bindings/blob/master/CODE_OF_CONDUCT.md).
