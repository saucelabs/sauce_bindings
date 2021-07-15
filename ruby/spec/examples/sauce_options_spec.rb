# frozen_string_literal: true

require 'sauce_bindings'
require 'rspec'

describe 'Sauce Options' do
  it 'creates session' do
    # 1. Create a SauceOptions instance with Sauce Labs Specific Options
    sauce_options = SauceBindings::Options.firefox(extended_debugging: true,
                                                   idle_timeout: 45,
                                                   time_zone: 'Alaska')

    # 2. Create Session object with SauceOptions object instance
    session = SauceBindings::Session.new(sauce_options)

    # 3. Start Session to get the Driver
    driver = session.start

    # 4. Use the driver in your tests just like normal
    driver.get('https://www.saucedemo.com/')

    # 5. Stop the Session with whether the test passed or failed
    session.stop(true)
  end
end
