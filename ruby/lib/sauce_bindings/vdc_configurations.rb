# frozen_string_literal: true

module SauceBindings
  class VDCConfigurations < BaseConfigurations
    def self.valid_options
      super + %i[browser_version page_load_strategy accept_insecure_certs proxy
                 strict_file_interactability unhandled_prompt_behavior implicit_wait_timeout
                 page_load_timeout script_timeout record_video video_upload_on_pass
                 record_screenshots record_logs max_duration command_timeout
                 idle_timeout prerun priority screen_resolution time_zone]
    end
  end
end
