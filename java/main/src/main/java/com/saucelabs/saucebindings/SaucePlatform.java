package com.saucelabs.saucebindings;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lombok.Getter;

public enum SaucePlatform {
  LINUX("Linux"),
  WINDOWS_11("Windows 11"),
  WINDOWS_10("Windows 10"),
  WINDOWS_8_1("Windows 8.1"),
  WINDOWS_8("Windows 8"),
  MAC_VENTURA("macOS 13"),
  MAC_MONTEREY("macOS 12"),
  MAC_BIG_SUR("macOS 11"),
  MAC_CATALINA("macOS 10.15"),
  MAC_MOJAVE("macOS 10.14"),
  MAC_HIGH_SIERRA("macOS 10.13"),
  MAC_SIERRA("macOS 10.12"),
  MAC_EL_CAPITAN("OS X 10.11"),
  MAC_YOSEMITE("OS X 10.10");

  @Getter private final String value;

  private static final class PlatformLookup {
    private static final Map<String, String> lookup = new HashMap<String, String>();
  }

  public static Set keys() {
    return PlatformLookup.lookup.keySet();
  }

  SaucePlatform(String value) {
    this.value = value;
    PlatformLookup.lookup.put(value, this.name());
  }

  public static String fromString(String value) {
    return PlatformLookup.lookup.get(value);
  }

  /**
   * Whether the platform is on an Apple Mac OS.
   *
   * @return true if platform is OS X or macOS
   */
  public boolean isMac() {
    return this.value.contains("macOS") || this.value.contains("OS X");
  }

  /**
   * Whether the platform is Microsoft Windows.
   *
   * @return true if platform is Windows OS
   */
  public boolean isWindows() {
    return this.value.contains("Windows");
  }

  public String toString() {
    return this.value;
  }
}
