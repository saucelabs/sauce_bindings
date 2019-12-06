# frozen_string_literal: true

require 'spec_helper'

module SimpleSauce
  describe Options do
    describe '#new' do
      let(:default_options) do
        {'browserName' => 'chrome',
         'browserVersion' => 'latest',
         'platformName' => 'Windows 10',
         'sauce:options' => {}}
      end

      it 'uses latest Chrome version on Windows 10 by default' do
        sauce_opts = SimpleSauce::Options.new

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

      it 'accepts Sauce Lab specific settings' do
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

        sauce_opts = SimpleSauce::Options.new(sauce_options)

        expected_options =  default_options.merge('sauce:options' => {})
        sauce_options.each  do |key, value|
          expected_options['sauce:options'][SimpleSauce::Options.send(:camel_case, key.to_s)] = value
        end

        expect(sauce_opts.capabilities).to eq(expected_options)
      end

      it 'raises ArgumentError if parameter is not recognized as valid' do
        expect { SimpleSauce::Options.new(foo: 'bar') }.to raise_exception(ArgumentError)
      end
    end

    it 'uses accessor' do
      sauce_opts = SimpleSauce::Options.new
      sauce_opts.browser_name = 'firefox'
      sauce_opts.idle_timeout = 3

      expect(sauce_opts.capabilities).to eq('browserName' => 'firefox',
                                            'browserVersion' => 'latest',
                                            'platformName' => 'Windows 10',
                                            'sauce:options' => {'idleTimeout' => 3})
    end
  end
end
