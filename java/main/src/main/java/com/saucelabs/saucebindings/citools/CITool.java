package com.saucelabs.saucebindings.citools;

public interface CITool {
  String getToolName();

  String getBuildName();

  String getBuildNumber();
}
