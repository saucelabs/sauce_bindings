package com.saucelabs.simplesauce;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Accessors(chain = true)
@Setter @Getter
public class TimeoutStore {
    private Integer implicitWait;
    private Integer script;
    private Integer pageLoad;

    public Map<Timeouts, Integer> getTimeouts() {
        HashMap<Timeouts, Integer> map = new HashMap<>();
        if (implicitWait != null) {
            map.put(Timeouts.IMPLICIT, implicitWait);
        }
        if (script != null) {
            map.put(Timeouts.SCRIPT, script);
        }
        if (pageLoad != null) {
            map.put(Timeouts.PAGE_LOAD, pageLoad);
        }
        return map;
    }
}
