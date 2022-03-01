# frozen_string_literal: true

require 'selenium-webdriver'

module SauceBindings
  class Options
    class << self
      def camel_case(str)
        str.to_s.gsub(/_([a-z])/) { Regexp.last_match(1).upcase }
      end

      def chrome(**opts)
        opts[:browser_name] = 'chrome'
        opts[:valid_options] = ChromeConfigurations.valid_options
        Options.new(**opts)
      end

      def edge(**opts)
        if Selenium::WebDriver::VERSION[0] == '3'
          raise ArgumentError, 'Selenium 3 is not compatible with the Chromium based Microsoft Edge.'
        end

        opts[:browser_name] = 'MicrosoftEdge'
        opts[:valid_options] = EdgeConfigurations.valid_options
        Options.new(**opts)
      end

      def firefox(**opts)
        opts[:browser_name] = 'firefox'
        opts[:valid_options] = FirefoxConfigurations.valid_options
        Options.new(**opts)
      end

      def ie(**opts)
        opts[:browser_name] = 'internet explorer'
        opts[:valid_options] = IEConfigurations.valid_options
        Options.new(**opts)
      end

      def safari(**opts)
        opts[:browser_name] = 'safari'
        opts[:platform_name] ||= 'macOS 11'
        opts[:valid_options] = SafariConfigurations.valid_options
        Options.new(**opts)
      end
    end

    BROWSER_NAMES = {Selenium::WebDriver::Chrome::Options => 'chrome',
                     Selenium::WebDriver::Edge::Options => 'MicrosoftEdge',
                     Selenium::WebDriver::Firefox::Options => 'firefox',
                     Selenium::WebDriver::IE::Options => 'internet explorer',
                     Selenium::WebDriver::Safari::Options => 'safari'}.freeze

    W3C = %i[browser_name browser_version platform_name accept_insecure_certs page_load_strategy proxy
             set_window_rect timeouts unhandled_prompt_behavior strict_file_interactability].freeze

    SAUCE = %i[avoid_proxy build chromedriver_version command_timeout custom_data extended_debugging idle_timeout
               iedriver_version max_duration name parent_tunnel prerun priority public record_logs record_screenshots
               record_video screen_resolution selenium_version tags time_zone tunnel_identifier video_upload_on_pass
               capture_performance].freeze

    attr_reader :selenium_options, :timeouts

    def initialize(**opts)
      se_options = Array(opts.delete(:selenium_options))
      valid_options = opts.delete(:valid_options)
      if valid_options.nil?
        valid_options = SAUCE + W3C
        warn 'Using `Options.new(opts)` directly is deprecated, use static methods for desired browser instead; ' \
        'e.g., `Options.chrome(opts)` or `Options.safari(opts)'
      end

      create_variables(valid_options, opts)
      @selenium_options = se_options.map(&:as_json).inject(:merge) || {}
      parse_selenium_options(se_options)
      @build ||= build_name

      @browser_name ||= selenium_options['browserName'] || 'chrome'
      @platform_name ||= 'Windows 10'
      @browser_version ||= 'latest'
    end

    def capabilities
      caps = selenium_options.dup
      W3C.each do |key|
        value = parse_w3c_key(key)
        caps[self.class.camel_case(key)] = value unless value.nil?
      end
      caps['sauce:options'] = {}
      SAUCE.each do |key|
        value = parse_sauce_key(key)
        caps['sauce:options'][self.class.camel_case(key)] = value unless value.nil?
      end
      caps
    end
    alias as_json capabilities

    def merge_capabilities(opts)
      opts.each do |key, value|
        raise ArgumentError, "#{key} is not a valid parameter for Options class" unless respond_to?("#{key}=")

        value = value.transform_keys(&:to_sym) if value.is_a?(Hash)

        send("#{key}=", value)
      end
    end

    private

    def key_value(key)
      send(key)
    rescue NoMethodError
      # Ignored
    end

    def parse_w3c_key(key)
      value = send(key)

      if key == :proxy && value
        value.as_json
      elsif key == :timeouts
        if value
          warn ':timeouts is deprecated, use :implicit_wait_timeout, :page_load_timeout or :script_timeout directly; ' \
          '(ensure the values are set as seconds not milliseconds)'
          value.transform_keys do |old_key|
            self.class.camel_case(old_key)
          end
        else
          manage_timeouts
        end
      else
        value
      end
    end

    def manage_timeouts
      timeouts = {}
      timeouts['implicit'] = ms_multiplier(@implicit_wait_timeout) if @implicit_wait_timeout
      timeouts['pageLoad'] = ms_multiplier(@page_load_timeout) if @page_load_timeout
      timeouts['script'] = ms_multiplier(@script_timeout) if @script_timeout
      timeouts unless timeouts.empty?
    end

    def ms_multiplier(int)
      raise(ArgumentError, 'Timeouts need to be entered as seconds not milliseconds') if int > 600

      int * 1000
    end

    def parse_sauce_key(key)
      value = key_value(key)

      case key
      when :prerun
        camelize_keys(value)
      when :custom_data
        camelize_keys(value)
      when :disable_record_video
        !send(:record_video)
      when :disable_video_upload_on_pass
        !send(:video_upload_on_pass)
      when :disable_record_screenshots
        !send(:record_screenshots)
      when :disable_record_logs
        !send(:record_logs)
      else
        value
      end
    end

    def camelize_keys(hash)
      hash&.transform_keys do |old_key|
        self.class.camel_case(old_key)
      end
    end

    def parse_selenium_options(selenium_opts)
      selenium_opts.each do |opt|
        W3C.each do |capability|
          value = option_value(opt, capability)
          next if capability == :timeouts && value&.empty?

          if capability == :browser_name
            @browser_name ||= BROWSER_NAMES[opt.class]
            validate_browser_name(value || BROWSER_NAMES[opt.class])
          elsif value
            send("#{capability}=", value)
          end
        end
      end
    end

    def option_value(opt, capability)
      if opt.respond_to?(capability)
        begin
          opt.send(capability)
        rescue KeyError
          # Ignored
        end
      elsif opt.respond_to?(:[])
        opt[capability]
      end
    end

    def validate_browser_name(browser)
      return if @browser_name.nil? || browser == @browser_name

      raise ArgumentError, "Selenium class identifies capabilities for #{browser}, which does not match the " \
"provided browser name: #{@browser_name}"
    end

    def create_variables(key, opts)
      key.each do |option|
        singleton_class.class_eval { attr_accessor option }
        next unless opts.key?(option)

        instance_variable_set("@#{option}", opts.delete(option))
      end

      if opts.key?(:browser_name)
        singleton_class.class_eval { attr_reader :browser_name }
        @browser_name = opts.delete(:browser_name)
      end

      return if opts.empty?

      raise ArgumentError, "#{opts.inspect} are not valid parameters for Options class with #{@browser_name}"
    end

    def build_name
      # Jenkins
      if ENV['BUILD_TAG']
        "#{ENV['BUILD_NAME']}: #{ENV['BUILD_NUMBER']}"
      # Bamboo
      elsif ENV['bamboo_agentId']
        "#{ENV['bamboo_shortJobName']}: #{ENV['bamboo_buildNumber']}"
      # Travis
      elsif ENV['TRAVIS_JOB_ID']
        "#{ENV['TRAVIS_JOB_NAME']}: #{ENV['TRAVIS_JOB_NUMBER']}"
      # CircleCI
      elsif ENV['CIRCLE_JOB']
        "#{ENV['CIRCLE_JOB']}: #{ENV['CIRCLE_BUILD_NUM']}"
      # GitHub Actions
      elsif ENV['GITHUB_SHA']
        "#{ENV['GITHUB_WORKFLOW']}: #{ENV['GITHUB_SHA']}"
      # Gitlab
      elsif ENV['CI_JOB_NAME']
        "#{ENV['CI_JOB_NAME']}: #{ENV['CI_JOB_ID']}"
      # Team City
      elsif ENV['TEAMCITY_PROJECT_NAME']
        "#{ENV['TEAMCITY_PROJECT_NAME']}: #{ENV['BUILD_NUMBER']}"
      # Default
      else
        "Build Time - #{Time.now.to_i}"
      end
    end
  end
end
