# frozen_string_literal: true

require 'sauce_bindings'
require 'rspec'

describe 'Create Session' do
  it 'creates default options' do
    session = SauceBindings::Session.new
    driver = session.start

    driver.get('https://www.saucedemo.com/')

    session.stop(true)
  end

  it 'accepts custom Sauce Options' do
    options = SauceBindings::Options.firefox(browser_version: '88.0')
    session = SauceBindings::Session.new(options)
    driver = session.start

    driver.get('https://www.saucedemo.com/')

    session.stop(true)
  end

  it 'accepts data center and options' do
    options = SauceBindings::Options.firefox(browser_version: '88.0')
    session = SauceBindings::Session.new(options, data_center: :EU_CENTRAL)
    driver = session.start

    driver.get('https://www.saucedemo.com/')

    session.stop(true)
  end

  it 'adds comments to the UI' do
    session = SauceBindings::Session.new
    driver = session.start

    driver.get('https://www.saucedemo.com/')
    session.annotate("Add this comment")

    session.stop(true)
  end

  it 'pauses the test' do
    session = SauceBindings::Session.new
    driver = session.start

    driver.get('https://www.saucedemo.com/')
    session.pause
  end

  it 'disables and enables logging' do
    session = SauceBindings::Session.new
    driver = session.start

    driver.get('https://www.saucedemo.com/')

    session.disable_logging
    driver.find_element(id: 'password').send_keys('secret_sauce')
    session.enable_logging

    session.stop(true)
  end

  it 'stops and starts network' do
    options = SauceBindings::Options.safari
    session = SauceBindings::Session.new(options)
    driver = session.start

    session.stop_network
    driver.get('https://www.saucedemo.com/')

    session.start_network
    driver.get('https://www.saucedemo.com/')

    session.stop(true)
  end

  it 'changes the name' do
    session = SauceBindings::Session.new
    driver = session.start

    driver.get('https://www.saucedemo.com/')

    session.change_name("New Name!")

    session.stop(true)
  end

  it 'adds tags' do
    session = SauceBindings::Session.new
    driver = session.start

    driver.get('https://www.saucedemo.com/')

    session.add_tags(%w[tag1 tag2])

    session.stop(true)
  end
end
