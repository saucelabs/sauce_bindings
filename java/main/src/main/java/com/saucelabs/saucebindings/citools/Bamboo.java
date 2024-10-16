package com.saucelabs.saucebindings.citools;

public class Bamboo implements CITool {

  @Override
  public String getToolName() {
    return "Bamboo";
  }

  @Override
  public String getBuildName() {
    return System.getenv("bamboo_shortJobName");
  }

  @Override
  public String getBuildNumber() {
    return System.getenv("bamboo_buildNumber");
  }
}
