# frozen_string_literal: true

require 'spec_helper'

module SimpleSauce
  describe Session do
    describe '#new' do
      it 'defaults to US West data center'
      it 'overrides default value for data center'
      it 'raises exception if data center is invalid'

      it 'accepts provided Options instance'
      it 'generates default Options instance if not provided'
    end

    describe '#data_center=' do
      it 'overrides default value for data center'
    end

    describe '#start' do
      it 'creates a session on Sauce labs'
    end

    describe '#stop' do
      it 'quits the driver'
    end
  end
end
