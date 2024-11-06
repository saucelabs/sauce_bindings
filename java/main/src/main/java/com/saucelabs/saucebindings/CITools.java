package com.saucelabs.saucebindings;

import com.google.common.collect.ImmutableMap;
import com.saucelabs.saucebindings.citools.Bamboo;
import com.saucelabs.saucebindings.citools.CITool;
import com.saucelabs.saucebindings.citools.CircleCI;
import com.saucelabs.saucebindings.citools.DefaultTool;
import com.saucelabs.saucebindings.citools.GitHub;
import com.saucelabs.saucebindings.citools.GitLab;
import com.saucelabs.saucebindings.citools.Jenkins;
import com.saucelabs.saucebindings.citools.TeamCity;
import com.saucelabs.saucebindings.citools.TravisCI;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Determines the information a test needs based on what CI Tool is being used. It does not need to
 * be initialized.
 */
public abstract class CITools {
  private static CITool ciTool;

  /** Map of CI Tools and what Environment variable, if present, indicates its usage. */
  public static final Map<String, Supplier<CITool>> KNOWN_TOOLS =
      ImmutableMap.of(
          "bamboo_agentId", Bamboo::new,
          "CIRCLE_JOB", CircleCI::new,
          "GITHUB_SHA", GitHub::new,
          "CI", GitLab::new,
          "JENKINS_HOME", Jenkins::new,
          "TEAMCITY_PROJECT_NAME", TeamCity::new,
          "TRAVIS_JOB_ID", TravisCI::new);

  public static String getCiToolName() {
    return getCiTool().getClientPlatform();
  }

  /**
   * The default build name based on CI Tool.
   *
   * @return the constant name of the build
   */
  public static String getBuildName() {
    return getCiTool().getBuildName();
  }

  /**
   * The default build number based on CI Tool.
   *
   * @return the variable number of the current build
   */
  public static String getBuildNumber() {
    return getCiTool().getBuildNumber();
  }

  private static CITool getCiTool() {
    if (ciTool != null) {
      return ciTool;
    } else if (SystemManager.get("SAUCE_CLIENT_PLATFORM") != null) {
      // Override the CI Tool lookup when this environment variable is set.
      return new DefaultTool();
    } else {
      for (Map.Entry<String, Supplier<CITool>> tool : KNOWN_TOOLS.entrySet()) {
        if (System.getenv(tool.getKey()) != null) {
          ciTool = tool.getValue().get();
          return ciTool;
        }
      }
      ciTool = new DefaultTool();
      return ciTool;
    }
  }
}
