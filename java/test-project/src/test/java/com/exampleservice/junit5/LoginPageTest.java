package com.exampleservice.junit5;

import com.exampleservice.pageobjects.LoginPage;
import com.exampleservice.pageobjects.NavBar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.TimeoutException;

public class LoginPageTest extends BaseTest {
  private LoginPage loginPage;

  @BeforeEach
  public void navigate(TestInfo testinfo) {
    loginPage = new LoginPage(driver);
    loginPage.open();
  }

  @Test
  @Tag("content")
  void pageCheck() {
    Assertions.assertDoesNotThrow(() -> loginPage.validateContent("Not yet an account?"));
  }

  @Test
  @Tag("content")
  void hasNavBar() {
    Assertions.assertDoesNotThrow(() -> new NavBar(driver).isDisplayed());
  }

  @Test
  @Tag("feature")
  void successfulLogin() {
    Assertions.assertDoesNotThrow(loginPage::loginCustomer);
  }

  @Test
  @Tag("feature")
  void unsuccessfulLogin() {
    Assertions.assertThrows(
        TimeoutException.class, () -> loginPage.login("bad@example.com", "login"));
  }

  void forgotPassword() {
    // This requires new PO
  }

  void register() {
    // This requires new PO
  }
}
