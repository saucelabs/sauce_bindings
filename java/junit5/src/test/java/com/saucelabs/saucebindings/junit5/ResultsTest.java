package com.saucelabs.saucebindings.junit5;

import com.saucelabs.saucebindings.SauceSession;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.RegisterExtension;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ResultsTest {
  private SauceSession session;

  @RegisterExtension static SauceBindingsExtension sauceExtension = new SauceBindingsExtension();

  @Test
  public void abortedTest(SauceSession session) {
    this.session = session;
    Assertions.assertNotNull(session);
    Assertions.assertNotNull(session.getDriver());
    Assumptions.assumeTrue(
        false, "This assumption is false so it will abort; extension still needs to stop session");
  }

  @AfterAll
  public void after() {
    Assertions.assertNotNull(session);
    Assertions.assertNull(session.getDriver());
  }
}
