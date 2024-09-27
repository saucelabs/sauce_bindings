package com.saucelabs.saucebindings.options;

import com.saucelabs.saucebindings.CITools;
import com.saucelabs.saucebindings.JobVisibility;
import com.saucelabs.saucebindings.Prerun;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucebindings.SystemManager;
import java.net.URL;
import java.util.*;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.openqa.selenium.MutableCapabilities;

@Accessors(chain = true)
@Setter
@Getter
public class SauceLabsOptions extends BaseOptions {
  // https://wiki.saucelabs.com/display/DOCS/Test+Configuration+Options
  private Boolean avoidProxy = null;
  private String build;
  private Boolean capturePerformance = null;
  private String chromedriverVersion;
  private Integer commandTimeout = null;
  private Map<String, Object> customData = null;
  private Boolean extendedDebugging = null;
  private String geckodriverVersion;
  private String edgedriverVersion;
  private Integer idleTimeout = null;
  private String iedriverVersion;
  private Integer maxDuration = null;
  private String name;
  private String parentTunnel;
  private Map<Prerun, Object> prerun;
  private URL prerunUrl;
  private Integer priority = null;
  private JobVisibility
      jobVisibility; // the actual key for this is a Java reserved keyword "public"; uses enum
  private Boolean recordLogs = null;
  private Boolean recordScreenshots = null;
  private Boolean recordVideo = null;
  private String screenResolution;
  private String seleniumVersion;

  public BaseOptions setTags(List<String> tags) {
    this.tags.addAll(tags);
    return this;
  }

  public BaseOptions setTag(String tags) {
    this.tags.add(tags);
    return this;
  }

  private List<String> tags = new ArrayList<>(Arrays.asList("sauce-bindings", "java"));
  private String timeZone;
  private String tunnelIdentifier;
  private Boolean videoUploadOnPass = null;

  public final List<String> validOptions =
      Arrays.asList(
          "avoidProxy",
          "build",
          "capturePerformance",
          "chromedriverVersion",
          "commandTimeout",
          "customData",
          "extendedDebugging",
          "geckodriverVersion",
          "edgedriverVersion",
          "idleTimeout",
          "iedriverVersion",
          "jobVisibility",
          "maxDuration",
          "name",
          "parentTunnel",
          "prerun",
          "prerunUrl",
          "priority",
          // public, do not use, reserved keyword, using jobVisibility with enum
          "recordLogs",
          "recordScreenshots",
          "recordVideo",
          "screenResolution",
          "seleniumVersion",
          "tags",
          "timeZone",
          "tunnelIdentifier",
          "videoUploadOnPass");

  public SauceLabsOptions() {
    capabilityManager = new CapabilityManager(this);
  }

  /**
   * @return instance of MutableCapabilities representing all key value pairs set in SauceOptions
   * @see SauceSession#start()
   */
  public MutableCapabilities toCapabilities() {
    capabilities.setCapability("username", getSauceUsername());
    capabilities.setCapability("accessKey", getSauceAccessKey());

    Object visibilityValue = capabilityManager.getCapability("jobVisibility");
    if (visibilityValue != null) {
      capabilities.setCapability("public", visibilityValue);
      setJobVisibility(null);
    }

    Object customDataValue = capabilityManager.getCapability("customData");
    if (customDataValue != null) {
      capabilities.setCapability("custom-data", customDataValue);
      setCustomData(null);
    }

    Object prerunValue = capabilityManager.getCapability("prerunUrl");
    if (prerunValue != null) {
      capabilities.setCapability("prerun", prerunValue);
      setPrerunUrl(null);
    }

    capabilityManager.addCapabilities();
    return capabilities;
  }

  /**
   * This method is to handle special cases and enums as necessary
   *
   * @param key Which capability to set on this instance's Selenium MutableCapabilities instance
   * @param value The value of the capability getting set
   */
  @Override
  protected void setCapability(String key, Object value) {
    if ("jobVisibility".equals(key)) {
      capabilityManager.validateCapability("JobVisibility", JobVisibility.keys(), (String) value);
      setJobVisibility(JobVisibility.valueOf(JobVisibility.fromString((String) value)));
    } else if ("prerun".equals(key)) {
      Map<Prerun, Object> prerunMap = new HashMap<>();
      ((Map) value)
          .forEach(
              (oldKey, val) -> {
                capabilityManager.validateCapability("Prerun", Prerun.keys(), (String) oldKey);
                String keyString = Prerun.fromString((String) oldKey);
                prerunMap.put(Prerun.valueOf(keyString), val);
              });
      setPrerun(prerunMap);
    } else if ("tags".equals(key)) {
      try {
        List<String> tagsList = (List) value;
        for (String tag: tagsList) {
          tags.add(tag);
        }
      } catch (ClassCastException e) {
        throw new RuntimeException(e);
      }
    }
    else {
      super.setCapability(key, value);
    }
  }

  /**
   * @return a String representing the best default build name and number for your test based on CI
   *     Tool ENV Variables
   */
  public String getBuild() {
    return build != null ? build : CITools.getBuildName() + ": " + CITools.getBuildNumber();
  }

  /**
   * @deprecated This method is no longer supported
   * @return whether CI is accounted for in code
   */
  @Deprecated
  public boolean isKnownCI() {
    return CITools.getBuildName() != null;
  }

  /**
   * @deprecated use CITools.knownTools instead
   */
  @Deprecated public static final Map<String, String> knownCITools = CITools.KNOWN_TOOLS;

  protected String getSauceUsername() {
    return SystemManager.get("SAUCE_USERNAME", "Sauce Username was not provided");
  }

  protected String getSauceAccessKey() {
    return SystemManager.get("SAUCE_ACCESS_KEY", "Sauce Access Key was not provided");
  }
}
