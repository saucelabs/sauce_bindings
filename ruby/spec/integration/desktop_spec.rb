# frozen_string_literal: true

require 'spec_helper'

module SauceBindings
  describe Session do
    before { WebMock.allow_net_connect! }

    describe '#data_center=' do
      it 'defaults to US West' do
        @session = Session.new
        driver = @session.start

        expect(driver).not_to be_nil
        expect(@session.url).to include('us-west-1')
      end

      it 'executes on US East' do
        options = Options.chrome(platform_name: 'Linux')
        @session = Session.new(options)
        @session.data_center = :US_EAST
        driver = @session.start

        expect(driver).not_to be_nil
        expect(@session.url).to include('us-east-4')
      end

      it 'executes on EU Central' do
        @session = Session.new
        @session.data_center = :EU_CENTRAL
        driver = @session.start

        expect(driver).not_to be_nil
        expect(@session.url).to include('eu-central-1')
      end

    end

    describe '#stop_network' do
      it 'stops and starts the network' do
        @session = Session.new(Options.safari)
        driver = @session.start

        @session.stop_network
        driver.get('https://www.saucedemo.com')

        expect(driver.title).to eq 'Failed to open page'

        @session.start_network
        driver.get('https://www.saucedemo.com')

        expect(driver.title).to eq 'Swag Labs'
      end
    end
  end
end
