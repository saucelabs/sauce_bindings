package com.practicesoftwaretesting.junit4;

import com.practicesoftwaretesting.pageobjects.NavBar;
import com.practicesoftwaretesting.pageobjects.user.AccountPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriverException;

public class AccountPageTest extends BaseTest {
  private AccountPage accountPage;

  @Before
  public void navigate() {
    accountPage = new AccountPage(driver);
    accountPage.open();
  }

  @Category(ContentTests.class)
  @Test
  public void pageCheck() {
    Assertions.assertDoesNotThrow(() -> accountPage.validateContent("manage your profile, favorites and orders"));
  }

  @Category(ContentTests.class)
  @Test
  public void hasNavBar() {
    try {
      new NavBar(driver).isDisplayed();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Category(NavigationTests.class)
  @Test
  public void navigateToFavorites() {
    try {
      accountPage.navigateToFavorites();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Category(NavigationTests.class)
  @Test
  public void navigateToProfile() {
    try {
      accountPage.navigateToProfile();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Category(NavigationTests.class)
  @Test
  public void navigateToInvoices() {
    try {
      accountPage.navigateToInvoices();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Category(NavigationTests.class)
  @Test
  public void navigateToMessages() {
    try {
      accountPage.navigateToMessages();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }
}
