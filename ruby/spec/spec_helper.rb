# frozen_string_literal: true

require 'sauce_bindings'
require 'rspec'
require 'webmock/rspec'
require 'climate_control'

RSpec.configure do |config|
  config.expect_with :rspec do |c|
    c.syntax = :expect
  end

  config.before(:suite) do
    BUILD_ENV = {BUILD_TAG: '', BUILD_NAME: 'TEMP BUILD', BUILD_NUMBER: '11'}.freeze
    SAUCE_ACCESS = {SAUCE_USERNAME: 'foo', SAUCE_ACCESS_KEY: '123'}.freeze
  end

  config.after do |example|
    @session&.stop(!example.exception) if @session&.session_id != '0'
  end
end

DEPRECATION_WARNINGS = %i[options_init timeouts merge].freeze

DEPRECATION_WARNINGS.each do |deprecation|
  RSpec::Matchers.define "have_deprecated_#{deprecation}" do
    match do |actual|
      return actual.call if ENV['IGNORE_DEPRECATIONS']

      warning = /\[DEPRECATION\] \[:#{deprecation}/
      expect {
        actual.call
        @stdout_message = File.read $stdout if $stdout.is_a?(File)
      }.to output(warning).to_stdout_from_any_process
    end

    failure_message do |_actual|
      return 'unexpected exception in the custom matcher block' unless @stdout_message

      deprecations_found = @stdout_message[/WARN Watir \[DEPRECATION\] ([^.*\ ]*)/, 1]
      but_message = if deprecations_found.nil?
                      'no Warnings were found'
                    else
                      "deprecation Warning of #{deprecations_found} was found instead"
                    end
      "expected Warning message of \"#{deprecation}\" being deprecated, but #{but_message}"
    end

    def supports_block_expectations?
      true
    end
  end
end
