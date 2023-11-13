package com.saucelabs.saucebindings.junit5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class AddHooksTest extends SauceBaseTest {
  Boolean working = null;

  @BeforeEach
  public void setUp(TestInfo testinfo) {
    working = true;
    super.setUp(testinfo);
  }

  @AfterEach
  public void teardown() {
    System.out.println("Can do something in teardown before test watcher methods");
  }

  @Test
  public void useCustomOptions() {
    Assertions.assertTrue(working);
  }
}
