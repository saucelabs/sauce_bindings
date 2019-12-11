package com.saucelabs.simplesauce;

import lombok.Getter;

enum SafariVersion {
    _8("8.0");

    @Getter private final String version;

    SafariVersion(String version) {
        this.version = version;
    }
}
