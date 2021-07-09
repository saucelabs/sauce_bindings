# frozen_string_literal: true

module SauceBindings
  class EdgeConfigurations < VDCConfigurations
    def self.valid_options
      super + %i[edgedriver_version selenium_version set_window_rect]
    end
  end
end
