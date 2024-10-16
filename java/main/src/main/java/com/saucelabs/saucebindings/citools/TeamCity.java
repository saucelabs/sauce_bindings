package com.saucelabs.saucebindings.citools;

public class TeamCity implements CITool {

  @Override
  public String getToolName() {
    return "Team City";
  }

  @Override
  public String getBuildName() {
    return System.getenv("TEAMCITY_PROJECT_NAME");
  }

  @Override
  public String getBuildNumber() {
    return System.getenv("BUILD_NUMBER");
  }
}
