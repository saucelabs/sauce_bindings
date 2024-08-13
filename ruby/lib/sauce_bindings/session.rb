# frozen_string_literal: true

require 'sauce_whisk'
require 'sa11y/analyze'
require 'selenium-webdriver'

module SauceBindings
  class Session
    DATA_CENTERS = {US_WEST: 'us-west-1',
                    US_EAST: 'us-east-4',
                    EU_CENTRAL: 'eu-central-1'}.freeze

    attr_writer :url
    attr_reader :driver, :options, :data_center, :session_id
    attr_accessor :http_client, :listener

    def initialize(options = nil, data_center: nil, http_client: nil, listener: nil)
      @options = options || Options.chrome
      @http_client = http_client
      @listener = listener

      self.data_center = data_center || :US_WEST
    end

    def start
      @driver = Selenium::WebDriver.for :remote, to_selenium
      @session_id = @driver.session_id
      @driver
    end

    def stop(result)
      return if @driver.nil?

      unless result.is_a?(TrueClass) || result.is_a?(FalseClass)
        raise ArgumentError, 'Result must be a boolean value representing whether a test has passed'
      end

      SauceWhisk::Jobs.change_status(@session_id, result)
      print_results

      @driver.quit
    end

    def data_center=(data_center)
      unless DATA_CENTERS.key?(data_center)
        msg = "#{data_center} is an invalid data center; specify one of: #{DATA_CENTERS.keys}"
        raise ArgumentError, msg
      end

      SauceWhisk.data_center = data_center == :EU_CENTRAL ? :EU_VDC : data_center
      @data_center = data_center
    end

    def accessibility_results(js_lib: nil, frames: true, cross_origin: false)
      validate_session_started('accessibility_results')
      sa11y = Sa11y::Analyze.new(driver, js_lib: js_lib, frames: frames, cross_origin: cross_origin)
      sa11y.results
    end

    def annotate(comment)
      validate_session_started('annotate')
      driver.execute_script("sauce:context=#{comment}")
    end

    def pause
      validate_session_started('pause')
      driver.execute_script('sauce: break')
      puts "\nThis test has been stopped; no more driver commands will be accepted"
      puts "\nYou can take manual control of the test from the Sauce Labs UI here: #{test_link}"
      @driver = nil
    end

    def disable_logging
      validate_session_started('disable_logging')
      driver.execute_script('sauce: disable log')
    end

    def enable_logging
      validate_session_started('enable_logging')
      driver.execute_script('sauce: enable log')
    end

    def stop_network
      validate_session_started('stop_network')
      unless options.mac?
        error = "Can only start or stop the network on a Mac; current platform is: #{options.platform_name}"
        raise InvalidPlatformError, error
      end

      driver.execute_script('sauce: stop network')
    end

    def start_network
      validate_session_started('start_network')
      unless options.mac?
        error = "Can only start or stop the network on a Mac; current platform is: #{options.platform_name}"
        raise InvalidPlatformError, error
      end

      driver.execute_script('sauce: start network')
    end

    def change_name(name)
      validate_session_started('change_name')

      driver.execute_script("sauce:job-name=#{name}")
    end

    def add_tags(tags)
      tags = Array(tags)
      validate_session_started('tags=')
      @driver.execute_script("sauce:job-tags=#{tags.join(',')}")
    end

    def url
      @url ||= "https://ondemand.#{DATA_CENTERS[data_center]}.saucelabs.com/wd/hub"
    end

    def to_selenium
      caps = {url: url, capabilities: Selenium::WebDriver::Remote::Capabilities.new(options.capabilities)}
      caps[:listener] = listener if listener
      caps[:http_client] = http_client if http_client
      caps
    end

    private

    def test_link
      dc = data_center == :US_WEST ? '' : "#{DATA_CENTERS[data_center]}."
      "https://app.#{dc}saucelabs.com/tests/#{@session_id}"
    end

    def validate_session_started(method)
      raise SessionNotStartedError, "session must be started before executing ##{method}" unless @driver
    end

    def print_results
      # Add output for the Sauce OnDemand Jenkins plugin
      # The first print statement will automatically populate links on Jenkins to Sauce
      # The second print statement will output the job link to logging/console
      puts "SauceOnDemandSessionID=#{@session_id} job-name=#{@options.name}"

      puts "Test Job Link: #{test_link}"
    end
  end
end
