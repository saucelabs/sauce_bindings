package com.saucelabs.saucebindings.citools;

public class GitHub implements CITool {

  @Override
  public String getToolName() {
    return "GitHub";
  }

  @Override
  public String getBuildName() {
    String workflow = System.getenv("GITHUB_WORKFLOW");
    String jobName = System.getenv("GITHUB_JOB");
    return workflow + " / " + jobName;
  }

  @Override
  public String getBuildNumber() {
    return System.getenv("GITHUB_RUN_NUMBER");
  }
}
