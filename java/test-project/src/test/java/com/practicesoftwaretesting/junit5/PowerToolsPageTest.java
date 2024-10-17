package com.practicesoftwaretesting.junit5;

import com.practicesoftwaretesting.pageobjects.NavBar;
import com.practicesoftwaretesting.pageobjects.PowerToolsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class PowerToolsPageTest extends BaseTest {
  private PowerToolsPage powerToolsPage;

  @BeforeEach
  public void navigate(TestInfo testinfo) {
    powerToolsPage = new PowerToolsPage(driver);
    powerToolsPage.open();
  }

  @Test
  @Tag("content")
  void pageCheck() {
    Assertions.assertDoesNotThrow(() -> powerToolsPage.validateContent("Grinder"));
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
