# frozen_string_literal: true

module SimpleSauce
  class Options
    class << self
      def camel_case(str)
        str.to_s.gsub(/_([a-z])/) { Regexp.last_match(1).upcase }
      end
    end

    W3C = %i[browser_name platform_name browser_version].freeze

    SAUCE = %i[access_key appium_version avoid_proxy build capture_html chromedriver_version command_timeout
               crmuxdriver_version custom_data disable_popup_handler extended_debugging firefox_adapter_version
               firefox_profile_url idle_timeout iedriver_version max_duration name parent_tunnel passed prerun
               prevent_requeue priority proxy_host public record_logs record_screenshots record_video
               restricted_public_info screen_resolution selenium_version source tags time_zone tunnel_identifier
               username video_upload_on_pass capture_performance].freeze

    def initialize(**opts)
      create_variables(SAUCE + W3C, opts)
      @build ||= build_name

      @browser_name ||= 'chrome'
      @platform_name ||= 'Windows 10'
      @browser_version ||= 'latest'
    end

    def capabilities
      caps = W3C.each_with_object({}) do |key, hash|
        value = send(key)
        hash[self.class.camel_case(key)] = value if value
      end
      caps['sauce:options'] = {}
      SAUCE.each do |key|
        value = send(key)
        caps['sauce:options'][self.class.camel_case(key)] = value unless value.nil?
      end
      caps
    end

    alias as_json capabilities

    private

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
        "#{ENV['TRAVIS_REPO_SLUG'][%r{[^/]+$}]}: #{ENV['TRAVIS_JOB_NUMBER']}"
      # CircleCI
      elsif ENV['CIRCLE_JOB']
        "#{ENV['CIRCLE_JOB']}: #{ENV['CIRCLE_BUILD_NUM']}"
      # Gitlab
      elsif ENV['CI']
        "#{ENV['CI_JOB_NAME']}: #{ENV['CI_JOB_ID']}"
      # Default
      else
        "Build Time - #{Time.now.to_i}"
      end
    end
  end
end
