package com.saucelabs.saucebindings;

public class SystemManager {

  /**
   * Shortcut for getting the System Property or the Environment Variable If the no value is found,
   * an exception is thrown with the provided message
   *
   * @param key the name of the property or environment variable
   * @param errorMessage the error message if the property or environment variable is not found
   * @return the value of the provided field name
   */
  public static String get(String key, String errorMessage) {
    String value = get(key);
    if (value == null) {
      throw new SauceEnvironmentVariablesNotSetException(errorMessage);
    } else {
      return value;
    }
  }

  /**
   * Shortcut for getting the System Property or the Environment Variable
   *
   * @param key the name of the property or environment variable
   * @return the value of the provided field name
   */
  public static String get(String key) {
    if (System.getProperty(key) != null) {
      return System.getProperty(key);
    } else if (System.getenv(key) != null) {
      return System.getenv(key);
    } else {
      return null;
    }
  }
}
