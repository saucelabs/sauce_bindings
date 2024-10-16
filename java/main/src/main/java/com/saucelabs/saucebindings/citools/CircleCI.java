package com.saucelabs.saucebindings.citools;

public class CircleCI implements CITool {

  @Override
  public String getToolName() {
    return "Circle CI";
  }

  @Override
  public String getBuildName() {
    return System.getenv("CIRCLE_JOB");
  }

  @Override
  public String getBuildNumber() {
    return System.getenv("CIRCLE_BUILD_NUM");
  }
}
