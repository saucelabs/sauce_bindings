package com.exampleservice.junit5;

import com.exampleservice.pageobjects.NavBar;
import com.exampleservice.pageobjects.RentalsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class RentalsPageTest extends BaseTest {
  private RentalsPage rentalsPage;

  @BeforeEach
  public void navigate(TestInfo testinfo) {
    rentalsPage = new RentalsPage(driver);
    rentalsPage.open();
  }

  @Test
  @Tag("content")
  void pageCheck() {
    Assertions.assertDoesNotThrow(() -> rentalsPage.validateContent("Bulldozer"));
  }

  @Test
  @Tag("content")
  void hasNavBar() {
    Assertions.assertDoesNotThrow(() -> new NavBar(driver).isDisplayed());
  }
}
