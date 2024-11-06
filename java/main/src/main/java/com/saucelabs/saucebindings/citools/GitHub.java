package com.saucelabs.saucebindings.citools;

import com.saucelabs.saucebindings.SystemManager;

public class GitHub implements CITool {

  @Override
  public String getClientPlatform() {
    return "GitHub";
  }

  @Override
  public String getBuildName() {
    String buildName = SystemManager.get("SAUCE_BUILD_NAME");
    String workflow = System.getenv("GITHUB_WORKFLOW");
    String jobName = System.getenv("GITHUB_JOB");

    return buildName != null ? buildName : workflow + " / " + jobName;
  }

  @Override
  public String getBuildNumber() {
    String buildNumber = SystemManager.get("SAUCE_BUILD_NUMBER");
    return buildNumber != null ? buildNumber : System.getenv("GITHUB_RUN_NUMBER");
  }
}
