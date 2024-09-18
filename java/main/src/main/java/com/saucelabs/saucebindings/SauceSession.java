package com.saucelabs.saucebindings;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.selenium.AxeBuilder;
import com.saucelabs.saucebindings.options.BaseConfigurations;
import com.saucelabs.saucebindings.options.SauceOptions;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SauceSession {
  @Getter protected RemoteWebDriver driver;
  @Getter @Setter private DataCenter dataCenter = DataCenter.US_WEST;
  @Getter private final SauceOptions sauceOptions;
  @Setter private URL sauceUrl;
  @Getter private String result;

  public SauceSession() {
    this(new SauceOptions());
  }

  /**
   * Ideally the end user calls build() on Configurations instance. This constructor is being
   * accommodating in case they do not.
   *
   * @param configs the instance of Configuration used to create the Options
   */
  public SauceSession(BaseConfigurations configs) {
    this(configs.build());
  }

  public SauceSession(SauceOptions options) {
    sauceOptions = options;
  }

  /**
   * Starts the session on Sauce Labs.
   *
   * @return the driver instance for using as normal in your tests.
   */
  public RemoteWebDriver start() {
    if (isDisabled()) {
      return null;
    }

    this.driver = createRemoteWebDriver(getSauceUrl(), sauceOptions.toCapabilities());
    return driver;
  }

  /**
   * @return the full URL for sending to Sauce Labs based on the desired data center.
   */
  public URL getSauceUrl() {
    try {
      if (sauceUrl != null) {
        return sauceUrl;
      } else {
        return new URL(getDataCenter().getEndpoint());
      }
    } catch (MalformedURLException e) {
      throw new InvalidArgumentException("Invalid URL", e);
    }
  }

  /**
   * Analyzes Accessibility for the current page. User can work with the results from the Results
   * object or see them in the accessibility tab in the Sauce Labs UI.
   *
   * @return an object with the accessibility analysis
   */
  public Results getAccessibilityResults() {
    return getAccessibilityResults(true);
  }

  /**
   * Analyzes Accessibility for the current page. User can work with the results from the Results
   * object or see them in the accessibility tab in the Sauce Labs UI.
   *
   * @param frames whether the page being evaluated needs to dig into frames. passing false here
   *     will slightly improve performance.
   * @return an object with the accessibility analysis
   */
  public Results getAccessibilityResults(boolean frames) {
    AxeBuilder axeBuilder = new AxeBuilder();
    if (!frames) {
      axeBuilder.disableIframeTesting();
    }
    return getAccessibilityResults(axeBuilder);
  }

  /**
   * Analyzes Accessibility for the current page. User can work with the results from the Results
   * object or see them in the accessibility tab in the Sauce Labs UI.
   *
   * @param builder for advanced accessibility information provide your own instance of an
   *     AxeBuilder.
   * @return an object with the accessibility analysis
   */
  public Results getAccessibilityResults(AxeBuilder builder) {
    if (isDisabled()) {
      return null;
    }
    validateSessionStarted("getAccessibilityResults()");

    return builder.analyze(driver);
  }

  /**
   * Ends the session on Sauce Labs and quits the driver. It requires reporting whether the test has
   * passed or failed.
   *
   * @param passed true if the test has passed, otherwise false
   */
  public void stop(Boolean passed) {
    if (this.driver != null) {
      String update = passed ? "passed" : "failed";
      updateResult(update);
      quit();
    }
  }

  /**
   * Add a comment to the command list displayed in the Sauce Labs UI.
   *
   * @param comment the value to be displayed in the Sauce Labs UI.
   * @see <a
   *     href="https://docs.saucelabs.com/basics/test-config-annotation/test-annotation/#providing-context-for-selenium-commands">
   *     Providing Context for Selenium Commands</a>
   */
  public void annotate(String comment) {
    if (isDisabled()) {
      return;
    }
    validateSessionStarted("annotate()");

    driver.executeScript("sauce:context=" + comment);
  }

  /**
   * Stops the session to allow the user to take manual control of the session. No more automated
   * commands will be processed after this command
   *
   * @see <a
   *     href="https://docs.saucelabs.com/basics/test-config-annotation/test-annotation/#methods">
   *     Test Annotation Methods</a>
   */
  public void pause() {
    if (isDisabled()) {
      return;
    }
    validateSessionStarted("pause()");

    String sauceTestLink =
        String.format("https://app.saucelabs.com/tests/%s", this.driver.getSessionId());
    driver.executeScript("sauce: break");
    System.out.println("\nThis test has been stopped; no more driver commands will be accepted");
    System.out.println(
        "\nYou can take manual control of the test from the Sauce Labs UI here: " + sauceTestLink);
    this.driver = null;
  }

  /**
   * Turns off logging of commands within the test in order to omit sensitive data from the log.json
   * file. Using this feature will prevent access to other logs in their entirety
   *
   * @see #enableLogging()
   * @see <a
   *     href="https://docs.saucelabs.com/basics/test-config-annotation/test-annotation/#methods">
   *     Test Annotation Methods</a>
   */
  public void disableLogging() {
    if (isDisabled()) {
      return;
    }
    validateSessionStarted("disableLogging()");

    driver.executeScript("sauce: disable log");
  }

  /**
   * Turns logging back on after logging has been disabled.
   *
   * @see #disableLogging()
   * @see <a
   *     href="https://docs.saucelabs.com/basics/test-config-annotation/test-annotation/#methods">
   *     Test Annotation Methods</a>
   */
  public void enableLogging() {
    if (isDisabled()) {
      return;
    }
    validateSessionStarted("enableLogging()");

    driver.executeScript("sauce: enable log");
  }

  /**
   * Stops VM’s network connection.
   *
   * @see #startNetwork()
   * @see <a
   *     href="https://docs.saucelabs.com/basics/test-config-annotation/test-annotation/#methods">
   *     Test Annotation Methods</a>
   */
  public void stopNetwork() {
    if (isDisabled()) {
      return;
    }
    validateSessionStarted("stopNetwork()");
    validateMac("Can only stop network for a Mac Platform;");

    driver.executeScript("sauce: stop network");
  }

  /**
   * Restarts the VM’s network connection after it has been stopped.
   *
   * @see #stopNetwork()
   * @see <a
   *     href="https://docs.saucelabs.com/basics/test-config-annotation/test-annotation/#methods">
   *     Test Annotation Methods</a>
   */
  public void startNetwork() {
    if (isDisabled()) {
      return;
    }
    validateSessionStarted("startNetwork()");
    validateMac("Can only start network for a Mac Platform;");

    driver.executeScript("sauce: start network");
  }

  /**
   * This is typically set in SauceOptions before starting the Session. This allows dynamially
   * updating if necessary (e.g. name based on discovered state).
   *
   * @param name the test name
   * @see <a
   *     href="https://docs.saucelabs.com/basics/test-config-annotation/test-annotation/#methods">
   *     Test Annotation Methods</a>
   * @see BaseConfigurations#setName(String)
   */
  public void changeTestName(String name) {
    if (isDisabled()) {
      return;
    }
    validateSessionStarted("changeName()");

    driver.executeScript("sauce:job-name=" + name);
  }

  /**
   * This is typically set in SauceOptions before starting the Session. This allows updating
   * dynamically based on information available after starting session.
   *
   * @param tags the tags to associate with the test
   * @see <a
   *     href="https://docs.saucelabs.com/basics/test-config-annotation/test-annotation/#methods">
   *     Test Annotation Methods</a>
   * @see BaseConfigurations#setTags(List)
   */
  public void addTags(List<String> tags) {
    if (isDisabled()) {
      return;
    }
    validateSessionStarted("setTags()");

    String tagString = String.join(",", tags);
    driver.executeScript("sauce:job-tags=" + tagString);
  }

  /**
   * @deprecated Do not use magic strings, pass in boolean for whether test has passed.
   */
  @Deprecated
  public void stop(String result) {
    stop(result.equals("passed"));
  }

  /**
   * Not intended for subclassing. Package-private for testing.
   *
   * @param url to send session commands to
   * @param capabilities configuration for session
   * @return driver instance
   */
  RemoteWebDriver createRemoteWebDriver(URL url, Capabilities capabilities) {
    return new RemoteWebDriver(url, capabilities);
  }

  private void updateResult(String result) {
    this.result = result;
    getDriver().executeScript("sauce:job-result=" + result);

    // Add output for the Sauce OnDemand Jenkins plugin
    // The first print statement will automatically populate links on Jenkins to Sauce
    // The second print statement will output the job link to logging/console
    String sauceReporter =
        String.format(
            "SauceOnDemandSessionID=%s job-name=%s",
            this.driver.getSessionId(), this.sauceOptions.sauce().getName());
    String sauceTestLink =
        String.format(
            "Test Job Link:" + getDataCenter().getTestLink() + "%s", this.driver.getSessionId());
    System.out.print(sauceReporter + "\n" + sauceTestLink + "\n");
  }

  private void validateSessionStarted(String method) {
    if (driver == null) {
      throw new SauceSessionNotStartedException(method);
    }
  }

  private void quit() {
    driver.quit();
    driver = null;
  }

  private void validateMac(String msg) {
    SaucePlatform platformName = sauceOptions.getPlatformName();

    if (!platformName.isMac()) {
      String error = msg + " current platform is: " + platformName;
      throw new InvalidArgumentException(error);
    }
  }

  private boolean isDisabled() {
    return Boolean.parseBoolean(System.getenv("SAUCE_DISABLED"))
        || Boolean.getBoolean("sauce.disabled");
  }
}
