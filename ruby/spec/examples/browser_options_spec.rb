# frozen_string_literal: true

require 'sauce_bindings'
require 'rspec'

describe 'Browser Options' do
  it 'creates session' do
    # 1. Create Selenium Browser Options instance
    browser_options = Selenium::WebDriver::Chrome::Options.new(args: ['--start-fullscreen'])

    # 2. Create Sauce Options object with the Browser Options object instance
    sauce_options = SauceBindings::Options.chrome(selenium_options: browser_options)

    # 3. Create Session object with SauceOptions object instance
    session = SauceBindings::Session.new(sauce_options)

    # 4. Start Session to get the Driver
    driver = session.start

    # 5. Use the driver in your tests just like normal
    driver.get('https://www.saucedemo.com/')

    # 6. Stop the Session with whether the test passed or failed
    session.stop(true)
  end
end
