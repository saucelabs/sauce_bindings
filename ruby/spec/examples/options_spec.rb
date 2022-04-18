# frozen_string_literal: true

require 'sauce_bindings'
require 'rspec'

describe 'Options' do
  it 'accepts default browser values' do
    sauce_options = SauceBindings::Options.firefox

    session = SauceBindings::Session.new(sauce_options)
    driver = session.start

    driver.get('https://www.saucedemo.com/')

    session.stop(true)
  end

  it 'accepts custom values' do
    sauce_options = SauceBindings::Options.firefox(browser_version: '88.0',
                                                   platform_name: 'Windows 8',
                                                   unhandled_prompt_behavior: 'ignore',
                                                   idle_timeout: 45,
                                                   time_zone: 'Alaska')

    session = SauceBindings::Session.new(sauce_options)
    driver = session.start

    driver.get('https://www.saucedemo.com/')

    session.stop(true)
  end

  it 'accepts custom and browser vendor options' do
    browser_options = Selenium::WebDriver::Chrome::Options.new(args: ['--start-fullscreen'])

    sauce_options = SauceBindings::Options.chrome(browser_options, browser_version: '88.0',
                                                                   platform_name: 'Windows 8',
                                                                   unhandled_prompt_behavior: 'ignore',
                                                                   idle_timeout: 45,
                                                                   time_zone: 'Alaska')

    session = SauceBindings::Session.new(sauce_options)
    driver = session.start

    driver.get('https://www.saucedemo.com/')

    session.stop(true)
  end
end
