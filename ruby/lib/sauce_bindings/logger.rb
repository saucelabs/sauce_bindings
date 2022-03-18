# frozen_string_literal: true

module SauceBindings
  #
  # @example Enable full logging
  #   SauceBindings.logger.level = :debug
  #
  # @example Log to file
  #   SauceBindings.logger.output = 'webdrivers.log'
  #
  # @example Use logger manually
  #   SauceBindings.logger.info('This is info message')
  #   SauceBindings.logger.warn('This is warning message')
  #
  class Logger < Selenium::WebDriver::Logger
    def initialize
      super('SauceBindings')
    end
  end
end
