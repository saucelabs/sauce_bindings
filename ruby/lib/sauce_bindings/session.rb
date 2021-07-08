# frozen_string_literal: true

require 'sauce_whisk'
require 'selenium-webdriver'

module SauceBindings
  class Session
    DATA_CENTERS = {US_WEST: 'ondemand.us-west-1.saucelabs.com',
                    US_EAST: 'ondemand.us-east-1.saucelabs.com',
                    EU_CENTRAL: 'ondemand.eu-central-1.saucelabs.com',
                    APAC_SOUTHEAST: 'ondemand.apac-southeast-1.saucelabs.com'}.freeze

    attr_writer :url
    attr_reader :driver, :options, :data_center
    attr_accessor :http_client, :listener

    def initialize(options = nil, data_center: nil, http_client: nil, listener: nil)
      @options = options || Options.chrome
      @http_client = http_client
      @listener = listener

      @username = ENV['SAUCE_USERNAME']
      @access_key = ENV['SAUCE_ACCESS_KEY']
      self.data_center = data_center || :US_WEST
    end

    def start
      raise ArgumentError, "needs username; use `ENV['SAUCE_USERNAME']`" unless @username
      raise ArgumentError, "needs access_key; use `ENV['SAUCE_ACCESS_KEY']`" unless @access_key

      @driver = Selenium::WebDriver.for :remote, to_selenium
    end

    def stop(result)
      return if @driver.nil?

      SauceWhisk::Jobs.change_status(@driver.session_id, result)
      # Add output for the Sauce OnDemand Jenkins plugin
      # The first print statement will automatically populate links on Jenkins to Sauce
      # The second print statement will output the job link to logging/console
      puts "SauceOnDemandSessionID=#{@driver.session_id} job-name=#{@options.name}"
      puts "Test Job Link: https://app.saucelabs.com/tests/#{@driver.session_id}"
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

    def url
      @url ||= "https://#{@username}:#{@access_key}@#{DATA_CENTERS[data_center]}:443/wd/hub"
    end

    def to_selenium
      caps = {url: url, desired_capabilities: options.capabilities}
      caps[:listener] = listener if listener
      caps[:http_client] = http_client if http_client
      caps
    end
  end
end
