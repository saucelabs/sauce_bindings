package com.exampleservice.testng;

import com.exampleservice.pageobjects.NavBar;
import com.exampleservice.pageobjects.user.AccountPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AccountsPageTest extends BaseTest {
  @BeforeMethod
  public void navigate() {
    new AccountPage(driver.get()).open();
  }

  @Test(groups = {"contents"})
  public void pageCheck() {
    AccountPage accountPage = new AccountPage(driver.get());
    Assertions.assertDoesNotThrow(() -> accountPage.validateContent("manage your profile, favorites and orders"));
  }

  @Test(groups = {"contents"})
  public void hasNavBar() {
    try {
      new NavBar(driver.get()).isDisplayed();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test(groups = {"navigation"})
  public void navigateToFavorites() {
    try {
      AccountPage accountPage = new AccountPage(driver.get());
      accountPage.navigateToFavorites();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test(groups = {"navigation"})
  public void navigateToProfile() {
    try {
      AccountPage accountPage = new AccountPage(driver.get());
      accountPage.navigateToProfile();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test(groups = {"navigation"})
  public void navigateToInvoices() {
    try {
      AccountPage accountPage = new AccountPage(driver.get());
      accountPage.navigateToInvoices();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test(groups = {"navigation"})
  public void navigateToMessages() {
    try {
      AccountPage accountPage = new AccountPage(driver.get());
      accountPage.navigateToMessages();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }
}
