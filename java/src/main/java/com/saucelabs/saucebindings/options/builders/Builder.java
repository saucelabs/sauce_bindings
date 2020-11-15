package com.saucelabs.saucebindings.options.builders;

import com.saucelabs.saucebindings.SauceOptions;
import com.saucelabs.saucebindings.options.VisualOptions;

public interface Builder<T extends Builder> {
    SauceOptions getSauceOptions();

    public default VisualOptions<T> visual() {
        return new VisualOptions(this);
    }
}
