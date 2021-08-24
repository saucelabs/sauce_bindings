# frozen_string_literal: true

require 'spec_helper'

module SauceBindings
  describe Session do
    describe 'gets accessibility results' do
      before { WebMock.allow_net_connect! }

      it 'with nested iframes' do
        @session = Session.new
        driver = @session.start

        driver.get('http://watir.com/examples/nested_iframes.html')

        results = @session.accessibility_results

        problems = results['violations'].map { |v| v['nodes'].size }.inject(0, :+)
        expect(problems).to eq 16
      end

      it 'with nested frames' do
        @session = Session.new
        driver = @session.start

        driver.get('http://watir.com/examples/nested_frames.html')

        results = @session.accessibility_results

        problems = results['violations'].map { |v| v['nodes'].size }.inject(0, :+)
        expect(problems).to eq 14
      end

      it 'with nested iframes and frames turned off' do
        @session = Session.new
        driver = @session.start

        driver.get('http://watir.com/examples/nested_iframes.html')

        results = @session.accessibility_results(frames: false)

        problems = results['violations'].map { |v| v['nodes'].size }.inject(0, :+)
        expect(problems).to eq 7
      end

      it 'with nested frames and frames turned off' do
        @session = Session.new
        driver = @session.start

        driver.get('http://watir.com/examples/nested_frames.html')

        results = @session.accessibility_results(frames: false)

        problems = results['violations'].map { |v| v['nodes'].size }.inject(0, :+)
        expect(problems).to eq 6
      end
    end
  end
end
