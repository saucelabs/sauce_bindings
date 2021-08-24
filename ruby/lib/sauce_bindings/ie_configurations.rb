# frozen_string_literal: true

module SauceBindings
  class IEConfigurations < VDCConfigurations
    def self.valid_options
      super + %i[iedriver_version avoid_proxy selenium_version set_window_rect]
    end
  end
end
