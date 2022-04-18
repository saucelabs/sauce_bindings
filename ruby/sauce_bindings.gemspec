# frozen_string_literal: true

lib = File.expand_path('lib', __dir__)
$LOAD_PATH.unshift lib unless $LOAD_PATH.include?(lib)
require 'sauce_bindings/version'

Gem::Specification.new do |spec|
  spec.name          = 'sauce_bindings'
  spec.version       = SauceBindings::VERSION
  spec.authors       = ['Titus Fortner']
  spec.email         = ['titusfortner@gmail.com']
  spec.required_ruby_version = '>= 2.7.0'

  spec.summary       = 'Simple interface for interacting with Sauce Labs.'
  spec.description   = 'Reduces complexity in user code for running Selenium tests on Sauce Labs'
  spec.homepage      = 'https://github.com/saucelabs/sauce_bindings'
  spec.license       = 'MIT'

  spec.files         = `git ls-files`.split("\n")
  spec.test_files    = `git ls-files -- {test,spec,features}/*`.split("\n")
  spec.require_paths = ['lib']

  spec.add_development_dependency 'bundler', '~> 2.0'
  spec.add_development_dependency 'capybara', '~> 3.16'
  spec.add_development_dependency 'climate_control'
  spec.add_development_dependency 'parallel_split_test', '~> 0.10'
  spec.add_development_dependency 'rake', '>= 12.3.3'
  spec.add_development_dependency 'rspec', '~> 3.0'
  spec.add_development_dependency 'rubocop', '~> 1.0'
  spec.add_development_dependency 'rubocop-performance'
  spec.add_development_dependency 'rubocop-rspec', '~> 2.0'
  spec.add_development_dependency 'webmock', '~> 3.5'

  spec.add_runtime_dependency 'sa11y', '~> 0.2.0'
  spec.add_runtime_dependency 'sauce_whisk'
  spec.add_runtime_dependency 'selenium-webdriver', '~> 4.0'
  spec.metadata['rubygems_mfa_required'] = 'true'
end
