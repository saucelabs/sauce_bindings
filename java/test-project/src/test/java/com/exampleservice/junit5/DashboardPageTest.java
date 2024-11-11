package com.exampleservice.junit5;

import com.exampleservice.pageobjects.NavBar;
import com.exampleservice.pageobjects.admin.DashboardPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class DashboardPageTest extends BaseTest {
  private DashboardPage dashboardPage;

  @BeforeEach
  public void navigate() {
    dashboardPage = new DashboardPage(driver);
    dashboardPage.open();
  }

  @Test
  @Tag("content")
  void pageCheck() {
    Assertions.assertDoesNotThrow(() -> dashboardPage.validateContent("Latest orders"));
  }

  @Test
  @Tag("content")
  void hasNavBar() {
    Assertions.assertDoesNotThrow(() -> new NavBar(driver).isDisplayed());
  }

  @Test
  @Tag("feature")
  void editInvoice() {
    // Requires new "Orders" PO that can update status
  }
}
