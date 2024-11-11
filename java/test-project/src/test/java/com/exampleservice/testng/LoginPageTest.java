package com.exampleservice.testng;

import com.exampleservice.pageobjects.LoginPage;
import com.exampleservice.pageobjects.NavBar;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

  @BeforeMethod
  public void navigate() {
    new LoginPage(driver.get()).open();
  }

  @Test(groups = {"contents"})
  public void pageCheck() {
    LoginPage loginPage = new LoginPage(driver.get());
    Assertions.assertDoesNotThrow(() -> loginPage.validateContent("Not yet an account?"));
  }

  @Test(groups = {"contents"})
  public void hasNavBar() {
    try {
      new NavBar(driver.get()).isDisplayed();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test(groups = {"features"})
  public void successfulLogin() {
    try {
      LoginPage loginPage = new LoginPage(driver.get());
      loginPage.loginCustomer();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test(groups = {"features"})
  public void unsuccessfulLogin() {
    try {
      LoginPage loginPage = new LoginPage(driver.get());
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
