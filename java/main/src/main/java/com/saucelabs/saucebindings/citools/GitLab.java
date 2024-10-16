package com.saucelabs.saucebindings.citools;

public class GitLab implements CITool {

  @Override
  public String getToolName() {
    return "GitLab";
  }

  @Override
  public String getBuildName() {
    return System.getenv("CI_JOB_NAME");
  }

  @Override
  public String getBuildNumber() {
    return System.getenv("CI_JOB_ID");
  }
}
