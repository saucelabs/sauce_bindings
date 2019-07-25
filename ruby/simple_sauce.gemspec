
lib = File.expand_path('lib', __dir__)
$:.unshift lib unless $:.include?(lib)
require "simple_sauce/version"

Gem::Specification.new do |spec|
  spec.name          = "simple_sauce"
  spec.version       = SimpleSauce::VERSION
  spec.authors       = ["Titus Fortner"]
  spec.email         = ["titusfortner@gmail.com"]

  spec.summary       = 'Simple interface for interacting with Sauce Labs.'
  spec.description   = 'Reduces complexity in user code for running Selenium tests on Sauce Labs'
  spec.homepage      = "https://github.com/saucelabs/simple_sauce"
  spec.license       = "MIT"

  spec.files         = `git ls-files`.split("\n")
  spec.test_files    = `git ls-files -- {test,spec,features}/*`.split("\n")
  spec.require_paths = ['lib']

  spec.add_development_dependency "bundler", "~> 2.0"
  spec.add_development_dependency "rake", "~> 10.0"
  spec.add_development_dependency "rspec", "~> 3.0"

  spec.add_runtime_dependency 'selenium-webdriver', '~> 3.0'
end
