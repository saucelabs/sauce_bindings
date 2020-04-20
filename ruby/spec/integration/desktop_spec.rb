# frozen_string_literal: true

require 'spec_helper'

module SauceBindings
  describe Session do
    describe '#new' do
      it 'defaults to US West' do
        session = Session.new
        session.start
        session.stop(true)
      end

      it 'executes on US East' do
        options = Options.new(platform_name: 'Linux')
        session = Session.new(options, data_center: :US_EAST)
        session.start
        session.stop(true)
      end

      it 'executes on EU Central' do
        session = Session.new(data_center: :EU_VDC)
        session.start
        session.stop(true)
      end
    end
  end
end
