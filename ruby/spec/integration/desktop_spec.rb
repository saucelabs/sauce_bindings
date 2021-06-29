# frozen_string_literal: true

require 'spec_helper'

module SauceBindings
  describe Session do
    describe 'starts with data center' do
      before { WebMock.allow_net_connect! }

      it 'defaults to US West' do
        @session = Session.new
        driver = @session.start

        expect(driver).not_to be_nil
        expect(@session.url).to include('us-west-1')
      end

      it 'executes on US East' do
        options = Options.new(platform_name: 'Linux')
        @session = Session.new(options, data_center: :US_EAST)
        driver = @session.start

        expect(driver).not_to be_nil
        expect(@session.url).to include('us-east-1')
      end

      it 'executes on EU Central' do
        @session = Session.new(data_center: :EU_CENTRAL)
        driver = @session.start

        expect(driver).not_to be_nil
        expect(@session.url).to include('eu-central-1')
      end
    end
  end
end
