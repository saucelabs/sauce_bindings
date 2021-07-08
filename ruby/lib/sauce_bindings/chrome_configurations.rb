# frozen_string_literal: true

module SauceBindings
  class ChromeConfigurations < VDCConfigurations
    def self.valid_options
      super + %i[chromedriver_version extended_debugging capture_performance set_window_rect]
    end
  end
end
