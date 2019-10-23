package com.saucelabs.simplesauce;

public enum EdgeVersion {
    E16("16_16299"),
    E15("15_15063"),
    E14("14_14393"),
    E13("13_10586"),
    E17("17_17134"),
    E18("18_17763");

    public String label;

    EdgeVersion(String label) {
        this.label = label;
    }
}
