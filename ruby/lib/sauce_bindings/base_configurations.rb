# frozen_string_literal: true

module SauceBindings
  class BaseConfigurations
    def self.valid_options
      %i[platform_name name build tags custom_data public tunnel_identifier parent_tunnel]
    end
  end
end
