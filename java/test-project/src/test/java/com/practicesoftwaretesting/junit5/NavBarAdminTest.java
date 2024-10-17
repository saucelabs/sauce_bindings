package com.practicesoftwaretesting.junit5;

import com.practicesoftwaretesting.pageobjects.admin.DashboardPage;
import com.practicesoftwaretesting.pageobjects.admin.NavBarAdmin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class NavBarAdminTest extends BaseTest {
  private NavBarAdmin navBarAdmin;

  @BeforeEach
  public void navigate(TestInfo testinfo) {
    new DashboardPage(driver).open();
    navBarAdmin = new NavBarAdmin(driver);
  }

  @Test
  @Tag("navigation")
  void navigateToDashboard() {
    navBarAdmin.navigateToProducts();
    Assertions.assertDoesNotThrow(navBarAdmin::navigateToDashboard);
  }

  @Test
  @Tag("navigation")
  void navigateToBrands() {
    Assertions.assertDoesNotThrow(navBarAdmin::navigateToBrands);
  }

  @Test
  @Tag("navigation")
  void navigateToCategories() {
    Assertions.assertDoesNotThrow(navBarAdmin::navigateToCategories);
  }

  @Test
  @Tag("navigation")
  void navigateToProducts() {
    Assertions.assertDoesNotThrow(navBarAdmin::navigateToProducts);
  }

  @Test
  @Tag("navigation")
  void navigateToOrders() {
    Assertions.assertDoesNotThrow(navBarAdmin::navigateToOrders);
  }

  @Test
  @Tag("navigation")
  void navigateToUsers() {
    Assertions.assertDoesNotThrow(navBarAdmin::navigateToUsers);
  }

  @Test
  @Tag("navigation")
  void navigateToMessages() {
    Assertions.assertDoesNotThrow(navBarAdmin::navigateToMessages);
  }

  @Test
  @Tag("navigation")
  void navigateToSettings() {
    Assertions.assertDoesNotThrow(navBarAdmin::navigateToSettings);
  }
}
