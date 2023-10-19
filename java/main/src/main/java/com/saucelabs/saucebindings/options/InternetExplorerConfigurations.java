package com.saucelabs.saucebindings.options;

import org.openqa.selenium.ie.InternetExplorerOptions;

public class InternetExplorerConfigurations
    extends VDCConfigurations<InternetExplorerConfigurations> {
  InternetExplorerConfigurations(InternetExplorerOptions internetExplorerOptions) {
    sauceOptions = new SauceOptions(internetExplorerOptions);
  }

  /**
   * You should almost always use the latest version of Selenium
   *
   * @param version the version of Selenium Server to use for the test
   * @return instance of configuration
   */
  public InternetExplorerConfigurations setSeleniumVersion(String version) {
    sauceOptions.sauce().setSeleniumVersion(version);
    return this;
  }

  /**
   * This should match the Selenium version
   *
   * @param version the specific version of Driver to use with the job
   * @return instance of configuration
   */
  public InternetExplorerConfigurations setIedriverVersion(String version) {
    sauceOptions.sauce().setIedriverVersion(version);
    return this;
  }

  /**
   * Sauce routes traffic through a proxy server so that HTTPS connections with self-signed
   * certificates work everywhere. This method allows bypassing the proxy so browsers communicate
   * directly the server. Toggles avoid Proxy to True
   *
   * @return instance of configuration
   */
  public InternetExplorerConfigurations setAvoidProxy() {
    sauceOptions.sauce().setAvoidProxy(true);
    return this;
  }
}
