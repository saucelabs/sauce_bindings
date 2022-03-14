# frozen_string_literal: true

require 'sauce_whisk'
require 'sa11y/analyze'
require 'selenium-webdriver'

module SauceBindings
  class Session
    DATA_CENTERS = {US_WEST: 'us-west-1',
                    US_EAST: 'us-east-1',
                    EU_CENTRAL: 'eu-central-1',
                    APAC_SOUTHEAST: 'apac-southeast-1'}.freeze

    attr_writer :url
    attr_reader :driver, :options, :data_center
    attr_accessor :http_client, :listener

    def initialize(options = nil, data_center: nil, http_client: nil, listener: nil)
      @options = options || Options.chrome
      @http_client = http_client
      @listener = listener

      self.data_center = data_center || :US_WEST
    end

    def start
      @driver = Selenium::WebDriver.for :remote, to_selenium
    end

    def stop(result)
      return if @driver.nil?

      unless result.is_a?(TrueClass) || result.is_a?(FalseClass)
        raise ArgumentError, "Result must be a boolean value representing whether a test has passed"
      end

      SauceWhisk::Jobs.change_status(@driver.session_id, result)
      # Add output for the Sauce OnDemand Jenkins plugin
      # The first print statement will automatically populate links on Jenkins to Sauce
      # The second print statement will output the job link to logging/console
      puts "SauceOnDemandSessionID=#{@driver.session_id} job-name=#{@options.name}"

      test_link = data_center == :US_WEST ? '' : "#{DATA_CENTERS[data_center]}."

      puts "Test Job Link: https://app.#{test_link}saucelabs.com/tests/#{@driver.session_id}"
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
      sa11y = Sa11y::Analyze.new(driver, js_lib: js_lib, frames: frames, cross_origin: cross_origin)
      sa11y.results
    end

    def url
      @url ||= "https://ondemand.#{DATA_CENTERS[data_center]}.saucelabs.com/wd/hub"
    end

    def to_selenium
      caps = {url: url, desired_capabilities: options.capabilities}
      caps[:listener] = listener if listener
      caps[:http_client] = http_client if http_client
      caps
    end
  end
end
