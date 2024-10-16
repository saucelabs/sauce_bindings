package com.saucelabs.saucebindings.citools;

public class Jenkins implements CITool {

  @Override
  public String getToolName() {
    return "Jenkins";
  }

  @Override
  public String getBuildName() {
    return System.getenv("BUILD_NAME");
  }

  @Override
  public String getBuildNumber() {
    return System.getenv("BUILD_NUMBER");
  }
}
