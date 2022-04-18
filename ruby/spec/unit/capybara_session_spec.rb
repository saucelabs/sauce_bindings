# frozen_string_literal: true

require 'spec_helper'
require 'sauce_bindings/capybara_session'

module SauceBindings
  describe CapybaraSession do
    let(:valid_response) do
      {status: 200,
       body: {value: {sessionId: '0', capabilities: Selenium::WebDriver::Remote::Capabilities.chrome}}.to_json,
       headers: {content_type: 'application/json'}}
    end
    let(:default_capabilities) do
      {browserName: 'chrome',
       browserVersion: 'latest',
       platformName: 'Windows 10',
       'sauce:options': {username: 'foo',
                         accessKey: '123',
                         build: 'TEMP BUILD: 11'}}
    end

    def expect_request
      body = {capabilities: {alwaysMatch: default_capabilities}}.to_json

      endpoint = 'https://ondemand.us-west-1.saucelabs.com/wd/hub/session'
      stub_request(:post, endpoint).with(body: body).to_return(valid_response)
    end

    describe '#new' do
      it 'registers a Capybara Driver' do
        CapybaraSession.new

        expect(Capybara.drivers.names).to include(:sauce)
        expect(Capybara.current_driver).to eq :sauce
      end
    end

    describe '#start' do
      it 'starts the session with Capybara driver' do
        expect_request

        ClimateControl.modify(**SAUCE_ACCESS, **BUILD_ENV) do
          @driver = CapybaraSession.new.start
        end

        expect(@driver).to eq Capybara.current_session.driver.browser

        Capybara.current_session.driver.instance_variable_set(:@browser, nil)
      end
    end

    describe '#stop' do
      it 'quits the driver' do
        cappy_driver = instance_double(Capybara::Selenium::Driver)
        driver = instance_double(Selenium::WebDriver::Remote::Driver, session_id: '1234')
        allow(cappy_driver).to receive(:browser).and_return(driver)

        allow(SauceWhisk::Jobs).to receive(:change_status).with('1234', true)

        session = CapybaraSession.new

        allow(Capybara.current_session).to receive(:driver).and_return(cappy_driver)
        allow(Capybara.current_session).to receive(:quit)

        session.start
        session.stop(true)

        expect(SauceWhisk::Jobs).to have_received(:change_status).with('1234', true)
        expect(Capybara.current_session).to have_received(:quit)

        Capybara.current_session.driver.instance_variable_set(:@browser, nil)
      end
    end
  end
end
