package com.exampleservice.junit5;

import com.exampleservice.pageobjects.HandToolsPage;
import com.exampleservice.pageobjects.NavBar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class HandToolsPageTest extends BaseTest {
  private HandToolsPage handToolsPage;

  @BeforeEach
  public void navigate() {
    handToolsPage = new HandToolsPage(driver);
    handToolsPage.open();
  }

  @Test
  @Tag("content")
  void pageCheck() {
    Assertions.assertDoesNotThrow(() -> handToolsPage.validateContent("Hand Saw"));
  }

  @Test
  @Tag("content")
  void hasNavBar() {
    Assertions.assertDoesNotThrow(() -> new NavBar(driver).isDisplayed());
  }

  @Test
  @Tag("feature")
  @Disabled("Not implemented yet")
  void filterCategory() {}

  @Test
  @Tag("feature")
  @Disabled("Not implemented yet")
  void filterBrand() {}

  @Test
  @Tag("feature")
  @Disabled("Not implemented yet")
  void sort() {}

  @Test
  @Tag("feature")
  @Disabled("Not implemented yet")
  void selectProduct() {}
}
