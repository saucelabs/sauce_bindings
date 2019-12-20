# frozen_string_literal: true

require 'spec_helper'

module SimpleSauce
  describe Options do
    before do
      ENV['BUILD_TAG'] = ''
      ENV['BUILD_NAME'] = 'TEMP BUILD'
      ENV['BUILD_NUMBER'] = '11'
    end

    after { ENV.delete 'BUILD_TAG' }

    describe '#new' do
      let(:default_options) do
        {'browserName' => 'chrome',
         'browserVersion' => 'latest',
         'platformName' => 'Windows 10',
         'sauce:options' => {'build' => 'TEMP BUILD: 11'}}
      end

      it 'uses latest Chrome version on Windows 10 by default' do
        sauce_opts = Options.new

        expect(sauce_opts.capabilities).to eq(default_options)
      end

      it 'accepts provided values for browser, browser version and platform name' do
        options = Options.new(browser_name: 'firefox',
                              browser_version: '123',
                              platform_name: 'Mac')

        expect(options.browser_name).to eq 'firefox'
        expect(options.browser_version).to eq '123'
        expect(options.platform_name).to eq 'Mac'
      end

      it 'accepts other w3c values' do
        options = Options.new(accept_insecure_certs: true,
                              page_load_strategy: 'eager')

        expect(options.accept_insecure_certs).to eq true
        expect(options.page_load_strategy).to eq 'eager'
      end

      it 'accepts Sauce Labs specific settings' do
        sauce_options = {max_duration: 1,
                         command_timeout: 2,
                         idle_timeout: 3,
                         name: 'foo',
                         build: 'bar',
                         tags: %w[foo bar],
                         parent_tunnel: 'bar',
                         tunnel_identifier: 'foobar',
                         screen_resolution: '10x10',
                         time_zone: 'Foo',
                         extended_debugging: true,
                         capture_performance: true,
                         record_video: false,
                         video_upload_on_pass: false,
                         record_screenshots: false,
                         record_logs: false,
                         username: 'foo',
                         access_key: '1234',
                         passed: true}

        sauce_opts = Options.new(sauce_options)

        expected_options = default_options.merge('sauce:options' => {})
        sauce_options.each do |key, value|
          expected_options['sauce:options'][Options.send(:camel_case, key.to_s)] = value
        end

        expect(sauce_opts.capabilities).to eq(expected_options)
      end

      it 'accepts Selenium Capabilities and overrides default browser' do
        caps = Selenium::WebDriver::Remote::Capabilities.firefox(accept_insecure_certs: true,
                                                                 page_load_strategy: 'eager')
        options = Options.new(selenium_options: caps)

        expect(options.browser_name).to eq 'firefox'
        expect(options.selenium_options['pageLoadStrategy']).to eq 'eager'
        expect(options.selenium_options['acceptInsecureCerts']).to eq true
      end

      it 'accepts Selenium Options and overrides default browser' do
        browser_opts = Selenium::WebDriver::Firefox::Options.new(args: ['--foo'])
        options = Options.new(selenium_options: browser_opts)

        expect(options.browser_name).to eq 'firefox'
        # Note - this is a bug in Selenium
        expect(options.selenium_options['moz:firefoxOptions']).to eq(args: ['--foo'])
      end

      it 'accepts Selenium Capabilities and Options class instances' do
        caps = Selenium::WebDriver::Remote::Capabilities.chrome(accept_insecure_certs: true,
                                                                page_load_strategy: 'eager')
        browser_opts = Selenium::WebDriver::Chrome::Options.new(args: ['--foo'])
        options = Options.new(selenium_options: [caps, browser_opts])

        expect(options.selenium_options['pageLoadStrategy']).to eq 'eager'
        expect(options.selenium_options['acceptInsecureCerts']).to eq true
        # Note - this is a bug in Selenium
        expect(options.selenium_options['goog:chromeOptions']).to eq(args: ['--foo'])
      end

      it 'accepts W3C, Sauce, Browser Options and Capabilities at the same time' do
        caps = Selenium::WebDriver::Remote::Capabilities.chrome(page_load_strategy: 'eager')
        browser_opts = Selenium::WebDriver::Firefox::Options.new(args: ['--foo'])

        options = Options.new(browser_name: 'firefox',
                              browser_version: '77',
                              accept_insecure_certs: true,
                              command_timeout: 2,
                              time_zone: 'Alaska',
                              selenium_options: [caps, browser_opts])

        expect(options.browser_name).to eq 'firefox'
        expect(options.browser_version).to eq '77'
        expect(options.platform_name).to eq 'Windows 10'
        expect(options.accept_insecure_certs).to eq true

        expect(options.selenium_options['pageLoadStrategy']).to eq 'eager'
        # Note - this is a bug in Selenium
        expect(options.selenium_options['moz:firefoxOptions']).to eq(args: ['--foo'])
      end

      it 'creates a default build value' do
        sauce_opts = Options.new
        expect(sauce_opts.capabilities.dig('sauce:options', 'build')).to eq 'TEMP BUILD: 11'
      end

      it 'raises ArgumentError if parameter is not recognized as valid' do
        expect { Options.new(foo: 'bar') }.to raise_exception(ArgumentError)
      end
    end

    describe '#accessors' do
      it 'parses w3c values' do
        sauce_opts = Options.new
        sauce_opts.browser_name = 'firefox'
        sauce_opts.browser_version = '7'
        sauce_opts.platform_name = 'macOS 10.14'

        expect(sauce_opts.capabilities).to eq('browserName' => 'firefox',
                                              'browserVersion' => '7',
                                              'platformName' => 'macOS 10.14',
                                              'sauce:options' => {'build' => 'TEMP BUILD: 11'})
      end

      it 'parses Sauce values' do
        sauce_opts = Options.new
        sauce_opts.idle_timeout = 3
        sauce_opts.build = 'CUSTOM BUILD'
        sauce_opts.name = 'TEST NAME'
        sauce_opts.record_screenshots = false

        expect(sauce_opts.capabilities['sauce:options']).to eq('idleTimeout' => 3,
                                                               'recordScreenshots' => false,
                                                               'name' => 'TEST NAME',
                                                               'build' => 'CUSTOM BUILD')
      end
    end

    describe '#capabilities' do
      it 'correctly generates capabilities for w3c values' do
        options = Options.new(browser_name: 'firefox',
                              accept_insecure_certs: true)

        expect(options.capabilities).to eq('browserName' => 'firefox',
                                           'browserVersion' => 'latest',
                                           'platformName' => 'Windows 10',
                                           'acceptInsecureCerts' => true,
                                           'sauce:options' => {'build' => 'TEMP BUILD: 11'})
      end

      it 'correctly generates capabilities for sauce specific values' do
        options = Options.new(command_timeout: 2,
                              time_zone: 'Alaska')

        expect(options.capabilities).to eq('browserName' => 'chrome',
                                           'browserVersion' => 'latest',
                                           'platformName' => 'Windows 10',
                                           'sauce:options' => {'build' => 'TEMP BUILD: 11',
                                                               'commandTimeout' => 2,
                                                               'timeZone' => 'Alaska'})
      end

      it 'correctly generates capabilities for selenium object values' do
        caps = Selenium::WebDriver::Remote::Capabilities.chrome(accept_insecure_certs: true,
                                                                page_load_strategy: 'eager')
        browser_opts = Selenium::WebDriver::Chrome::Options.new(args: ['--foo'])
        options = Options.new(selenium_options: [caps, browser_opts])

        jwp_defaults = {'cssSelectorsEnabled' => true,
                        'javascriptEnabled' => true,
                        'nativeEvents' => false,
                        'platform' => 'ANY',
                        'rotatable' => false,
                        'takesScreenshot' => false,
                        'version' => ''}

        expected_caps = {'browserName' => 'chrome',
                         'browserVersion' => 'latest',
                         'platformName' => 'Windows 10',
                         'acceptInsecureCerts' => true,
                         'pageLoadStrategy' => 'eager',
                         'sauce:options' => {'build' => 'TEMP BUILD: 11'},
                         'goog:chromeOptions' => {args: ['--foo']}}

        expect(options.capabilities).to eq(jwp_defaults.merge(expected_caps))
      end
    end
  end
end
