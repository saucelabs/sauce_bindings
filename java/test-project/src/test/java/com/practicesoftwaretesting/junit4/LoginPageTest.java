package com.practicesoftwaretesting.junit4;

import com.practicesoftwaretesting.pageobjects.LoginPage;
import com.practicesoftwaretesting.pageobjects.NavBar;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;

public class LoginPageTest extends BaseTest {
  private LoginPage loginPage;

  @Before
  public void navigate() {
    loginPage = new LoginPage(driver);
    loginPage.open();
  }

  @Test
  @Category(ContentTests.class)
  public void pageCheck() {
    Assertions.assertDoesNotThrow(() -> loginPage.validateContent("Not yet an account?"));
  }

  @Test
  @Category(ContentTests.class)
  public void hasNavBar() {
    try {
      new NavBar(driver).isDisplayed();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  @Category(FeatureTests.class)
  public void successfulLogin() {
    try {
      loginPage.loginCustomer();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  @Category(FeatureTests.class)
  public void unsuccessfulLogin() {
    try {
      loginPage.login("bad@example.com", "login");
      Assert.fail("This test should time out");
    } catch (TimeoutException ignored) {
    }
  }

  public void forgotPassword() {
    // This requires new PO
  }

  public void register() {
    // This requires new PO
  }
}
