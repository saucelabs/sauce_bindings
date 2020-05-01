# frozen_string_literal: true

require 'sauce_bindings'
require 'rspec'

describe 'Browser Options' do
  before { WebMock.allow_net_connect! }

  it 'creates session' do
    # 1. Create Selenium Browser Options instance
    browser_options = Selenium::WebDriver::Firefox::Options.new(args: ['--foo'])

    # 2. Create Sauce Options object with the Browser Options object instance
    sauce_options = SauceBindings::Options.new(selenium_options: browser_options)

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
