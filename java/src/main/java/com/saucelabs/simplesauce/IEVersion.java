package com.saucelabs.simplesauce;

import lombok.Getter;

enum IEVersion {
    _11("11.285");

    @Getter private final String version;

    IEVersion(String version) {
        this.version = version;
    }
}
