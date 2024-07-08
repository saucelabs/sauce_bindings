package com.saucelabs.saucebindings.junit5;

import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

import lombok.Getter;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;

public class SauceBindingsExtension implements TestWatcher, BeforeEachCallback {
  private static final Logger logger = Logger.getLogger(SauceBindingsExtension.class.getName());
  private final SauceOptions sauceOptions;
  private final DataCenter dataCenter;
  @Getter private SauceSession session;
  @Getter private WebDriver driver;

  public SauceBindingsExtension() {
    this(new SauceOptions(), DataCenter.US_WEST);
  }

  public SauceBindingsExtension(SauceOptions sauceOptions) {
    this(sauceOptions, DataCenter.US_WEST);
  }

  public SauceBindingsExtension(SauceOptions sauceOptions, DataCenter dataCenter) {
    this.sauceOptions = sauceOptions;
    this.dataCenter = dataCenter;
  }

  public SauceBindingsExtension(Capabilities capabilities) {
    this(capabilities, DataCenter.US_WEST);
  }

  public SauceBindingsExtension(Capabilities capabilities, DataCenter dataCenter) {
    this.sauceOptions = new SauceOptions();

    // TODO: Update Sauce Bindings to handle "sauce:options" the same as it handles "sauce
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

  public SauceBindingsExtension(DataCenter dataCenter) {
    this(new SauceOptions(), dataCenter);
  }

  @Override
  public void beforeEach(ExtensionContext context) {
    if (isExtensionDisabled()) {
      return;
    }

    if (sauceOptions.sauce().getName() == null) {
      sauceOptions.sauce().setName(context.getDisplayName());
    }

    session = new SauceSession(sauceOptions);
    session.setDataCenter(dataCenter);
    driver = session.start();
  }

  @Override
  public void testSuccessful(ExtensionContext context) {
    if (isExtensionDisabled()) {
      return;
    }

    try {
      session.stop(true);
    } catch (NoSuchSessionException e) {
      logger.severe(
          "Driver quit prematurely; Remove calls to `driver.quit()` to allow SauceBindingsExtension"
              + " to stop the test");
    }
  }

  @Override
  public void testFailed(ExtensionContext context, Throwable cause) {
    if (session != null) {
      session.annotate("Failure Reason: " + cause.getMessage());

      Arrays.stream(cause.getStackTrace())
          .map(StackTraceElement::toString)
          .filter(line -> !line.contains("sun"))
          .forEach(session::annotate);

      session.stop(false);
    }
  }

  // TODO: Implement this in SauceSession directly
  private boolean isExtensionDisabled() {
    String value = System.getProperty("SAUCE_DISABLED");
    return Boolean.parseBoolean(value) || Boolean.getBoolean("sauce.disabled");
  }
}
