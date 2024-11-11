package com.exampleservice.testng;

import com.exampleservice.pageobjects.admin.DashboardPage;
import com.exampleservice.pageobjects.admin.NavBarAdmin;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NavBarAdminPageTest extends BaseTest {

  @BeforeMethod
  public void navigate() {
    new DashboardPage(driver.get()).open();
  }

  @Test(groups = {"navigation"})
  public void navigateToDashboard() {
    NavBarAdmin navBarAdmin = new NavBarAdmin(driver.get());
    navBarAdmin.navigateToProducts();

    try {
      navBarAdmin.navigateToDashboard();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test(groups = {"navigation"})
  public void navigateToBrands() {
    try {
      new NavBarAdmin(driver.get()).navigateToBrands();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test(groups = {"navigation"})
  public void navigateToCategories() {
    try {
      new NavBarAdmin(driver.get()).navigateToProducts();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test(groups = {"navigation"})
  public void navigateToProducts() {
    try {
      new NavBarAdmin(driver.get()).navigateToProducts();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test(groups = {"navigation"})
  public void navigateToOrders() {
    try {
      new NavBarAdmin(driver.get()).navigateToOrders();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test(groups = {"navigation"})
  public void navigateToUsers() {
    try {
      new NavBarAdmin(driver.get()).navigateToUsers();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test(groups = {"navigation"})
  public void navigateToMessages() {
    try {
      new NavBarAdmin(driver.get()).navigateToMessages();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test(groups = {"navigation"})
  public void navigateToSettings() {
    try {
      new NavBarAdmin(driver.get()).navigateToSettings();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }
}
