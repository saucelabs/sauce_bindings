package com.saucelabs.saucebindings.citools;

public class TravisCI implements CITool {

  @Override
  public String getToolName() {
    return "Travis CI";
  }

  @Override
  public String getBuildName() {
    return System.getenv("TRAVIS_JOB_NAME");
  }

  @Override
  public String getBuildNumber() {
    return System.getenv("TRAVIS_JOB_NUMBER");
  }
}
