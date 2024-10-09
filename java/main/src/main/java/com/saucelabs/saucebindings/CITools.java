package com.saucelabs.saucebindings;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

/**
 * Determines the information a test needs based on what CI Tool is being used. It does not need to
 * be initialized.
 */
public abstract class CITools {
  private static String ciToolName;

  /** Map of CI Tools and what Environment variable, if present, indicates its usage. */
  public static final Map<String, String> KNOWN_TOOLS =
      ImmutableMap.of(
          "Bamboo", "bamboo_agentId",
          "Circle", "CIRCLE_JOB",
          "Github", "GITHUB_SHA",
          "GitLab", "CI",
          "Jenkins", "JENKINS_HOME",
          "TeamCity", "TEAMCITY_PROJECT_NAME",
          "Travis", "TRAVIS_JOB_ID");

  /** The Build Name and Build Number values to properly differentiate test runs. */
  public static final Map<String, ImmutableList<String>> BUILD_VALUES =
      ImmutableMap.of(
          "Bamboo", ImmutableList.of("bamboo_shortJobName", "bamboo_buildNumber"),
          "Circle", ImmutableList.of("CIRCLE_JOB", "CIRCLE_BUILD_NUM"),
          "Github", ImmutableList.of("GITHUB_JOB", "GITHUB_RUN_NUMBER"),
          "GitLab", ImmutableList.of("CI_JOB_NAME", "CI_JOB_ID"),
          "Jenkins", ImmutableList.of("BUILD_NAME", "BUILD_NUMBER"),
          "TeamCity", ImmutableList.of("TEAMCITY_PROJECT_NAME", "BUILD_NUMBER"),
          "Travis", ImmutableList.of("TRAVIS_JOB_NAME", "TRAVIS_JOB_NUMBER"));

  public static String getCiToolName() {
    if (ciToolName == null) {
      for (Map.Entry<String, String> tool : KNOWN_TOOLS.entrySet()) {
        if (SystemManager.get(tool.getValue()) != null) {
          ciToolName = tool.getKey();
          return ciToolName;
        }
      }
    }
    return ciToolName == null ? "unknown" : ciToolName;
  }

  /**
   * The default build name based on CI Tool.
   *
   * @return the constant name of the build
   */
  public static String getBuildName() {
    if (!getCiToolName().equalsIgnoreCase("unknown")) {
      String buildNameKey = BUILD_VALUES.get(getCiToolName()).get(0);
      return SystemManager.get(buildNameKey);
    }
    return "Default Build Name";
  }

  /**
   * The default build number based on CI Tool.
   *
   * @return the variable number of the current build
   */
  public static String getBuildNumber() {
    if (!getCiToolName().equalsIgnoreCase("unknown")) {
      String buildNumberKey = BUILD_VALUES.get(getCiToolName()).get(1);
      return SystemManager.get(buildNumberKey);
    }
    return String.valueOf(System.currentTimeMillis());
  }
}
