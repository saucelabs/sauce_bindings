package com.saucelabs.saucebindings.citools;

import com.saucelabs.saucebindings.SystemManager;

public class CircleCI implements CITool {

  @Override
  public String getClientPlatform() {
    return "Circle CI";
  }

  @Override
  public String getBuildName() {
    String buildName = SystemManager.get("SAUCE_BUILD_NAME");
    return buildName != null ? buildName : System.getenv("CIRCLE_JOB");
  }

  @Override
  public String getBuildNumber() {
    String buildNumber = SystemManager.get("SAUCE_BUILD_NUMBER");
    return buildNumber != null ? buildNumber : System.getenv("CIRCLE_BUILD_NUM");
  }
}
