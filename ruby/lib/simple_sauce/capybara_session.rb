# frozen_string_literal: true

require 'simple_sauce'
require 'capybara'

module SimpleSauce
  class CapybaraSession < Session
    def initialize(*)
      super

      Capybara.register_driver :sauce do |app|
        Capybara::Selenium::Driver.new(app, **{browser: :remote}.merge(to_selenium))
      end
      Capybara.current_driver = :sauce
    end

    def start
      raise ArgumentError, "needs username; use `ENV['SAUCE_USERNAME']` or `Session#username=`" unless @username
      raise ArgumentError, "needs access_key; use `ENV['SAUCE_ACCESS_KEY']` or `Session#access_key=`" unless @access_key

      @capybara_driver = Capybara.current_session.driver
      @driver = @capybara_driver.browser
    end

    def stop(result)
      return if @driver.nil?

      SauceWhisk::Jobs.change_status(@driver.session_id, result)

      Capybara.current_session.quit
    end
  end
end
