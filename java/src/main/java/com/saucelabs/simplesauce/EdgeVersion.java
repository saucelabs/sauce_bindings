package com.saucelabs.simplesauce;

import lombok.Getter;

enum EdgeVersion {
    _18("18.17763"),
    _17("17.17134"),
    _16("16.16299"),
    _15("15.15063"),
    _14("14.14393"),
    _13("13.10586");

    @Getter private final String version;

    EdgeVersion(String version) {
        this.version = version;
    }
}
