# frozen_string_literal: true

require 'spec_helper'

module SauceBindings
  describe Options do
    context 'when using deprecated methods' do
      describe '#new' do
        let(:default_options) do
          {'browserName' => 'chrome',
           'browserVersion' => 'latest',
           'platformName' => 'Windows 10',
           'sauce:options' => {'build' => 'TEMP BUILD: 11'}}
        end

        it 'uses latest Chrome version on Windows 10 by default' do
          expect { @options = Options.new }.to have_deprecated_options_init

          expect(@options.browser_name).to eq 'chrome'
          expect(@options.browser_version).to eq 'latest'
          expect(@options.platform_name).to eq 'Windows 10'
        end

        it 'accepts provided values for browser, browser version and platform name' do
          expect {
            @options = Options.new(browser_name: 'firefox',
                                   browser_version: '123',
                                   platform_name: 'Mac')
          }.to have_deprecated_options_init

          expect(@options.browser_name).to eq 'firefox'
          expect(@options.browser_version).to eq '123'
          expect(@options.platform_name).to eq 'Mac'
        end

        it 'accepts other w3c values' do
          proxy = Selenium::WebDriver::Proxy.new(ssl: 'foo')
          timeouts = {implicit: 1,
                      page_load: 59,
                      script: 29}
          expect {
            @options = Options.new(accept_insecure_certs: true,
                                   page_load_strategy: 'eager',
                                   proxy: proxy,
                                   set_window_rect: true,
                                   unhandled_prompt_behavior: 'accept',
                                   strict_file_interactability: true,
                                   timeouts: timeouts)
          }.to have_deprecated_options_init

          expect(@options.accept_insecure_certs).to eq true
          expect(@options.page_load_strategy).to eq 'eager'
          expect(@options.proxy).to eq proxy
          expect(@options.set_window_rect).to eq true
          expect(@options.unhandled_prompt_behavior).to eq 'accept'
          expect(@options.strict_file_interactability).to eq true
          expect(@options.timeouts).to eq timeouts
        end

        it 'accepts Sauce Labs specific settings' do
          custom_data = {foo: 'foo',
                         bar: 'bar'}
          prerun = {executable: 'http://url.to/your/executable.exe',
                    args: ['--silent', '-a', '-q'],
                    background: false,
                    timeout: 120}
          tags = %w[foo bar foobar]
          sauce_options = {
            avoid_proxy: true,
            build: 'Sample Build Name',
            capture_performance: true,
            chromedriver_version: '71',
            command_timeout: 2,
            custom_data: custom_data,
            extended_debugging: true,
            idle_timeout: 3,
            iedriver_version: '3.141.0',
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
            selenium_version: '3.141.59',
            tags: tags,
            time_zone: 'San Francisco',
            tunnel_identifier: 'tunnelname',
            video_upload_on_pass: false
          }

          expect { @options = Options.new(**sauce_options) }.to have_deprecated_options_init

          expect(@options.avoid_proxy).to eq true
          expect(@options.build).to eq 'Sample Build Name'
          expect(@options.capture_performance).to eq true
          expect(@options.chromedriver_version).to eq '71'
          expect(@options.command_timeout).to eq 2
          expect(@options.custom_data).to eq custom_data
          expect(@options.extended_debugging).to eq true
          expect(@options.idle_timeout).to eq 3
          expect(@options.iedriver_version).to eq '3.141.0'
          expect(@options.max_duration).to eq 300
          expect(@options.name).to eq 'Sample Test Name'
          expect(@options.parent_tunnel).to eq 'Mommy'
          expect(@options.prerun).to eq prerun
          expect(@options.priority).to eq 0
          expect(@options.public).to eq 'team'
          expect(@options.record_logs).to eq false
          expect(@options.record_screenshots).to eq false
          expect(@options.record_video).to eq false
          expect(@options.screen_resolution).to eq '10x10'
          expect(@options.selenium_version).to eq '3.141.59'
          expect(@options.tags).to eq tags
          expect(@options.time_zone).to eq 'San Francisco'
          expect(@options.tunnel_identifier).to eq 'tunnelname'
          expect(@options.video_upload_on_pass).to eq false
        end

        it 'accepts Selenium Capabilities and overrides default browser' do
          caps = Selenium::WebDriver::Remote::Capabilities.firefox(accept_insecure_certs: true,
                                                                   page_load_strategy: 'eager')
          expect { @options = Options.new(selenium_options: caps) }.to have_deprecated_options_init

          expect(@options.browser_name).to eq 'firefox'
          expect(@options.accept_insecure_certs).to eq true
          expect(@options.page_load_strategy).to eq 'eager'
        end

        it 'accepts Selenium Options and overrides default browser' do
          browser_opts = Selenium::WebDriver::Firefox::Options.new(args: ['--foo'])
          expect { @options = Options.new(selenium_options: browser_opts) }.to have_deprecated_options_init

          expect(@options.browser_name).to eq 'firefox'
          expect(@options.selenium_options['moz:firefoxOptions']).to eq('args' => ['--foo'])
        end

        it 'accepts Selenium Capabilities and Options class instances' do
          caps = Selenium::WebDriver::Remote::Capabilities.chrome(accept_insecure_certs: true,
                                                                  page_load_strategy: 'eager')
          browser_opts = Selenium::WebDriver::Chrome::Options.new(args: ['--foo'])
          expect { @options = Options.new(selenium_options: [caps, browser_opts]) }.to have_deprecated_options_init

          expect(@options.page_load_strategy).to eq 'eager'
          expect(@options.accept_insecure_certs).to eq true
          expect(@options.selenium_options['goog:chromeOptions']).to eq('args' => ['--foo'])
        end

        it 'accepts W3C, Sauce, Browser Options and Capabilities at the same time' do
          caps = Selenium::WebDriver::Remote::Capabilities.chrome(page_load_strategy: 'eager')
          browser_opts = Selenium::WebDriver::Firefox::Options.new(args: ['--foo'])

          expect {
            @options = Options.new(browser_version: '77',
                                   accept_insecure_certs: true,
                                   command_timeout: 2,
                                   time_zone: 'Alaska',
                                   selenium_options: [caps, browser_opts])
          }.to have_deprecated_options_init

          expect(@options.browser_name).to eq 'firefox'
          expect(@options.browser_version).to eq '77'
          expect(@options.platform_name).to eq 'Windows 10'
          expect(@options.accept_insecure_certs).to eq true
          expect(@options.command_timeout).to eq 2
          expect(@options.time_zone).to eq 'Alaska'
          expect(@options.page_load_strategy).to eq 'eager'

          expect(@options.selenium_options['moz:firefoxOptions']).to eq('args' => ['--foo'])
        end

        it 'creates a default build value' do
          ClimateControl.modify(**BUILD_ENV) do
            expect { @options = Options.new }.to have_deprecated_options_init
          end

          expect(@options.build).to eq 'TEMP BUILD: 11'
        end

        it 'raises ArgumentError if parameter is not recognized as valid' do
          expect {
            expect { @options = Options.new(foo: 'bar') }.to have_deprecated_options_init
          }.to raise_exception(ArgumentError)
        end
      end

      describe '#accessors' do
        it 'parses w3c values' do
          proxy = Selenium::WebDriver::Proxy.new(ssl: 'foo')
          timeouts = {implicit: 1,
                      page_load: 59,
                      script: 29}

          expect { @options = Options.new }.to have_deprecated_options_init

          @options.browser_name = 'firefox'
          @options.browser_version = '7'
          @options.platform_name = 'macOS 10.14'
          @options.accept_insecure_certs = true
          @options.page_load_strategy = 'eager'
          @options.proxy = proxy
          @options.set_window_rect = true
          @options.unhandled_prompt_behavior = 'accept'
          @options.strict_file_interactability = true
          @options.timeouts = timeouts

          expect(@options.browser_name).to eq 'firefox'
          expect(@options.browser_version).to eq '7'
          expect(@options.platform_name).to eq 'macOS 10.14'
          expect(@options.accept_insecure_certs).to eq true
          expect(@options.page_load_strategy).to eq 'eager'
          expect(@options.proxy).to eq proxy
          expect(@options.set_window_rect).to eq true
          expect(@options.unhandled_prompt_behavior).to eq 'accept'
          expect(@options.strict_file_interactability).to eq true
          expect(@options.timeouts).to eq timeouts
        end

        it 'parses Sauce values' do
          custom_data = {foo: 'foo',
                         bar: 'bar'}
          prerun = {executable: 'http://url.to/your/executable.exe',
                    args: ['--silent', '-a', '-q'],
                    background: false,
                    timeout: 120}
          tags = %w[foo bar foobar]

          expect { @options = Options.new }.to have_deprecated_options_init

          @options.avoid_proxy = true
          @options.build = 'Sample Build Name'
          @options.capture_performance = true
          @options.chromedriver_version = '71'
          @options.command_timeout = 2
          @options.custom_data = custom_data
          @options.extended_debugging = true
          @options.idle_timeout = 3
          @options.iedriver_version = '3.141.0'
          @options.max_duration = 300
          @options.name = 'Sample Test Name'
          @options.parent_tunnel = 'Mommy'
          @options.prerun = prerun
          @options.priority = 0
          @options.public = 'team'
          @options.record_logs = false
          @options.record_screenshots = false
          @options.record_video = false
          @options.screen_resolution = '10x10'
          @options.selenium_version = '3.141.59'
          @options.tags = tags
          @options.time_zone = 'San Francisco'
          @options.tunnel_identifier = 'tunnelname'
          @options.video_upload_on_pass = false

          expect(@options.avoid_proxy).to eq true
          expect(@options.build).to eq 'Sample Build Name'
          expect(@options.capture_performance).to eq true
          expect(@options.chromedriver_version).to eq '71'
          expect(@options.command_timeout).to eq 2
          expect(@options.custom_data).to eq custom_data
          expect(@options.extended_debugging).to eq true
          expect(@options.idle_timeout).to eq 3
          expect(@options.iedriver_version).to eq '3.141.0'
          expect(@options.max_duration).to eq 300
          expect(@options.name).to eq 'Sample Test Name'
          expect(@options.parent_tunnel).to eq 'Mommy'
          expect(@options.prerun).to eq prerun
          expect(@options.priority).to eq 0
          expect(@options.public).to eq 'team'
          expect(@options.record_logs).to eq false
          expect(@options.record_screenshots).to eq false
          expect(@options.record_video).to eq false
          expect(@options.screen_resolution).to eq '10x10'
          expect(@options.selenium_version).to eq '3.141.59'
          expect(@options.tags).to eq tags
          expect(@options.time_zone).to eq 'San Francisco'
          expect(@options.tunnel_identifier).to eq 'tunnelname'
          expect(@options.video_upload_on_pass).to eq false
        end
      end

      describe '#merge_capabilities' do
        context 'when unstructured' do
          it 'loads options from configuration' do
            timeouts = {implicit: 1,
                        page_load: 59,
                        script: 29}
            custom_data = {foo: 'foo',
                           bar: 'bar'}
            prerun = {executable: 'http://url.to/your/executable.exe',
                      args: ['--silent', '-a', '-q'],
                      background: false,
                      timeout: 120}
            tags = %w[foo bar foobar]

            expect { @options = Options.new }.to have_deprecated_options_init
            yaml = YAML.load_file('spec/deprecated_options.yml')

            expect { @options.merge_capabilities(yaml['example_values']) }.to have_deprecated_merge

            expect(@options.browser_name).to eq 'firefox'
            expect(@options.browser_version).to eq '123'
            expect(@options.platform_name).to eq 'Mac'
            expect(@options.accept_insecure_certs).to eq true
            expect(@options.page_load_strategy).to eq 'eager'
            expect(@options.set_window_rect).to eq true
            expect(@options.unhandled_prompt_behavior).to eq 'accept'
            expect(@options.strict_file_interactability).to eq true
            expect(@options.timeouts).to eq timeouts
            expect(@options.avoid_proxy).to eq true
            expect(@options.build).to eq 'Sample Build Name'
            expect(@options.capture_performance).to eq true
            expect(@options.chromedriver_version).to eq '71'
            expect(@options.command_timeout).to eq 2
            expect(@options.custom_data).to eq custom_data
            expect(@options.extended_debugging).to eq true
            expect(@options.idle_timeout).to eq 3
            expect(@options.iedriver_version).to eq '3.141.0'
            expect(@options.max_duration).to eq 300
            expect(@options.name).to eq 'Sample Test Name'
            expect(@options.parent_tunnel).to eq 'Mommy'
            expect(@options.prerun).to eq prerun
            expect(@options.priority).to eq 0
            expect(@options.public).to eq 'team'
            expect(@options.record_logs).to eq false
            expect(@options.record_screenshots).to eq false
            expect(@options.record_video).to eq false
            expect(@options.screen_resolution).to eq '10x10'
            expect(@options.selenium_version).to eq '3.141.59'
            expect(@options.tags).to eq tags
            expect(@options.time_zone).to eq 'San Francisco'
            expect(@options.tunnel_identifier).to eq 'tunnelname'
            expect(@options.video_upload_on_pass).to eq false
          end

          it 'raises exception if value not recognized' do
            expect { @options = Options.new }.to have_deprecated_options_init

            yaml = YAML.load_file('spec/deprecated_options.yml')

            msg = 'foo is not a valid parameter for Options class'
            expect {
              expect { @options.merge_capabilities(yaml['invalid_option']) }.to have_deprecated_merge
            }.to raise_exception(ArgumentError, msg)
          end
        end

        context 'when structured' do
          it 'loads options from configuration' do
            custom_data = {foo: 'foo',
                           bar: 'bar'}
            prerun = {executable: 'http://url.to/your/executable.exe',
                      args: ['--silent', '-a', '-q'],
                      background: false,
                      timeout: 120}
            tags = %w[foo bar foobar]

            yaml = YAML.load_file('spec/options.yml')
            example_values = yaml['firefox_mac']
            options = Options.send(example_values.delete('browser_name'))

            expect { options.merge_capabilities(example_values) }.to have_deprecated_merge

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
            expect(options.custom_data).to eq custom_data
            expect(options.extended_debugging).to eq true
            expect(options.idle_timeout).to eq 3
            expect(options.geckodriver_version).to eq '0.23'
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

          it 'raises exception if value not recognized' do
            options = Options.chrome
            yaml = YAML.load_file('spec/options.yml')

            msg = 'foo is not a valid parameter for Options class'
            expect {
              expect {
                options.merge_capabilities(yaml['invalid_option'])
              }.to have_deprecated_merge
            }.to raise_exception(ArgumentError, msg)
          end
        end
      end

      describe '#capabilities' do
        it 'correctly generates capabilities for w3c values' do
          proxy = Selenium::WebDriver::Proxy.new(ssl: 'foo')
          timeouts = {implicit: 1,
                      page_load: 59,
                      script: 29}

          ClimateControl.modify(**BUILD_ENV) do
            expect {
              @options = Options.new(browser_name: 'firefox',
                                     platform_name: 'Mac',
                                     accept_insecure_certs: true,
                                     page_load_strategy: 'eager',
                                     proxy: proxy,
                                     set_window_rect: true,
                                     unhandled_prompt_behavior: 'accept',
                                     strict_file_interactability: true,
                                     timeouts: timeouts)
            }.to have_deprecated_options_init
          end

          ClimateControl.modify(**SAUCE_ACCESS) do
            expect {
              @capabilities = @options.capabilities
            }.to have_deprecated_timeouts
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
                                      'timeouts' => {'implicit' => 1,
                                                     'pageLoad' => 59,
                                                     'script' => 29},
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
          sauce_options = {avoid_proxy: true,
                           build: 'Sample Build Name',
                           capture_performance: true,
                           chromedriver_version: '71',
                           command_timeout: 2,
                           custom_data: custom_data,
                           extended_debugging: true,
                           idle_timeout: 3,
                           iedriver_version: '3.141.0',
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
                           selenium_version: '3.141.59',
                           tags: tags,
                           time_zone: 'San Francisco',
                           tunnel_identifier: 'tunnelname',
                           video_upload_on_pass: false}

          ClimateControl.modify(**BUILD_ENV) do
            expect { @options = Options.new(**sauce_options) }.to have_deprecated_options_init
          end

          prerun_caps = {'executable' => 'http://url.to/your/executable.exe',
                         'args' => ['--silent', '-a', '-q'],
                         'background' => false,
                         'timeout' => 120}

          ClimateControl.modify(**SAUCE_ACCESS) do
            @capabilities = @options.capabilities
          end

          expect(@capabilities).to eq('browserName' => 'chrome',
                                      'browserVersion' => 'latest',
                                      'platformName' => 'Windows 10',
                                      'sauce:options' => {'build' => 'Sample Build Name',
                                                          'avoidProxy' => true,
                                                          'capturePerformance' => true,
                                                          'chromedriverVersion' => '71',
                                                          'commandTimeout' => 2,
                                                          'customData' => {'foo' => 'foo',
                                                                           'bar' => 'bar'},
                                                          'extendedDebugging' => true,
                                                          'idleTimeout' => 3,
                                                          'iedriverVersion' => '3.141.0',
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
                                                          'seleniumVersion' => '3.141.59',
                                                          'tags' => %w[foo bar foobar],
                                                          'timeZone' => 'San Francisco',
                                                          'tunnelIdentifier' => 'tunnelname',
                                                          'username' => 'foo',
                                                          'accessKey' => '123',
                                                          'videoUploadOnPass' => false})
        end

        it 'correctly generates capabilities for selenium object values' do
          caps = Selenium::WebDriver::Remote::Capabilities.chrome(accept_insecure_certs: true,
                                                                  page_load_strategy: 'eager')
          browser_opts = Selenium::WebDriver::Chrome::Options.new(args: ['--foo'])

          ClimateControl.modify(**BUILD_ENV) do
            expect { @options = Options.new(selenium_options: [caps, browser_opts]) }.to have_deprecated_options_init
          end

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
      end
    end
  end
end
