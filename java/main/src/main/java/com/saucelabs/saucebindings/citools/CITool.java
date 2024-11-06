package com.saucelabs.saucebindings.citools;

public interface CITool {
  String getClientPlatform();

  String getBuildName();

  String getBuildNumber();
}
