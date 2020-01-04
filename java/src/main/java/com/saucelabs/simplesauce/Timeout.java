package com.saucelabs.simplesauce;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Accessors(chain = true)
@Setter @Getter
public class Timeout {
    private Integer implicitWait;
    private Integer script;
    private Integer pageLoad;

    public Map<Options.Timeouts, Integer> convert() {
        HashMap<Options.Timeouts, Integer> map = new HashMap<>();
        if (implicitWait != null) {
            map.put(Options.Timeouts.IMPLICIT, implicitWait);
        }
        if (script != null) {
            map.put(Options.Timeouts.SCRIPT, script);
        }
        if (pageLoad != null) {
            map.put(Options.Timeouts.PAGE_LOAD, pageLoad);
        }
        return map;
    }
}
