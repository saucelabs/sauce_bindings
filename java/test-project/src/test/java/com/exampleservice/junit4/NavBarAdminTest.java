package com.exampleservice.junit4;

import com.exampleservice.pageobjects.admin.DashboardPage;
import com.exampleservice.pageobjects.admin.NavBarAdmin;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriverException;

public class NavBarAdminTest extends BaseTest {
  private NavBarAdmin navBarAdmin;

  @Before
  public void navigate() {
    new DashboardPage(driver).open();
    navBarAdmin = new NavBarAdmin(driver);
  }

  @Test
  @Category(NavigationTests.class)
  public void navigateToDashboard() {
    navBarAdmin.navigateToProducts();
    try {
      navBarAdmin.navigateToDashboard();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  @Category(NavigationTests.class)
  public void navigateToBrands() {
    try {
      navBarAdmin.navigateToBrands();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  @Category(NavigationTests.class)
  public void navigateToCategories() {
    try {
      navBarAdmin.navigateToCategories();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  @Category(NavigationTests.class)
  public void navigateToProducts() {
    try {
      navBarAdmin.navigateToProducts();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  @Category(NavigationTests.class)
  public void navigateToOrders() {
    try {
      navBarAdmin.navigateToOrders();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  @Category(NavigationTests.class)
  public void navigateToUsers() {
    try {
      navBarAdmin.navigateToUsers();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  @Category(NavigationTests.class)
  public void navigateToMessages() {
    try {
      navBarAdmin.navigateToMessages();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  @Category(NavigationTests.class)
  public void navigateToSettings() {
    try {
      navBarAdmin.navigateToSettings();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }
}
