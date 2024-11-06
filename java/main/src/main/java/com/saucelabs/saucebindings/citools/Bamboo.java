package com.saucelabs.saucebindings.citools;

import com.saucelabs.saucebindings.SystemManager;

public class Bamboo implements CITool {

  @Override
  public String getClientPlatform() {
    return "Bamboo";
  }

  @Override
  public String getBuildName() {
    String buildName = SystemManager.get("SAUCE_BUILD_NAME");
    return buildName != null ? buildName : System.getenv("bamboo_shortJobName");
  }

  @Override
  public String getBuildNumber() {
    String buildNumber = SystemManager.get("SAUCE_BUILD_NUMBER");
    return buildNumber != null ? buildNumber : System.getenv("bamboo_buildNumber");
  }
}
