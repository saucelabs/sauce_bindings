package com.saucelabs.saucebindings.citools;

import com.saucelabs.saucebindings.SystemManager;

public class GitLab implements CITool {

  @Override
  public String getClientPlatform() {
    return "GitLab";
  }

  @Override
  public String getBuildName() {
    String buildName = SystemManager.get("SAUCE_BUILD_NAME");
    return buildName != null ? buildName : System.getenv("CI_JOB_NAME");
  }

  @Override
  public String getBuildNumber() {
    String buildNumber = SystemManager.get("SAUCE_BUILD_NUMBER");
    return buildNumber != null ? buildNumber : System.getenv("CI_JOB_ID");
  }
}
