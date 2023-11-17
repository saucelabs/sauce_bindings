package com.saucelabs.saucebindings.junit4;

import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;

public class SauceBindingsExtension extends TestWatcher {
  private final SauceOptions sauceOptions;
  private final DataCenter dataCenter;
  private final Boolean enabled;
  @Getter private SauceSession session;
  @Getter private WebDriver driver;

  public SauceBindingsExtension() {
    this(builder());
  }

  private SauceBindingsExtension(Builder builder) {
    this.sauceOptions = builder.sauceOptions == null ? new SauceOptions() : builder.sauceOptions;
    this.dataCenter = builder.dataCenter;
    this.enabled = builder.enabled;
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  protected void starting(Description description) {
    if (!enabled) {
      return;
    }

    if (sauceOptions.sauce().getName() == null) {
      sauceOptions.sauce().setName(description.getDisplayName());
    }

    session = new SauceSession(sauceOptions);
    session.setDataCenter(dataCenter);
    driver = session.start();
  }

  @Override
  protected void succeeded(Description description) {
    if (!enabled) {
      return;
    }

    try {
      session.stop(true);
    } catch (NoSuchSessionException e) {
      System.out.println(
          "Driver quit prematurely; Remove calls to `driver.quit()` to allow SauceBindingsExtension to stop the test");
    }
  }

  @Override
  protected void failed(Throwable e, Description description) {
    if (session != null) {
      session.annotate("Failure Reason: " + e.getMessage());

      Arrays.stream(e.getStackTrace())
          .map(StackTraceElement::toString)
          .filter(line -> !line.contains("sun"))
          .forEach(session::annotate);

      session.stop(false);
    }
  }

  public static class Builder {
    private SauceOptions sauceOptions;
    private DataCenter dataCenter = DataCenter.US_WEST;
    private Boolean enabled = true;

    public Builder capabilities(SauceOptions sauceOptions) {
      this.sauceOptions = sauceOptions;
      return this;
    }

    public Builder capabilities(Capabilities capabilities) {
      this.sauceOptions = convertCapabilities(capabilities);
      return this;
    }

    public Builder dataCenter(DataCenter dataCenter) {
      this.dataCenter = dataCenter;
      return this;
    }

    public Builder enabled(Boolean enabled) {
      this.enabled = enabled;
      return this;
    }

    public SauceBindingsExtension build() {
      return new SauceBindingsExtension(this);
    }

    // TODO: Put this in Sauce Bindings directly?
    private SauceOptions convertCapabilities(Capabilities capabilities) {
      SauceOptions options = new SauceOptions();
      Map<String, Object> capabilitiesMap = new HashMap<>(capabilities.asMap());

      @SuppressWarnings("unchecked")
      Map<String, Object> sauceMap = (Map<String, Object>) capabilitiesMap.get("sauce:options");
      capabilitiesMap.remove("sauce:options");
      options.mergeCapabilities(capabilitiesMap);
      options.sauce().mergeCapabilities(sauceMap);
      return options;
    }
  }
}
