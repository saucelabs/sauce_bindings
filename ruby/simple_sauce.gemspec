# frozen_string_literal: true

lib = File.expand_path('lib', __dir__)
$LOAD_PATH.unshift lib unless $LOAD_PATH.include?(lib)
require 'simple_sauce/version'

Gem::Specification.new do |spec|
  spec.name          = 'simple_sauce'
  spec.version       = SimpleSauce::VERSION
  spec.authors       = ['Titus Fortner']
  spec.email         = ['titusfortner@gmail.com']

  spec.summary       = 'Simple interface for interacting with Sauce Labs.'
  spec.description   = 'Reduces complexity in user code for running Selenium tests on Sauce Labs'
  spec.homepage      = 'https://github.com/saucelabs/simple_sauce'
  spec.license       = 'MIT'

  spec.files         = `git ls-files`.split("\n")
  spec.test_files    = `git ls-files -- {test,spec,features}/*`.split("\n")
  spec.require_paths = ['lib']

  spec.add_development_dependency 'bundler', '~> 2.0'
  spec.add_development_dependency 'capybara', '~> 3.16'
  spec.add_development_dependency 'rake', '~> 10.0'
  spec.add_development_dependency 'rspec', '~> 3.0'
  spec.add_development_dependency 'rubocop', '~>0.66'
  spec.add_development_dependency 'rubocop-performance'
  spec.add_development_dependency 'rubocop-rspec', '~>1.32'
  spec.add_development_dependency 'webmock', '~> 3.5'

  spec.add_runtime_dependency 'sauce_whisk'
  spec.add_runtime_dependency 'selenium-webdriver', '~> 3.142.0', '>= 3.142.7'
end
