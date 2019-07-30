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

      it 'uses username and access key if ENV variables are defined'
      it 'accepts provided username and access key'
    end

    describe '#data_center=' do
      it 'overrides default value for data center'
    end

    describe '#username=' do
      it 'accepts provided username'
    end

    describe '#access_key=' do
      it 'accepts provided access key'
    end

    describe '#start' do
      it 'creates a session on Sauce labs'
      it 'raises exception if no username set'
      it 'raises exception if no access key set'
    end

    describe '#stop' do
      it 'quits the driver'
    end
  end
end
