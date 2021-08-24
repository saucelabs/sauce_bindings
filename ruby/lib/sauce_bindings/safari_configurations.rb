# frozen_string_literal: true

module SauceBindings
  class SafariConfigurations < VDCConfigurations
    def self.valid_options
      super + %i[avoid_proxy selenium_version set_window_rect]
    end
  end
end
