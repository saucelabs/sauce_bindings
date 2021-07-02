# frozen_string_literal: true

require 'sauce_bindings'
require 'rspec'

describe 'Create Session' do
  it 'starts session' do
    # 1. Create Session object with the desired Data Center
    session = SauceBindings::Session.new

    # 2. Start Session to get the Driver
    driver = session.start

    # 3. Use the driver in your tests just like normal
    driver.get('https://www.saucedemo.com/')

    # 4a. Get accessibility default results with frame support
    session.accessibility_results

    # 4b. Get accessibility default results without frame support
    session.accessibility_results(frames: false)

    # 4c. Get accessibility results with cross origin frame support
    session.accessibility_results(cross_origin: true)

    # 4d. Get accessibility results with a different version of Axe Core JS Library
    session.accessibility_results(js_lib: File.read(File.expand_path('axe.min.js', __dir__)))

    # 5. Stop the Session with whether the test passed or failed
    session.stop(true)
  end
end
