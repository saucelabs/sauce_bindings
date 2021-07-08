# frozen_string_literal: true

require 'sauce_bindings'
require 'rspec'

describe 'Create Session' do
  it 'starts session' do
    # 1. Create Session object with the defaults
    session = SauceBindings::Session.new

    # 2. Start Session to get the Driver
    driver = session.start

    # 3. Use the driver in your tests just like normal
    driver.get('https://www.saucedemo.com/')

    # 4. Stop the Session with whether the test passed or failed
    session.stop(true)
  end
end
