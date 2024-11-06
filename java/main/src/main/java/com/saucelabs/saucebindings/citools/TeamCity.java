package com.saucelabs.saucebindings.citools;

import com.saucelabs.saucebindings.SystemManager;

public class TeamCity implements CITool {

  @Override
  public String getClientPlatform() {
    return "Team City";
  }

  @Override
  public String getBuildName() {
    String buildName = SystemManager.get("SAUCE_BUILD_NAME");
    return buildName != null ? buildName : System.getenv("TEAMCITY_PROJECT_NAME");
  }

  @Override
  public String getBuildNumber() {
    String buildNumber = SystemManager.get("SAUCE_BUILD_NUMBER");
    return buildNumber != null ? buildNumber : System.getenv("BUILD_NUMBER");
  }
}
