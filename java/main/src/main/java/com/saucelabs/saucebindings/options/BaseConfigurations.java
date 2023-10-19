package com.saucelabs.saucebindings.options;

import com.saucelabs.saucebindings.JobVisibility;
import com.saucelabs.saucebindings.SaucePlatform;
import java.util.List;
import java.util.Map;

public abstract class BaseConfigurations<T extends BaseConfigurations<T>> {
  // Needs to be instantiated in subclass
  SauceOptions sauceOptions = null;

  // Available on all configs desktop & mobile

  /**
   * @param platform the enum representing the operating system the browser or mobile device should
   *     be running on
   * @return instance of configuration
   */
  // Override this in subclasses to ensure valid enum
  public T setPlatformName(SaucePlatform platform) {
    sauceOptions.setPlatformName(platform);
    return (T) this;
  }

  /**
   * @param name used to record test names for jobs and make it easier to find individual tests
   * @return instance of configuration
   */
  public T setName(String name) {
    sauceOptions.sauce().setName(name);
    return (T) this;
  }

  /**
   * @param build used to associate jobs with a build number, which is then displayed on both the
   *     Dashboard and Archives view
   * @return instance of configuration
   */
  public T setBuild(String build) {
    sauceOptions.sauce().setBuild(build);
    return (T) this;
  }

  /**
   * @param tags used to provide labels for grouping and filtering jobs in the Dashboard and
   *     Archives view.
   * @return instance of configuration
   */
  public T setTags(List<String> tags) {
    sauceOptions.sauce().setTags(tags);
    return (T) this;
  }

  /**
   * @param data used to provide any custom data associated with a test, limited to 64KB in size
   * @return instance of configuration
   */
  public T setCustomData(Map<String, Object> data) {
    sauceOptions.sauce().setCustomData(data);
    return (T) this;
  }

  /**
   * This corresponds to "public" keyword in Sauce Documentation, but 'public' is a reserved keyword
   * in Java
   *
   * @param visibility enum representing test result visibility level, which controls who can view
   *     the test details
   * @return instance of configuration
   */
  public T setJobVisibility(JobVisibility visibility) {
    sauceOptions.sauce().setJobVisibility(visibility);
    return (T) this;
  }

  /**
   * @param identifier the name of the Sauce Connect tunnel for the test to use
   * @return instance of configuration
   */
  public T setTunnelIdentifier(String identifier) {
    sauceOptions.sauce().setTunnelIdentifier(identifier);
    return (T) this;
  }

  /**
   * Setting this requires also setting a tunnelIdentifier
   *
   * @param identifier the ancestor account name associated with the Sauce Connect tunnel the test
   *     should use
   * @return instance of configuration
   */
  public T setParentTunnel(String identifier) {
    sauceOptions.sauce().setParentTunnel(identifier);
    return (T) this;
  }

  /**
   * @return sauceOptions instance built from setter methods
   */
  public SauceOptions build() {
    return sauceOptions;
  }
}
