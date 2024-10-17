package com.practicesoftwaretesting.testng;

import com.practicesoftwaretesting.pageobjects.user.AccountPage;
import com.practicesoftwaretesting.pageobjects.user.NavBarUser;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NavBarUserTest extends BaseTest {

  @BeforeMethod
  public void navigate() {
    new AccountPage(driver.get()).open();
  }

  @Test(groups = {"navigation"})
  public void navigateToAccount() {
    try {
      new NavBarUser(driver.get()).navigateToAccount();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test(groups = {"navigation"})
  public void navigateToFavorites() {
    try {
      new NavBarUser(driver.get()).navigateToFavorites();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test(groups = {"navigation"})
  public void navigateToProfile() {
    try {
      new NavBarUser(driver.get()).navigateToProfile();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test(groups = {"navigation"})
  public void navigateToInvoices() {
    try {
      new NavBarUser(driver.get()).navigateToInvoices();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test(groups = {"navigation"})
  public void navigateToMessages() {
    try {
      new NavBarUser(driver.get()).navigateToMessages();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }
}
