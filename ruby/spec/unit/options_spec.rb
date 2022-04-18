# frozen_string_literal: true

require 'spec_helper'

module SauceBindings
  describe Options do
    describe '::from_file' do
      it 'loads yaml options from configuration' do
        options = Options.from_file('spec/options.yml', 'firefox_mac')

        expect(options.browser_name).to eq 'firefox'
        expect(options.browser_version).to eq '123'
        expect(options.platform_name).to eq 'Mac'
        expect(options.accept_insecure_certs).to eq true
        expect(options.page_load_strategy).to eq 'eager'
        expect(options.set_window_rect).to eq true
        expect(options.unhandled_prompt_behavior).to eq 'accept'
        expect(options.strict_file_interactability).to eq true
        expect(options.implicit_wait_timeout).to eq 1
        expect(options.page_load_timeout).to eq 59
        expect(options.script_timeout).to eq 29
        expect(options.build).to eq 'Sample Build Name'
        expect(options.command_timeout).to eq 2
        expect(options.custom_data).to eq(foo: 'foo', bar: 'bar')
        expect(options.extended_debugging).to eq true
        expect(options.idle_timeout).to eq 3
        expect(options.geckodriver_version).to eq '0.23'
        expect(options.max_duration).to eq 300
        expect(options.name).to eq 'Sample Test Name'
        expect(options.parent_tunnel).to eq 'Mommy'
        expect(options.prerun).to eq(executable: 'http://url.to/your/executable.exe',
                                     args: %w[--silent -a -q],
                                     background: false,
                                     timeout: 120)
        expect(options.priority).to eq 0
        expect(options.public).to eq 'team'
        expect(options.record_logs).to eq false
        expect(options.record_screenshots).to eq false
        expect(options.record_video).to eq false
        expect(options.screen_resolution).to eq '10x10'
        expect(options.selenium_version).to eq '3.141.59'
        expect(options.tags).to eq %w[foo bar foobar]
        expect(options.time_zone).to eq 'San Francisco'
        expect(options.tunnel_identifier).to eq 'tunnelname'
        expect(options.video_upload_on_pass).to eq false
      end

      it 'loads json options from configuration' do
        options = Options.from_file('spec/options.json', 'firefox_mac')

        expect(options.browser_name).to eq 'firefox'
        expect(options.browser_version).to eq '123'
        expect(options.platform_name).to eq 'Mac'
        expect(options.accept_insecure_certs).to eq true
        expect(options.page_load_strategy).to eq 'eager'
        expect(options.set_window_rect).to eq true
        expect(options.unhandled_prompt_behavior).to eq 'accept'
        expect(options.strict_file_interactability).to eq true
        expect(options.implicit_wait_timeout).to eq 1
        expect(options.page_load_timeout).to eq 59
        expect(options.script_timeout).to eq 29
        expect(options.build).to eq 'Sample Build Name'
        expect(options.command_timeout).to eq 2
        expect(options.custom_data).to eq(foo: 'foo', bar: 'bar')
        expect(options.extended_debugging).to eq true
        expect(options.idle_timeout).to eq 3
        expect(options.geckodriver_version).to eq '0.23'
        expect(options.max_duration).to eq 300
        expect(options.name).to eq 'Sample Test Name'
        expect(options.parent_tunnel).to eq 'Mommy'
        expect(options.prerun).to eq(executable: 'http://url.to/your/executable.exe',
                                     args: %w[--silent -a -q],
                                     background: false,
                                     timeout: 120)
        expect(options.priority).to eq 0
        expect(options.public).to eq 'team'
        expect(options.record_logs).to eq false
        expect(options.record_screenshots).to eq false
        expect(options.record_video).to eq false
        expect(options.screen_resolution).to eq '10x10'
        expect(options.selenium_version).to eq '3.141.59'
        expect(options.tags).to eq %w[foo bar foobar]
        expect(options.time_zone).to eq 'San Francisco'
        expect(options.tunnel_identifier).to eq 'tunnelname'
        expect(options.video_upload_on_pass).to eq false
      end

      it 'raises exception if value not recognized' do
        msg = '{:foo=>"bar"} are not valid parameters for Options class with firefox'
        expect {
          Options.from_file('spec/options.yml', 'invalid_option')
        }.to raise_exception(ArgumentError, msg)
      end
    end

    describe '::chrome' do
      it 'uses latest Chrome version on Windows 10 by default' do
        options = Options.chrome

        expect(options.browser_name).to eq 'chrome'
        expect(options.browser_version).to eq 'latest'
        expect(options.platform_name).to eq 'Windows 10'
      end

      it 'accepts correct Selenium Options class' do
        browser_opts = Selenium::WebDriver::Chrome::Options.new(args: ['-foo'])
        options = Options.chrome(browser_opts)

        expect(options.selenium_options.dig('goog:chromeOptions', 'args')).to eq ['-foo']
      end

      it 'does not accept incorrect Selenium Options class' do
        browser_opts = Selenium::WebDriver::Firefox::Options.new

        expect { Options.chrome(browser_opts) }.to raise_exception(ArgumentError)
      end

      it 'accepts correct Selenium Capabilities class' do
        browser_opts = Selenium::WebDriver::Remote::Capabilities.chrome(browser_version: '99')
        options = Options.chrome(browser_opts)

        expect(options.browser_version).to eq '99'
      end

      it 'does not accept incorrect Selenium Capabilities class' do
        browser_opts = Selenium::WebDriver::Remote::Capabilities.firefox(browser_version: '99')

        expect { Options.chrome(browser_opts) }.to raise_exception(ArgumentError)
      end

      it 'accepts base configurations' do
        options = Options.chrome(custom_data: {foo: 'bar'})
        options.tags = %w[foo bar]

        expect(options.custom_data).to eq(foo: 'bar')
        expect(options.tags).to eq %w[foo bar]
      end

      it 'accepts vdc configurations' do
        options = Options.chrome(browser_version: '42.0')
        options.page_load_strategy = 'eager'

        expect(options.browser_version).to eq '42.0'
        expect(options.page_load_strategy).to eq 'eager'
      end

      it 'accepts valid configurations' do
        options = Options.chrome(extended_debugging: true)
        options.capture_performance = true

        expect(options.extended_debugging).to eq true
        expect(options.capture_performance).to eq true
      end

      it 'does not accept invalid configurations' do
        expect { Options.chrome(geckodriver_version: 'anything') }.to raise_exception(ArgumentError)
      end
    end

    describe '::edge' do
      it 'uses latest Edge version on Windows 10 by default' do
        options = Options.edge

        expect(options.browser_name).to eq 'MicrosoftEdge'
        expect(options.browser_version).to eq 'latest'
        expect(options.platform_name).to eq 'Windows 10'
      end

      it 'accepts correct Selenium Options class' do
        browser_opts = Selenium::WebDriver::Edge::Options.new(args: ['-foo'])
        options = Options.edge(browser_opts)

        expect(options.selenium_options.dig('ms:edgeOptions', 'args')).to eq ['-foo']
      end

      it 'does not accept incorrect Selenium Options class' do
        browser_opts = Selenium::WebDriver::Chrome::Options.new

        expect { Options.edge(browser_opts) }.to raise_exception(ArgumentError)
      end

      it 'accepts correct Selenium Capabilities class' do
        browser_opts = Selenium::WebDriver::Remote::Capabilities.edge(browser_version: '99')
        options = Options.edge(browser_opts)

        expect(options.browser_version).to eq '99'
      end

      it 'does not accept incorrect Selenium Capabilities class' do
        browser_opts = Selenium::WebDriver::Remote::Capabilities.chrome(browser_version: '99')

        expect { Options.edge(browser_opts) }.to raise_exception(ArgumentError)
      end

      it 'accepts base configurations' do
        options = Options.edge(custom_data: {foo: 'bar'})
        options.tags = %w[foo bar]

        expect(options.custom_data).to eq(foo: 'bar')
        expect(options.tags).to eq %w[foo bar]
      end

      it 'accepts vdc configurations' do
        options = Options.edge(browser_version: '42.0')
        options.page_load_strategy = 'eager'

        expect(options.browser_version).to eq '42.0'
        expect(options.page_load_strategy).to eq 'eager'
      end

      it 'accepts valid configurations' do
        options = Options.edge(edgedriver_version: '99')
        options.selenium_version = '3.14'

        expect(options.selenium_version).to eq '3.14'
        expect(options.edgedriver_version).to eq '99'
      end

      it 'does not accept invalid configurations' do
        expect { Options.edge(chromedriver_version: 'anything') }.to raise_exception(ArgumentError)
      end
    end

    describe '::firefox' do
      it 'uses latest Firefox version on Windows 10 by default' do
        options = Options.firefox

        expect(options.browser_name).to eq 'firefox'
        expect(options.browser_version).to eq 'latest'
        expect(options.platform_name).to eq 'Windows 10'
      end

      it 'accepts correct Selenium Options class' do
        browser_opts = Selenium::WebDriver::Firefox::Options.new(args: ['-foo'])
        options = Options.firefox(browser_opts)

        expect(options.selenium_options.dig('moz:firefoxOptions', 'args')).to eq ['-foo']
      end

      it 'does not accept incorrect Selenium Options class' do
        browser_opts = Selenium::WebDriver::Chrome::Options.new

        expect { Options.firefox(browser_opts) }.to raise_exception(ArgumentError)
      end

      it 'accepts correct Selenium Capabilities class' do
        browser_opts = Selenium::WebDriver::Remote::Capabilities.firefox(browser_version: '99')
        options = Options.firefox(browser_opts)

        expect(options.browser_version).to eq '99'
      end

      it 'does not accept incorrect Selenium Capabilities class' do
        browser_opts = Selenium::WebDriver::Remote::Capabilities.chrome(browser_version: '99')

        expect { Options.firefox(browser_opts) }.to raise_exception(ArgumentError)
      end

      it 'accepts base configurations' do
        options = Options.firefox(custom_data: {foo: 'bar'})
        options.tags = %w[foo bar]

        expect(options.custom_data).to eq(foo: 'bar')
        expect(options.tags).to eq %w[foo bar]
      end

      it 'accepts vdc configurations' do
        options = Options.firefox(browser_version: '42.0')
        options.page_load_strategy = 'eager'

        expect(options.browser_version).to eq '42.0'
        expect(options.page_load_strategy).to eq 'eager'
      end

      it 'accepts valid configurations' do
        options = Options.firefox(extended_debugging: true)
        options.selenium_version = '3.14'

        expect(options.selenium_version).to eq '3.14'
        expect(options.extended_debugging).to eq true
      end

      it 'does not accept invalid configurations' do
        expect { Options.firefox(chromedriver_version: 'anything') }.to raise_exception(ArgumentError)
      end
    end

    describe '::ie' do
      it 'uses latest IE version on Windows 10 by default' do
        options = Options.ie

        expect(options.browser_name).to eq 'internet explorer'
        expect(options.browser_version).to eq 'latest'
        expect(options.platform_name).to eq 'Windows 10'
      end

      it 'accepts correct Selenium Options class' do
        browser_opts = Selenium::WebDriver::IE::Options.new(args: ['-foo'])
        options = Options.ie(browser_opts)

        expect(options.selenium_options.dig('se:ieOptions', 'ie.browserCommandLineSwitches')).to eq '-foo'
      end

      it 'does not accept incorrect Selenium Options class' do
        browser_opts = Selenium::WebDriver::Chrome::Options.new

        expect { Options.ie(browser_opts) }.to raise_exception(ArgumentError)
      end

      it 'accepts correct Selenium Capabilities class' do
        browser_opts = Selenium::WebDriver::Remote::Capabilities.ie(browser_version: '99')
        options = Options.ie(browser_opts)

        expect(options.browser_version).to eq '99'
      end

      it 'does not accept incorrect Selenium Capabilities class' do
        browser_opts = Selenium::WebDriver::Remote::Capabilities.chrome(browser_version: '99')

        expect { Options.ie(browser_opts) }.to raise_exception(ArgumentError)
      end

      it 'accepts base configurations' do
        options = Options.ie(custom_data: {foo: 'bar'})
        options.tags = %w[foo bar]

        expect(options.custom_data).to eq(foo: 'bar')
        expect(options.tags).to eq %w[foo bar]
      end

      it 'accepts vdc configurations' do
        options = Options.ie(browser_version: '42.0')
        options.page_load_strategy = 'eager'

        expect(options.browser_version).to eq '42.0'
        expect(options.page_load_strategy).to eq 'eager'
      end

      it 'accepts valid configurations' do
        options = Options.ie(avoid_proxy: true)
        options.selenium_version = '3.14'

        expect(options.selenium_version).to eq '3.14'
        expect(options.avoid_proxy).to eq true
      end

      it 'does not accept invalid configurations' do
        expect { Options.ie(chromedriver_version: 'anything') }.to raise_exception(ArgumentError)
      end
    end

    describe '::safari' do
      it 'uses latest Safari version on latest macOS by default' do
        options = Options.safari

        expect(options.browser_name).to eq 'safari'
        expect(options.browser_version).to eq 'latest'
        expect(options.platform_name).to eq 'macOS 11'
      end

      it 'accepts correct Selenium Options class' do
        browser_opts = Selenium::WebDriver::Safari::Options.new(automatic_inspection: true)
        options = Options.safari(browser_opts)

        expect(options.selenium_options['safari:automaticInspection']).to eq true
      end

      it 'does not accept incorrect Selenium Options class' do
        browser_opts = Selenium::WebDriver::Chrome::Options.new

        expect { Options.safari(browser_opts) }.to raise_exception(ArgumentError)
      end

      it 'accepts correct Selenium Capabilities class' do
        browser_opts = Selenium::WebDriver::Remote::Capabilities.safari(browser_version: '99')
        options = Options.safari(browser_opts)

        expect(options.browser_version).to eq '99'
      end

      it 'does not accept incorrect Selenium Capabilities class' do
        browser_opts = Selenium::WebDriver::Remote::Capabilities.chrome(browser_version: '99')

        expect { Options.safari(browser_opts) }.to raise_exception(ArgumentError)
      end

      it 'accepts base configurations' do
        options = Options.safari(custom_data: {foo: 'bar'})
        options.tags = %w[foo bar]

        expect(options.custom_data).to eq(foo: 'bar')
        expect(options.tags).to eq %w[foo bar]
      end

      it 'accepts vdc configurations' do
        options = Options.safari(browser_version: '42.0')
        options.page_load_strategy = 'eager'

        expect(options.browser_version).to eq '42.0'
        expect(options.page_load_strategy).to eq 'eager'
      end

      it 'accepts valid configurations' do
        options = Options.safari(avoid_proxy: true)
        options.selenium_version = '3.14'

        expect(options.selenium_version).to eq '3.14'
        expect(options.avoid_proxy).to eq true
      end

      it 'does not accept invalid configurations' do
        expect { Options.safari(chromedriver_version: 'anything') }.to raise_exception(ArgumentError)
      end
    end

    describe '#accessors' do
      it 'parses w3c values' do
        proxy = Selenium::WebDriver::Proxy.new(ssl: 'foo')

        options = Options.firefox

        options.browser_version = '7'
        options.platform_name = 'macOS 10.14'
        options.accept_insecure_certs = true
        options.page_load_strategy = 'eager'
        options.proxy = proxy
        options.set_window_rect = true
        options.unhandled_prompt_behavior = 'accept'
        options.strict_file_interactability = true
        options.implicit_wait_timeout = 1
        options.page_load_timeout = 59
        options.script_timeout = 29

        expect(options.browser_name).to eq 'firefox'
        expect(options.browser_version).to eq '7'
        expect(options.platform_name).to eq 'macOS 10.14'
        expect(options.accept_insecure_certs).to eq true
        expect(options.page_load_strategy).to eq 'eager'
        expect(options.proxy).to eq proxy
        expect(options.set_window_rect).to eq true
        expect(options.unhandled_prompt_behavior).to eq 'accept'
        expect(options.strict_file_interactability).to eq true
        expect(options.implicit_wait_timeout).to eq 1
        expect(options.page_load_timeout).to eq 59
        expect(options.script_timeout).to eq 29
      end

      it 'parses Sauce values' do
        custom_data = {foo: 'foo',
                       bar: 'bar'}
        prerun = {executable: 'http://url.to/your/executable.exe',
                  args: ['--silent', '-a', '-q'],
                  background: false,
                  timeout: 120}
        tags = %w[foo bar foobar]

        options = Options.firefox

        options.build = 'Sample Build Name'
        options.command_timeout = 2
        options.custom_data = custom_data
        options.extended_debugging = true
        options.idle_timeout = 3
        options.max_duration = 300
        options.name = 'Sample Test Name'
        options.parent_tunnel = 'Mommy'
        options.prerun = prerun
        options.priority = 0
        options.public = 'team'
        options.record_logs = false
        options.record_screenshots = false
        options.record_video = false
        options.screen_resolution = '10x10'
        options.selenium_version = '3.141.59'
        options.tags = tags
        options.time_zone = 'San Francisco'
        options.tunnel_identifier = 'tunnelname'
        options.video_upload_on_pass = false

        expect(options.build).to eq 'Sample Build Name'
        expect(options.command_timeout).to eq 2
        expect(options.custom_data).to eq custom_data
        expect(options.extended_debugging).to eq true
        expect(options.idle_timeout).to eq 3
        expect(options.max_duration).to eq 300
        expect(options.name).to eq 'Sample Test Name'
        expect(options.parent_tunnel).to eq 'Mommy'
        expect(options.prerun).to eq prerun
        expect(options.priority).to eq 0
        expect(options.public).to eq 'team'
        expect(options.record_logs).to eq false
        expect(options.record_screenshots).to eq false
        expect(options.record_video).to eq false
        expect(options.screen_resolution).to eq '10x10'
        expect(options.selenium_version).to eq '3.141.59'
        expect(options.tags).to eq tags
        expect(options.time_zone).to eq 'San Francisco'
        expect(options.tunnel_identifier).to eq 'tunnelname'
        expect(options.video_upload_on_pass).to eq false
      end
    end

    describe '#capabilities' do
      it 'errors when timeouts are milliseconds' do
        expect { Options.chrome(implicit_wait_timeout: 601).capabilities }.to raise_error(ArgumentError)
        expect { Options.chrome(page_load_timeout: 601).capabilities }.to raise_error(ArgumentError)
        expect { Options.chrome(script_timeout: 601).capabilities }.to raise_error(ArgumentError)
      end

      it 'correctly generates capabilities for w3c values' do
        proxy = Selenium::WebDriver::Proxy.new(ssl: 'foo')

        ClimateControl.modify BUILD_TAG: '', BUILD_NAME: 'TEMP BUILD', BUILD_NUMBER: '11' do
          @options = Options.firefox(platform_name: 'Mac',
                                     accept_insecure_certs: true,
                                     page_load_strategy: 'eager',
                                     proxy: proxy,
                                     set_window_rect: true,
                                     unhandled_prompt_behavior: 'accept',
                                     strict_file_interactability: true,
                                     implicit_wait_timeout: 1,
                                     page_load_timeout: 59,
                                     script_timeout: 29)
        end

        ClimateControl.modify(**SAUCE_ACCESS) do
          @capabilities = @options.capabilities
        end

        expect(@capabilities).to eq('browserName' => 'firefox',
                                    'browserVersion' => 'latest',
                                    'platformName' => 'Mac',
                                    'acceptInsecureCerts' => true,
                                    'pageLoadStrategy' => 'eager',
                                    'proxy' => {'proxyType' => 'manual', 'sslProxy' => 'foo'},
                                    'setWindowRect' => true,
                                    'unhandledPromptBehavior' => 'accept',
                                    'strictFileInteractability' => true,
                                    'timeouts' => {'implicit' => 1000,
                                                   'pageLoad' => 59_000,
                                                   'script' => 29_000},
                                    'sauce:options' => {'build' => 'TEMP BUILD: 11',
                                                        'username' => 'foo',
                                                        'accessKey' => '123'})
      end

      it 'correctly generates capabilities for sauce values' do
        custom_data = {foo: 'foo',
                       bar: 'bar'}
        prerun = {executable: 'http://url.to/your/executable.exe',
                  args: ['--silent', '-a', '-q'],
                  background: false,
                  timeout: 120}
        tags = %w[foo bar foobar]
        sauce_options = {build: 'Sample Build Name',
                         capture_performance: true,
                         chromedriver_version: '71',
                         command_timeout: 2,
                         custom_data: custom_data,
                         extended_debugging: true,
                         idle_timeout: 3,
                         max_duration: 300,
                         name: 'Sample Test Name',
                         parent_tunnel: 'Mommy',
                         prerun: prerun,
                         priority: 0,
                         public: 'team',
                         record_logs: false,
                         record_screenshots: false,
                         record_video: false,
                         screen_resolution: '10x10',
                         tags: tags,
                         time_zone: 'San Francisco',
                         tunnel_identifier: 'tunnelname',
                         video_upload_on_pass: false}

        options = Options.chrome(**sauce_options)

        prerun_caps = {'executable' => 'http://url.to/your/executable.exe',
                       'args' => ['--silent', '-a', '-q'],
                       'background' => false,
                       'timeout' => 120}

        ClimateControl.modify(**SAUCE_ACCESS) do
          @capabilities = options.capabilities
        end

        expect(@capabilities).to eq('browserName' => 'chrome',
                                    'browserVersion' => 'latest',
                                    'platformName' => 'Windows 10',
                                    'sauce:options' => {'build' => 'Sample Build Name',
                                                        'capturePerformance' => true,
                                                        'chromedriverVersion' => '71',
                                                        'commandTimeout' => 2,
                                                        'customData' => {'foo' => 'foo',
                                                                         'bar' => 'bar'},
                                                        'extendedDebugging' => true,
                                                        'idleTimeout' => 3,
                                                        'maxDuration' => 300,
                                                        'name' => 'Sample Test Name',
                                                        'parentTunnel' => 'Mommy',
                                                        'prerun' => prerun_caps,
                                                        'priority' => 0,
                                                        'public' => 'team',
                                                        'recordLogs' => false,
                                                        'recordScreenshots' => false,
                                                        'recordVideo' => false,
                                                        'screenResolution' => '10x10',
                                                        'tags' => %w[foo bar foobar],
                                                        'timeZone' => 'San Francisco',
                                                        'tunnelIdentifier' => 'tunnelname',
                                                        'videoUploadOnPass' => false,
                                                        'username' => 'foo',
                                                        'accessKey' => '123'})
      end

      it 'correctly generates capabilities for selenium object values' do
        caps = Selenium::WebDriver::Remote::Capabilities.chrome(accept_insecure_certs: true)
        browser_opts = Selenium::WebDriver::Chrome::Options.new(args: ['--foo'],
                                                                page_load_strategy: 'eager')

        ClimateControl.modify BUILD_TAG: '', BUILD_NAME: 'TEMP BUILD', BUILD_NUMBER: '11' do
          @options = Options.chrome([caps, browser_opts])
        end
        expect(@options.accept_insecure_certs).to eq true
        expect(@options.page_load_strategy).to eq 'eager'
        expect(@options.selenium_options.dig('goog:chromeOptions', 'args')).to eq ['--foo']

        expected_caps = {'browserName' => 'chrome',
                         'browserVersion' => 'latest',
                         'platformName' => 'Windows 10',
                         'acceptInsecureCerts' => true,
                         'pageLoadStrategy' => 'eager',
                         'sauce:options' => {'build' => 'TEMP BUILD: 11',
                                             'username' => 'foo',
                                             'accessKey' => '123'},
                         'goog:chromeOptions' => {'args' => ['--foo']}}

        ClimateControl.modify(**SAUCE_ACCESS) do
          expect(@options.capabilities).to eq expected_caps
        end
      end

      it 'correctly generates capabilities for selenium object with Sauce values' do
        caps = Selenium::WebDriver::Remote::Capabilities.chrome(accept_insecure_certs: true)
        browser_opts = Selenium::WebDriver::Chrome::Options.new(args: ['--foo'],
                                                                page_load_strategy: 'eager',
                                                                'sauce:options': {username: ENV['SAUCE_USERNAME'],
                                                                                  access_key: ENV['SAUCE_ACCESS_KEY'],
                                                                                  build: 'Build Name',
                                                                                  max_duration: 300})

        @options = Options.chrome([caps, browser_opts])
        expect(@options.accept_insecure_certs).to eq true
        expect(@options.page_load_strategy).to eq 'eager'
        expect(@options.selenium_options.dig('goog:chromeOptions', 'args')).to eq ['--foo']

        expected_caps = {'browserName' => 'chrome',
                         'browserVersion' => 'latest',
                         'platformName' => 'Windows 10',
                         'acceptInsecureCerts' => true,
                         'pageLoadStrategy' => 'eager',
                         'sauce:options' => {'build' => 'Build Name',
                                             'username' => 'foo',
                                             'accessKey' => '123',
                                             'maxDuration' => 300},
                         'goog:chromeOptions' => {'args' => ['--foo']}}

        ClimateControl.modify(**SAUCE_ACCESS) do
          expect(@options.capabilities).to eq expected_caps
        end
      end
    end
  end
end
