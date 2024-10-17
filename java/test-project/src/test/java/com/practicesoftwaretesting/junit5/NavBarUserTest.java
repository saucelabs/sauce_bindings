package com.practicesoftwaretesting.junit5;

import com.practicesoftwaretesting.pageobjects.user.AccountPage;
import com.practicesoftwaretesting.pageobjects.user.NavBarUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class NavBarUserTest extends BaseTest {
  private NavBarUser navBarCustomer;

  @BeforeEach
  public void navigate(TestInfo testinfo) {
    new AccountPage(driver).open();
    navBarCustomer = new NavBarUser(driver);
  }

  @Test
  @Tag("navigation")
  void navigateToAccount() {
    Assertions.assertDoesNotThrow(navBarCustomer::navigateToAccount);
  }

  @Test
  @Tag("navigation")
  void navigateToFavorite() {
    Assertions.assertDoesNotThrow(navBarCustomer::navigateToFavorites);
  }

  @Test
  @Tag("navigation")
  void navigateToProfile() {
    Assertions.assertDoesNotThrow(navBarCustomer::navigateToProfile);
  }

  @Test
  @Tag("navigation")
  void navigateToInvoices() {
    Assertions.assertDoesNotThrow(navBarCustomer::navigateToInvoices);
  }

  @Test
  @Tag("navigation")
  void navigateToMessages() {
    Assertions.assertDoesNotThrow(navBarCustomer::navigateToMessages);
  }
}
