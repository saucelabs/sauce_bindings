package com.practicesoftwaretesting.junit5;

import com.practicesoftwaretesting.pageobjects.NavBar;
import com.practicesoftwaretesting.pageobjects.SpecialToolsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class SpecialToolsPageTest extends BaseTest {
  private SpecialToolsPage specialToolsPage;

  @BeforeEach
  public void navigate(TestInfo testinfo) {
    specialToolsPage = new SpecialToolsPage(driver);
    specialToolsPage.open();
  }

  @Test
  @Tag("content")
  void specialToolsPageCheck() {
    Assertions.assertDoesNotThrow(() -> specialToolsPage.validateContent("no products"));
  }

  @Test
  @Tag("content")
  void specialToolsHasNavBar() {
    Assertions.assertDoesNotThrow(() -> new NavBar(driver).isDisplayed());
  }
}
