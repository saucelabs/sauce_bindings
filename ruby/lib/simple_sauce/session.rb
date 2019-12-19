# frozen_string_literal: true

require 'sauce_whisk'
require 'selenium-webdriver'

module SimpleSauce
  class Session
    attr_writer :username, :access_key, :url
    attr_reader :driver, :options, :data_center

    def initialize(options = nil, username: nil, access_key: nil, data_center: nil)
      @options = options || Options.new

      self.data_center = data_center || :US_WEST
      @username = username || ENV['SAUCE_USERNAME']
      @access_key = access_key || ENV['SAUCE_ACCESS_KEY']
    end

    def start
      @driver = Selenium::WebDriver.for :remote, url: url, desired_capabilities: options.capabilities
    end

    def stop(result)
      return if @driver.nil?

      SauceWhisk::Jobs.change_status(@driver.session_id, result)
      @driver.quit
    end

    def data_center=(data_center)
      @data_center = data_center
      @dc_url = case data_center
                when :US_WEST
                  'ondemand.saucelabs.com:443/wd/hub'
                when :US_EAST
                  'us-east-1.saucelabs.com:443/wd/hub'
                when :EU_VDC
                  'ondemand.eu-central-1.saucelabs.com:443/wd/hub'
                else
                  msg = "#{data_center} is an invalid data center; specify :US_WEST, :US_EAST or :EU_VDC"
                  raise ::ArgumentError, msg
                end
      SauceWhisk.data_center = @data_center
    end

    def url
      raise ArgumentError, "No user name was set; use `ENV['SAUCE_USERNAME']` or `Session#username=`" unless @username
      unless @access_key
        raise ArgumentError, "No access key was set; use `ENV['SAUCE_ACCESS_KEY']` or `Session#access_key=`"
      end

      @url ||= "https://#{@username}:#{@access_key}@#{@dc_url}"
    end

    def to_selenium
      {url: url, desired_capabilities: Array(@options).map(&:as_json).inject(:merge)}
    end
  end
end
