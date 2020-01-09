# frozen_string_literal: true

require 'sauce_whisk'
require 'selenium-webdriver'

module SimpleSauce
  class Session
    DATA_CENTERS = {US_WEST: 'ondemand.us-west-1.saucelabs.com',
                    US_EAST: 'ondemand.us-east-1.saucelabs.com',
                    EU_VDC: 'ondemand.eu-central-1.saucelabs.com'}.freeze

    attr_writer :url
    attr_reader :driver, :options, :data_center

    def initialize(options = nil, data_center: nil)
      @options = options || Options.new

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
      @driver.quit
    end

    def data_center=(data_center)
      unless DATA_CENTERS.key?(data_center)
        msg = "#{data_center} is an invalid data center; specify :US_WEST, :US_EAST or :EU_VDC"
        raise ArgumentError, msg
      end

      SauceWhisk.data_center = data_center
      @data_center = data_center
    end

    def url
      @url ||= "https://#{@username}:#{@access_key}@#{DATA_CENTERS[data_center]}:443/wd/hub"
    end

    def to_selenium
      {url: url, desired_capabilities: options.capabilities}
    end
  end
end
