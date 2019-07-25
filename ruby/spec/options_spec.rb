# frozen_string_literal: true

require 'spec_helper'

module SimpleSauce
  describe Options do
    describe '#new' do
      it 'uses latest Chrome version on Windows 10 by default'
      it 'accepts provided values for browser, version and platform name'

      it 'accepts provided w3c values'
      it 'accepts provided Sauce values'
      it 'accepts provided browser option values'

      it 'accepts provided Selenium Browser Options instance'
      it 'accepts provided Selenium Browser Capabilities instance'
    end

    describe 'accessor variables' do
      it 'overrides default values for browser, version and platform name'
      it 'accepts provided w3c values'
      it 'accepts provided Sauce values'
      it 'accepts provided browser option values'
    end

    describe '#as_json' do
      it 'creates the correct hash representation'
    end
  end
end
