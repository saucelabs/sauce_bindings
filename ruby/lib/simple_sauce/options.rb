# frozen_string_literal: true

module SimpleSauce
  class Options
    class << self
      def camel_case(str)
        str.to_s.gsub(/_([a-z])/) { Regexp.last_match(1).upcase }
      end
    end

    W3C = %i[browser_name browser_version platform_name accept_insecure_certs page_load_strategy proxy
             set_window_rect timeouts unhandled_prompt_behavior strict_file_interactability].freeze

    SAUCE = %i[avoid_proxy build chromedriver_version command_timeout custom_data extended_debugging idle_timeout
               iedriver_version max_duration name parent_tunnel prerun priority public record_logs record_screenshots
               record_video screen_resolution selenium_version tags time_zone tunnel_identifier video_upload_on_pass
               capture_performance].freeze

    attr_accessor :browser_name, :browser_version, :platform_name, :accept_insecure_certs, :page_load_strategy, :proxy,
                  :set_window_rect, :timeouts, :unhandled_prompt_behavior, :strict_file_interactability, :avoid_proxy,
                  :build, :chromedriver_version, :command_timeout, :custom_data, :extended_debugging, :idle_timeout,
                  :iedriver_version, :max_duration, :name, :parent_tunnel, :prerun, :priority, :public, :record_logs,
                  :record_screenshots, :record_video, :screen_resolution, :selenium_version, :tags, :time_zone,
                  :tunnel_identifier, :video_upload_on_pass, :capture_performance
    attr_reader :selenium_options

    def initialize(**opts)
      parse_selenium_options(opts.delete(:selenium_options))
      create_variables(SAUCE + W3C, opts)
      @build ||= build_name

      @browser_name ||= selenium_options['browserName'] || 'chrome'
      @platform_name ||= 'Windows 10'
      @browser_version ||= 'latest'
    end

    def capabilities
      caps = selenium_options.dup
      W3C.each do |key|
        value = send(key)
        key = self.class.camel_case(key)

        if key == 'proxy' && value
          value = value.as_json
        elsif key == 'timeouts' && value
          value = value.inject({}) do |updated, (old_key, val)|
            updated[self.class.camel_case(old_key)] = val
            updated
          end
        end

        caps[key] = value if value
      end
      caps['sauce:options'] = {}
      SAUCE.each do |key|
        value = send(key)
        key = self.class.camel_case(key)

        if key == 'prerun' && value.is_a?(Hash)
          value = value.inject({}) do |updated, (old_key, val)|
            updated[self.class.camel_case(old_key)] = val
            updated
          end
        elsif key == 'customData' && value.is_a?(Hash)
          value = value.inject({}) do |updated, (old_key, val)|
            updated[self.class.camel_case(old_key)] = val
            updated
          end
        end

        caps['sauce:options'][key] = value unless value.nil?
      end
      caps
    end
    alias as_json capabilities

    def add_capabilities(opts)
      opts.each do |key, value|
        raise ArgumentError, "#{key} is not a valid parameter for Options class" unless respond_to?("#{key}=")

        if value.is_a?(Hash)
          value = value.inject({}) do |updated, (key, val)|
            updated[key.to_sym] = val
            updated
          end
        end

        send("#{key}=", value)
      end
    end

    private

    def parse_selenium_options(selenium_opts)
      opts = Array(selenium_opts)

      opts.each do |opt|
        case opt
        when Selenium::WebDriver::Firefox::Options
          @browser_name = 'firefox'
        when Selenium::WebDriver::Chrome::Options
          @browser_name = 'chrome'
        when Selenium::WebDriver::Edge::Options
          @browser_name = 'MicrosoftEdge'
        when Selenium::WebDriver::IE::Options
          @browser_name = 'internet explorer'
        when Selenium::WebDriver::Safari::Options
          @browser_name = 'safari'
        when Selenium::WebDriver::Remote::Capabilities
          W3C.each { |capability|
            send("#{capability}=", opt[capability]) if opt[capability]
          }
        end
      end

      @selenium_options = opts.map(&:as_json).inject(:merge) || {}
    end

    def create_variables(key, opts)
      key.each do |option|
        self.class.__send__(:attr_accessor, option)
        next unless opts.key?(option)

        instance_variable_set("@#{option}", opts.delete(option))
      end
      return if opts.empty?

      raise ArgumentError, "#{opts.inspect} are not valid parameters for Options class"
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
      # Gitlab
      elsif ENV['CI']
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
