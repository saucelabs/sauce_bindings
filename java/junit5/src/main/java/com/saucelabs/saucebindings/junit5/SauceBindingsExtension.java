package com.saucelabs.saucebindings.junit5;

import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SauceBindingsExtension implements TestWatcher, BeforeEachCallback, ParameterResolver {
  private static final Logger LOGGER = Logger.getLogger(SauceBindingsExtension.class.getName());
  protected SauceOptions sauceOptions = new SauceOptions();
  protected DataCenter dataCenter = DataCenter.US_WEST;

  public SauceBindingsExtension() {}

  private SauceBindingsExtension(SauceOptions sauceOptions, DataCenter dataCenter) {
    this.sauceOptions = sauceOptions;
    this.dataCenter = dataCenter;
  }

  @Override
  public void beforeEach(ExtensionContext context) {
    SauceOptions options = updateOptions(context);
    SauceSession session = new SauceSession(options);
    session.setDataCenter(dataCenter);
    WebDriver driver = session.start();

    getStore(context).put("session", session);
    getStore(context).put("driver", driver);
  }

  private SauceOptions updateOptions(ExtensionContext context) {
    SauceOptions options = sauceOptions.copy();
    options.sauce().setName(context.getDisplayName());
    options.sauce().getCustomData().put("sauce-bindings", "junit5");
    List<String> tags = options.sauce().getTags();
    if (tags != null) {
      tags.addAll(context.getTags());
    } else {
      options.sauce().setTags(new ArrayList<>(context.getTags()));
    }

    return options;
  }

  private ExtensionContext.Store getStore(ExtensionContext context) {
    return context.getStore(
        ExtensionContext.Namespace.create(getClass(), context.getRequiredTestMethod()));
  }

  @Override
  public void testSuccessful(ExtensionContext context) {
    if (!SauceSession.isDisabled()) {
      SauceSession session = (SauceSession) getStore(context).get("session");
      RemoteWebDriver driver = session.getDriver();
      try {
        session.stop(true);
      } catch (NoSuchSessionException e) {
        LOGGER.severe(
            "Driver quit prematurely; Remove calls to `driver.quit()` to allow SauceBindingsExtension"
                + " to stop the test");
      }
    }
  }

  @Override
  public void testFailed(ExtensionContext context, Throwable cause) {
    if (!SauceSession.isDisabled()) {
      SauceSession session = (SauceSession) getStore(context).get("session");
      try {
        session.annotate("Failure Reason: " + cause.getMessage());

        Arrays.stream(cause.getStackTrace())
            .map(StackTraceElement::toString)
            .filter(line -> !line.contains("sun"))
            .forEach(session::annotate);

        session.stop(false);
      } catch (NoSuchSessionException e) {
        LOGGER.severe(
            "Driver quit prematurely; Remove calls to `driver.quit()` to allow SauceBindingsExtension"
                + " to stop the test");
      }
    }
  }

  @Override
  public void testAborted(ExtensionContext context, Throwable cause) {
    LOGGER.fine("Test Aborted: " + cause.getMessage());
    SauceSession session = (SauceSession) getStore(context).get("session");
    if (session != null) {
      session.annotate("Test Aborted; marking completed instead of failed");
      session.annotate("Reason: " + cause.getMessage());

      Arrays.stream(cause.getStackTrace())
              .map(StackTraceElement::toString)
              .filter(line -> !line.contains("sun"))
              .forEach(session::annotate);

      session.abort();
    }
  }

  @Override
  public void testDisabled(ExtensionContext context, Optional<String> reason) {
    LOGGER.info("A Sauce session was not started for " + context.getDisplayName() + " because " + reason);
  }

  @Override
  public boolean supportsParameter(
      ParameterContext parameterContext, ExtensionContext extensionContext) {
    boolean session = parameterContext.getParameter().getType() == SauceSession.class;
    boolean driver = parameterContext.getParameter().getType() == WebDriver.class;
    return session || driver;
  }

  @Override
  public Object resolveParameter(
      ParameterContext parameterContext, ExtensionContext extensionContext) {
    if (parameterContext.getParameter().getType() == WebDriver.class) {
      return getStore(extensionContext).get("driver");
    } else {
      return getStore(extensionContext).get("session");
    }
  }

  public static class Builder {
    private SauceOptions sauceOptions = new SauceOptions();
    private DataCenter dataCenter = DataCenter.US_WEST;

    public Builder withSauceOptions(SauceOptions sauceOptions) {
      this.sauceOptions = sauceOptions;
      return this;
    }

    public Builder withCapabilities(Capabilities capabilities) {
      this.sauceOptions.mergeCapabilities(capabilities.asMap());
      return this;
    }

    public Builder withDataCenter(DataCenter dataCenter) {
      this.dataCenter = dataCenter;
      return this;
    }

    public SauceBindingsExtension build() {
      return new SauceBindingsExtension(sauceOptions, dataCenter);
    }
  }
}
