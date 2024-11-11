package com.exampleservice.junit5;

import com.exampleservice.pageobjects.user.AccountPage;
import com.exampleservice.pageobjects.NavBar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class AccountPageTest extends BaseTest {
  private AccountPage accountPage;

  @BeforeEach
  public void navigate() {
    accountPage = new AccountPage(driver);
    accountPage.open();
  }

  @Test
  @Tag("content")
  void pageCheck() {
    Assertions.assertDoesNotThrow(() -> accountPage.validateContent("manage your profile, favorites and orders"));
  }

  @Test
  @Tag("content")
  void hasNavBar() {
    Assertions.assertDoesNotThrow(() -> new NavBar(driver).isDisplayed());
  }

  @Test
  @Tag("navigation")
  void navigateToFavorites() {
    Assertions.assertDoesNotThrow(() -> accountPage.navigateToFavorites());
  }

  @Test
  @Tag("navigation")
  void navigateToProfile() {
    Assertions.assertDoesNotThrow(() -> accountPage.navigateToProfile());
  }

  @Test
  @Tag("navigation")
  void navigateToInvoices() {
    Assertions.assertDoesNotThrow(() -> accountPage.navigateToInvoices());
  }

  @Test
  @Tag("navigation")
  void navigateToMessages() {
    Assertions.assertDoesNotThrow(() -> accountPage.navigateToMessages());
  }
}
