package com.practicesoftwaretesting.junit5;

import com.practicesoftwaretesting.pageobjects.NavBar;
import com.practicesoftwaretesting.pageobjects.user.InvoicesPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class InvoicesPageTest extends BaseTest {
  private InvoicesPage invoicesPage;

  @BeforeEach
  public void navigate() {
    invoicesPage = new InvoicesPage(driver);
    invoicesPage.open();
  }

  @Test
  @Tag("content")
  void invoicesPageCheck() {
    Assertions.assertDoesNotThrow(() -> invoicesPage.validateContent("Invoice Number"));
  }

  @Test
  @Tag("content")
  void hasNavBar() {
    Assertions.assertDoesNotThrow(() -> new NavBar(driver).isDisplayed());
  }

  @Test
  @Tag("feature")
  void selectInvoice() {
    Assertions.assertDoesNotThrow(() -> invoicesPage.viewRandomInvoice());
  }
}
