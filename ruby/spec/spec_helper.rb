# frozen_string_literal: true

require 'sauce_bindings'
require 'rspec'
require 'webmock/rspec'

RSpec.configure do |config|
  config.expect_with :rspec do |c|
    c.syntax = :expect
  end

  config.after do |example|
    @session&.stop(!example.exception)
  end
end
