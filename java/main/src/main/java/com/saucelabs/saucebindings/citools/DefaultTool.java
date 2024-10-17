package com.saucelabs.saucebindings.citools;

import com.saucelabs.saucebindings.SystemManager;

public class DefaultTool implements CITool {

  @Override
  public String getToolName() {
    String toolName = SystemManager.get("SAUCE_TOOL_NAME");
    return toolName != null ? toolName : "Unknown";
  }

  @Override
  public String getBuildName() {
    String buildName = SystemManager.get("SAUCE_BUILD_NAME");
    return buildName != null ? buildName : "Undefined Build Name";
  }

  @Override
  public String getBuildNumber() {
    String buildNumber = SystemManager.get("SAUCE_BUILD_NUMBER");
    return buildNumber != null ? buildNumber : String.valueOf(System.currentTimeMillis());
  }
}
