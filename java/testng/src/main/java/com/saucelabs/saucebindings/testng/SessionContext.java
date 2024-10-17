package com.saucelabs.saucebindings.testng;

import com.saucelabs.saucebindings.CITools;
import com.saucelabs.saucebindings.DataCenter;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.options.SauceOptions;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.ITestContext;
import org.testng.ITestResult;

public class SessionContext {
  private final String key;
  private final ITestContext context;
  private static final String buildName = CITools.getBuildName() + "." + CITools.getBuildNumber();

  public static void setEnabled(ITestResult result) {
    Method method = result.getMethod().getConstructorOrMethod().getMethod();
    String className = method.getDeclaringClass().getName();
    result.getTestContext().setAttribute(className + "_listener", "true");
  }

  public static boolean isListening(Method method, ITestContext context) {
    String className = method.getDeclaringClass().getName();
    return Boolean.parseBoolean((String) context.getAttribute(className + "_listener"));
  }

  public static SauceSession getSession(ITestResult result) {
    Method method = result.getMethod().getConstructorOrMethod().getMethod();
    return (SauceSession) result.getTestContext().getAttribute(getKey(method));
  }

  public static String getKey(Method method) {
    return method.getDeclaringClass().getSimpleName() + "." + method.getName() + "_session";
  }

  private SessionContext(ITestContext context, String key) {
    this.context = context;
    this.key = key;
  }

  public WebDriver getDriver() {
    return getSession().getDriver();
  }

  public SauceSession getSession() {
    return (SauceSession) context.getAttribute(key);
  }

  public static SessionBuilder build(Method method, ITestContext context) {
    return new SessionBuilder(method, context);
  }

  public static class SessionBuilder {
    private final Method method;
    private final ITestContext context;
    private SauceOptions options;
    private DataCenter dataCenter = DataCenter.US_WEST;

    public SessionBuilder(Method method, ITestContext context) {
      this.method = method;
      this.context = context;
      this.options = new SauceOptions();
    }

    public SessionBuilder withSauceOptions(SauceOptions options) {
      this.options = options;
      return this;
    }

    public SessionBuilder withDataCenter(DataCenter dataCenter) {
      this.dataCenter = dataCenter;
      return this;
    }

    public SessionBuilder withCapabilities(Capabilities capabilities) {
      this.options.mergeCapabilities(capabilities.asMap());
      return this;
    }

    private void updateTestName() {
      String testName = method.getAnnotation(Test.class).description();
      if (testName == null || testName.isEmpty()) {
        testName = method.getDeclaringClass().getSimpleName() + ": " + method.getName();
      }
      options.sauce().setName(testName);
    }

    private void updateCustomData() {
      Properties prop = new Properties();
      try (InputStream input = SauceBindingsListener.class.getResourceAsStream("/app.properties")) {
        prop.load(input);
        String version = prop.getProperty("version", "unknown");
        options.sauce().getCustomData().put("sauce-bindings-testng", version);
      } catch (IOException ignored) {
        options.sauce().getCustomData().put("sauce-bindings-testng", "unknown");
      }
    }

    private void updateTags() {
      Test testAnnotation = method.getAnnotation(Test.class);
      if (testAnnotation != null) {
        String[] groups = testAnnotation.groups();
        if (groups != null) {
          List<String> tags = options.sauce().getTags();
          tags = tags == null ? new ArrayList<>() : new ArrayList<>(tags);
          tags.addAll(Arrays.asList(groups));
          options.sauce().setTags(Arrays.asList(groups));
        }
      }
    }

    public SessionContext start() {
      if (!isListening(method, context)) {
        String msg = "SauceBindingsListener must be configured in test suite xml or with @Listeners annotation";
        throw new RuntimeException(msg + " for: " + method.getDeclaringClass().getName());
      }

      options.sauce().setBuild(buildName);
      updateTestName();
      updateCustomData();
      updateTags();

      SauceSession session = new SauceSession(options);
      session.setDataCenter(dataCenter);
      session.start();

      String key = getKey(method);
      context.setAttribute(key, session);
      return new SessionContext(context, key);
    }
  }
}
