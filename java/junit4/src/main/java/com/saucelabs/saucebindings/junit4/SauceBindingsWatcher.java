package com.saucelabs.saucebindings.junit4;

import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;
import lombok.Getter;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;

@Getter
public class SauceBindingsWatcher extends TestWatcher {
  private static final Logger logger = Logger.getLogger(SauceBindingsWatcher.class.getName());
  private final SauceOptions sauceOptions;
  private final DataCenter dataCenter;
  private SauceSession session;
  private WebDriver driver;

  public SauceBindingsWatcher() {
    this(new SauceOptions(), DataCenter.US_WEST);
  }

  public SauceBindingsWatcher(SauceOptions sauceOptions) {
    this(sauceOptions, DataCenter.US_WEST);
  }

  public SauceBindingsWatcher(SauceOptions sauceOptions, DataCenter dataCenter) {
    this.sauceOptions = sauceOptions;
    this.dataCenter = dataCenter;
  }

  public SauceBindingsWatcher(Capabilities capabilities) {
    this(capabilities, DataCenter.US_WEST);
  }

  public SauceBindingsWatcher(Capabilities capabilities, DataCenter dataCenter) {
    this.sauceOptions = new SauceOptions();
    Map<String, Object> capabilitiesMap = new HashMap<>(capabilities.asMap());
    Optional.ofNullable(capabilitiesMap.get("sauce:options"))
        .filter(Map.class::isInstance)
        .map(Map.class::cast)
        .ifPresent(
            sauceOptionsMap -> {
              capabilitiesMap.put("sauce", sauceOptionsMap);
              capabilitiesMap.remove("sauce:options");
            });
    this.sauceOptions.mergeCapabilities(capabilitiesMap);
    this.dataCenter = dataCenter;
  }

  public SauceBindingsWatcher(DataCenter dataCenter) {
    this(new SauceOptions(), dataCenter);
  }

  @Override
  protected void starting(Description description) {
    if (sauceOptions.sauce().getName() == null) {
      sauceOptions.sauce().setName(description.getMethodName());
    }

    session = new SauceSession(sauceOptions);
    session.setDataCenter(dataCenter);
    driver = session.start();
  }

  @Override
  protected void succeeded(Description description) {
    try {
      session.stop(true);
    } catch (NoSuchSessionException e) {
      logger.severe(
          "Driver quit prematurely; Remove calls to `driver.quit()` to allow"
              + " SauceBindingsExtension to stop the test");
    }
  }

  @Override
  protected void failed(Throwable e, Description description) {
    if (session != null) {
      try {
        session.annotate("Failure Reason: " + e.getMessage());

        Arrays.stream(e.getStackTrace())
            .map(StackTraceElement::toString)
            .filter(line -> !line.contains("sun"))
            .forEach(session::annotate);

        session.stop(false);
      } catch (NoSuchSessionException ex) {
        logger.severe(
            "Driver quit prematurely; Remove calls to `driver.quit()` to allow"
                + " SauceBindingsExtension to stop the test");
      }
    }
  }
}
