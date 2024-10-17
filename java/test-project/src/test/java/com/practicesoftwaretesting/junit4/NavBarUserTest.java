package com.practicesoftwaretesting.junit4;

import com.practicesoftwaretesting.pageobjects.user.AccountPage;
import com.practicesoftwaretesting.pageobjects.user.NavBarUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.WebDriverException;

public class NavBarUserTest extends BaseTest {
  private NavBarUser navBarCustomer;

  @Before
  public void navigate() {
    new AccountPage(driver).open();
    navBarCustomer = new NavBarUser(driver);
  }

  @Test
  @Category(NavigationTests.class)
  public void navigateToAccount() {
    try {
      navBarCustomer.navigateToAccount();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  @Category(NavigationTests.class)
  public void navigateToFavorite() {
    try {
      navBarCustomer.navigateToFavorites();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  @Category(NavigationTests.class)
  public void navigateToProfile() {
    try {
      navBarCustomer.navigateToProfile();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  @Category(NavigationTests.class)
  public void navigateToInvoices() {
    try {
      navBarCustomer.navigateToInvoices();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  @Category(NavigationTests.class)
  public void navigateToMessages() {
    try {
      navBarCustomer.navigateToMessages();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }
}
