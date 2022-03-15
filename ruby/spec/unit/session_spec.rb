# frozen_string_literal: true

require 'spec_helper'

module SauceBindings
  describe Session do
    let(:valid_response) do
      {status: 200,
       body: {value: {sessionId: '0', capabilities: Selenium::WebDriver::Remote::Capabilities.chrome}}.to_json,
       headers: {"content_type": 'application/json'}}
    end
    let(:default_capabilities) do
      {browserName: 'chrome',
       browserVersion: 'latest',
       platformName: 'Windows 10',
       'sauce:options': {username: 'foo',
                         accessKey: '123',
                         build: 'TEMP BUILD: 11'}}
    end

    def expect_request(caps = default_capabilities)
      se3 = {desiredCapabilities: caps,
             capabilities: {firstMatch: [caps]}}.to_json
      se4 = {capabilities: {alwaysMatch: caps}}.to_json

      body = Selenium::WebDriver::VERSION[0] == '3' ? se3 : se4

      endpoint ||= 'https://ondemand.us-west-1.saucelabs.com/wd/hub/session'
      stub_request(:post, endpoint).with(body: body).to_return(valid_response)
    end

    before do
      allow_any_instance_of(Selenium::WebDriver::Remote::Http::Default).to receive(:use_proxy?).and_return(false)
      allow(ENV).to receive(:[]).with('BUILD_TAG').and_return('')
      allow(ENV).to receive(:[]).with('BUILD_NAME').and_return('TEMP BUILD')
      allow(ENV).to receive(:[]).with('BUILD_NUMBER').and_return('11')
      allow(ENV).to receive(:[]).with('SAUCE_USERNAME').and_return('foo')
      allow(ENV).to receive(:[]).with('SAUCE_ACCESS_KEY').and_return('123')
    end

    describe '#new' do
      it 'creates default Options instance if none is provided' do
        session = Session.new

        expected_results = {url: 'https://ondemand.us-west-1.saucelabs.com/wd/hub',
                            desired_capabilities: {'browserName' => 'chrome',
                                                   'browserVersion' => 'latest',
                                                   'platformName' => 'Windows 10',
                                                   'sauce:options' => {'build' => 'TEMP BUILD: 11',
                                                                       'username' => 'foo',
                                                                       'accessKey' => '123'}}}
        expect(session.to_selenium).to eq expected_results
      end

      it 'uses provided Options class' do
        sauce_opts = Options.chrome(browser_version: '123',
                                    platform_name: 'Mac',
                                    idle_timeout: 4)
        session = Session.new(sauce_opts)

        expected_results = {url: 'https://ondemand.us-west-1.saucelabs.com/wd/hub',
                            desired_capabilities: {'browserName' => 'chrome',
                                                   'browserVersion' => '123',
                                                   'platformName' => 'Mac',
                                                   'sauce:options' => {'idleTimeout' => 4,
                                                                       'build' => 'TEMP BUILD: 11',
                                                                       'username' => 'foo',
                                                                       'accessKey' => '123'}}}
        expect(session.to_selenium).to eq expected_results
      end

      it 'defaults to US West data Center' do
        session = Session.new
        expect(session.data_center).to eq :US_WEST
      end

      it 'uses provided Data Center' do
        session = Session.new(data_center: :EU_CENTRAL)

        expected_results = {url: 'https://ondemand.eu-central-1.saucelabs.com/wd/hub',
                            desired_capabilities: {'browserName' => 'chrome',
                                                   'browserVersion' => 'latest',
                                                   'platformName' => 'Windows 10',
                                                   'sauce:options' => {'build' => 'TEMP BUILD: 11',
                                                                       'username' => 'foo',
                                                                       'accessKey' => '123'}}}
        expect(session.to_selenium).to eq expected_results
      end

      it 'uses provided Event Listener' do
        listener = instance_double(Selenium::WebDriver::Support::AbstractEventListener)
        session = Session.new(listener: listener)

        expect(session.to_selenium[:listener]).to eq listener
      end

      it 'uses provided HTTP Client' do
        http_client = instance_double(Selenium::WebDriver::Remote::Http::Default)
        session = Session.new(http_client: http_client)

        expect(session.to_selenium[:http_client]).to eq http_client
      end

      it 'raises exception if data center is invalid' do
        expect { Session.new(data_center: :FOO) }.to raise_exception(ArgumentError)
      end
    end

    describe '#start' do
      it 'starts the session and returns Selenium Driver instance' do
        expect_request

        driver = Session.new.start
        expect(driver).to be_a Selenium::WebDriver::Driver
      end

      it 'raises exception if no username set' do
        allow(ENV).to receive(:[]).with('SAUCE_USERNAME')

        expect { Session.new.start }.to raise_exception(ArgumentError)
      end

      it 'raises exception if no access key set' do
        allow(ENV).to receive(:[]).with('SAUCE_ACCESS_KEY')

        expect { Session.new.start }.to raise_exception(ArgumentError)
      end
    end

    describe '#stop' do
      it 'quits the driver' do
        expect_request
        session = Session.new
        driver = session.start

        allow(session).to receive :print_results
        allow(driver).to receive :quit
        allow(SauceWhisk::Jobs).to receive(:change_status).with('0', false)
        session.stop(false)

        expect(driver).to have_received(:quit)
        expect(SauceWhisk::Jobs).to have_received(:change_status).with('0', false)
      end

      it 'raises error when argument value is not boolean' do
        expect_request

        session = Session.new
        driver = session.start
        allow(driver).to receive :quit

        expect { session.stop('String') }.to raise_error(ArgumentError)
      end
    end

    describe '#data_center=' do
      it 'overrides default value for data center' do
        session = Session.new
        session.data_center = :US_EAST

        expect(session.url).to eq('https://ondemand.us-east-1.saucelabs.com/wd/hub')
      end

      it 'raises exception if data center is invalid' do
        session = Session.new

        expect { session.data_center = :FOO }.to raise_exception(ArgumentError)
      end
    end

    describe '#http_client=' do
      it 'uses provided HTTP Client' do
        http_client = instance_double(Selenium::WebDriver::Remote::Http::Default)
        session = Session.new
        session.http_client = http_client

        expect(session.to_selenium[:http_client]).to eq http_client
      end
    end

    describe '#listener=' do
      it 'uses provided Event Listener' do
        listener = instance_double(Selenium::WebDriver::Support::AbstractEventListener)
        session = Session.new
        session.listener = listener

        expect(session.to_selenium[:listener]).to eq listener
      end
    end

    describe '#url=' do
      it 'allows user to override default URL' do
        session = Session.new
        session.url = 'https://bar:321@mycustomurl/foo/wd/hub:8080'

        expected_results = {url: 'https://bar:321@mycustomurl/foo/wd/hub:8080',
                            desired_capabilities: {'browserName' => 'chrome',
                                                   'browserVersion' => 'latest',
                                                   'platformName' => 'Windows 10',
                                                   'sauce:options' => {'build' => 'TEMP BUILD: 11',
                                                                       'username' => ENV['SAUCE_USERNAME'],
                                                                       'accessKey' => ENV['SAUCE_ACCESS_KEY']}}}
        expect(session.to_selenium).to eq expected_results
      end
    end

    describe '#annotate' do
      it 'raises exception if session not started' do
        session = Session.new
        expect { session.annotate('Comment') }.to raise_error(SessionNotStartedError)
      end

      it 'accepts annotation' do
        expect_request
        session = Session.new
        driver = session.start
        allow(driver).to receive :quit
        allow(driver).to receive(:execute_script)

        session.annotate('comment')

        expect(driver).to have_received(:execute_script).with('sauce:context=comment')
      end
    end

    describe '#pause' do
      it 'raises exception if session not started' do
        session = Session.new
        expect { session.pause }.to raise_error(SessionNotStartedError)
      end

      it 'pauses test' do
        expect_request
        session = Session.new
        driver = session.start
        allow(driver).to receive :quit
        allow(driver).to receive(:execute_script)

        message = "\nThis test has been stopped; no more driver commands will be accepted\n\n" \
        "You can take manual control of the test from the Sauce Labs UI here: https://app.saucelabs.com/tests/0\n"
        expect { session.pause }.to output(message).to_stdout

        expect(driver).to have_received(:execute_script).with('sauce: break')
      end
    end

    describe '#enable_logs' do
      it 'raises exception if session not started' do
        session = Session.new
        expect { session.enable_logging }.to raise_error(SessionNotStartedError)
      end

      it 'enables logs' do
        expect_request
        session = Session.new
        driver = session.start
        allow(driver).to receive :quit
        allow(driver).to receive(:execute_script)

        session.enable_logging

        expect(driver).to have_received(:execute_script).with('sauce: enable log')
      end
    end

    describe '#disable_logs' do
      it 'raises exception if session not started' do
        session = Session.new
        expect { session.disable_logging }.to raise_error(SessionNotStartedError)
      end

      it 'disables logs' do
        expect_request
        session = Session.new
        driver = session.start
        allow(driver).to receive :quit
        allow(driver).to receive(:execute_script)

        session.disable_logging

        expect(driver).to have_received(:execute_script).with('sauce: disable log')
      end
    end

    describe '#stop_network' do
      it 'raises exception if session not started' do
        session = Session.new
        expect { session.stop_network }.to raise_error(SessionNotStartedError)
      end

      it 'raises exception if session not on a Mac' do
        expect_request
        session = Session.new
        driver = session.start
        allow(driver).to receive :quit
        allow(driver).to receive(:execute_script)

        error = /Can only start or stop the network on a Mac/
        expect { session.stop_network }.to raise_error(InvalidPlatformError, error)
      end

      it 'stops network' do
        caps = default_capabilities.merge(platformName: 'mac')
        expect_request(caps)
        session = Session.new(Options.chrome(platform_name: 'mac'))
        driver = session.start
        allow(driver).to receive :quit
        allow(driver).to receive(:execute_script)

        session.stop_network

        expect(driver).to have_received(:execute_script).with('sauce: stop network')
      end
    end

    describe '#start_network' do
      it 'raises exception if session not started' do
        session = Session.new
        expect { session.start_network }.to raise_error(SessionNotStartedError)
      end

      it 'raises exception if session not on a Mac' do
        expect_request
        session = Session.new
        driver = session.start
        allow(driver).to receive :quit
        allow(driver).to receive(:execute_script)

        error = /Can only start or stop the network on a Mac/
        expect { session.start_network }.to raise_error(InvalidPlatformError, error)
      end

      it 'starts network' do
        caps = default_capabilities.merge(platformName: 'mac')
        expect_request(caps)
        session = Session.new(Options.chrome(platform_name: 'mac'))
        driver = session.start
        allow(driver).to receive :quit
        allow(driver).to receive(:execute_script)

        session.start_network

        expect(driver).to have_received(:execute_script).with('sauce: start network')
      end
    end

    describe '#change_name' do
      it 'raises exception if session not started' do
        session = Session.new
        expect { session.change_name('New Name') }.to raise_error(SessionNotStartedError)
      end

      it 'changes the test name' do
        expect_request
        session = Session.new
        driver = session.start
        allow(driver).to receive :quit
        allow(driver).to receive(:execute_script)

        session.change_name('New Name')

        expect(driver).to have_received(:execute_script).with('sauce:job-name=New Name')
      end
    end

    describe '#tags=' do
      it 'raises exception if session not started' do
        session = Session.new
        expect { session.add_tags([]) }.to raise_error(SessionNotStartedError)
      end

      it 'accepts single tag' do
        expect_request
        session = Session.new
        driver = session.start
        allow(driver).to receive :quit
        allow(driver).to receive(:execute_script)

        session.add_tags 'foo'

        expect(driver).to have_received(:execute_script).with('sauce:job-tags=foo')
      end

      it 'accepts multiple tags as String' do
        expect_request
        session = Session.new
        driver = session.start
        allow(driver).to receive :quit
        allow(driver).to receive(:execute_script)

        session.add_tags 'foo,bar'

        expect(driver).to have_received(:execute_script).with('sauce:job-tags=foo,bar')
      end

      it 'accepts multiple tags as Array' do
        expect_request
        session = Session.new
        driver = session.start
        allow(driver).to receive :quit
        allow(driver).to receive(:execute_script)

        session.add_tags %w[foo bar]

        expect(driver).to have_received(:execute_script).with('sauce:job-tags=foo,bar')
      end
    end
  end
end
