package com.saucelabs.saucebindings.junit5;

import com.saucelabs.saucebindings.SauceSession;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ResultsTest {
  private SauceSession session;

  @RegisterExtension static SauceBindingsExtension sauceExtension = new SauceBindingsExtension();

  @Test
  public void abortedTest(SauceSession session, WebDriver driver) {
    this.session = session;
    Assumptions.assumeTrue(
        false, "Test will only run when sauce is disabled with sauce.disabled system property'");
  }

  @AfterAll
  public void after() {
    Assertions.assertNotNull(session);
    Assertions.assertNull(session.getDriver());
  }
}
