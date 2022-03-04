# frozen_string_literal: true

require 'sauce_bindings'
require 'capybara'

module SauceBindings
  class CapybaraSession < Session
    def initialize(*)
      super

      Capybara.register_driver :sauce do |app|
        Capybara::Selenium::Driver.new(app, **{browser: :remote}.merge(to_selenium))
      end
      Capybara.current_driver = :sauce
    end

    def start
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
