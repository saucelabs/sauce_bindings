package com.saucelabs.saucebindings;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lombok.Getter;

public enum PageLoadStrategy {
  NONE("none"),
  EAGER("eager"),
  NORMAL("normal");

  @Getter private final String value;

  private static final class PageLoadStrategyLookup {
    private static final Map<String, String> lookup = new HashMap<String, String>();
  }

  public static Set keys() {
    return PageLoadStrategyLookup.lookup.keySet();
  }

  PageLoadStrategy(String value) {
    this.value = value;
    PageLoadStrategyLookup.lookup.put(value, this.name());
  }

  public static String fromString(String value) {
    return PageLoadStrategyLookup.lookup.get(value);
  }

  public String toString() {
    return this.value;
  }
}
