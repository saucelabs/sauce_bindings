# frozen_string_literal: true

require 'spec_helper'
require 'webmock/rspec'

module SimpleSauce
  describe Session do
    let(:valid_response) do
      {status: 200,
       body: {value: {sessionId: 0, capabilities: Selenium::WebDriver::Remote::Capabilities.chrome}}.to_json,
       headers: {"content_type": 'application/json'}}
    end
    let(:default_capabilities) do
      {browserName: 'chrome',
       browserVersion: 'latest',
       platformName: 'Windows 10',
       'sauce:options': {build: 'TEMP BUILD: 11'}}
    end

    def expect_request(body: nil, endpoint: nil)
      body = (body || {desiredCapabilities: default_capabilities,
                       capabilities: {firstMatch: [default_capabilities]}}).to_json
      endpoint ||= 'https://ondemand.saucelabs.com/wd/hub/session'
      stub_request(:post, endpoint).with(body: body).to_return(valid_response)
    end

    before do
      ENV['BUILD_TAG'] = ''
      ENV['BUILD_NAME'] = 'TEMP BUILD'
      ENV['BUILD_NUMBER'] = '11'
      ENV['SAUCE_USERNAME'] = 'foo'
      ENV['SAUCE_ACCESS_KEY'] = '123'
    end

    after do
      ENV.delete 'BUILD_TAG'
      ENV.delete 'BUILD_NAME'
      ENV.delete 'BUILD_NUMBER'
      ENV.delete 'SAUCE_USERNAME'
      ENV.delete 'SAUCE_ACCESS_KEY'
    end

    describe '#new' do
      it 'creates default Options instance if none is provided' do
        session = SimpleSauce::Session.new

        expected_results = {url: 'https://foo:123@ondemand.saucelabs.com:443/wd/hub',
                            desired_capabilities: {'browserName' => 'chrome',
                                                   'browserVersion' => 'latest',
                                                   'platformName' => 'Windows 10',
                                                   'sauce:options' => {'build' => 'TEMP BUILD: 11'}}}
        expect(session.to_selenium).to eq expected_results
      end

      it 'uses provided Options class' do
        sauce_opts = Options.new(browser_version: '123',
                                 platform_name: 'Mac',
                                 idle_timeout: 4)
        session = SimpleSauce::Session.new(sauce_opts)

        expected_results = {url: 'https://foo:123@ondemand.saucelabs.com:443/wd/hub',
                            desired_capabilities: {'browserName' => 'chrome',
                                                   'browserVersion' => '123',
                                                   'platformName' => 'Mac',
                                                   'sauce:options' => {'idleTimeout' => 4,
                                                                       'build' => 'TEMP BUILD: 11'}}}
        expect(session.to_selenium).to eq expected_results
      end

      it 'defaults to US West data Center' do
        session = SimpleSauce::Session.new
        expect(session.data_center).to eq :US_WEST
      end
    end

    describe '#start' do
      it 'starts the session and returns Selenium Driver instance' do
        expect_request

        driver = SimpleSauce::Session.new.start
        expect(driver).to be_a Selenium::WebDriver::Driver
      end

      it 'raises exception if no username set' do
        ENV.delete('SAUCE_USERNAME')

        expect { SimpleSauce::Session.new.start }.to raise_exception(ArgumentError)
      end

      it 'raises exception if no access key set' do
        ENV.delete('SAUCE_ACCESS_KEY')

        expect { SimpleSauce::Session.new.start }.to raise_exception(ArgumentError)
      end
    end

    describe '#stop' do
      it 'quits the driver' do
        driver = instance_double(Selenium::WebDriver::Remote::Driver, session_id: '1234')
        allow(Selenium::WebDriver::Driver).to receive(:for).and_return(driver)
        allow(driver).to receive :quit
        allow(SauceWhisk::Jobs).to receive(:change_status).with('1234', true)

        session = SimpleSauce::Session.new

        session.start
        session.stop(true)

        expect(driver).to have_received(:quit)
        expect(SauceWhisk::Jobs).to have_received(:change_status).with('1234', true)
      end
    end

    describe '#data_center=' do
      it 'overrides default value for data center' do
        session = SimpleSauce::Session.new
        session.data_center = :US_EAST

        expect(session.url).to eq('https://foo:123@us-east-1.saucelabs.com:443/wd/hub')
      end

      it 'raises exception if data center is invalid' do
        session = SimpleSauce::Session.new

        expect { session.data_center = :FOO }.to raise_exception(ArgumentError)
      end
    end

    describe '#username=' do
      it 'accepts provided username' do
        session = SimpleSauce::Session.new
        session.username = 'name'

        expect(session.url).to eq('https://name:123@ondemand.saucelabs.com:443/wd/hub')
      end
    end

    describe '#access_key=' do
      it 'accepts provided access key' do
        session = SimpleSauce::Session.new
        session.access_key = '456'

        expect(session.url).to eq('https://foo:456@ondemand.saucelabs.com:443/wd/hub')
      end
    end
  end
end
