# frozen_string_literal: true

module SauceBindings
  class FirefoxConfigurations < VDCConfigurations
    def self.valid_options
      super + %i[geckodriver_version extended_debugging selenium_version set_window_rect]
    end
  end
end
