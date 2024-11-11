package com.exampleservice.junit5;

import com.exampleservice.pageobjects.HomePage;
import com.exampleservice.pageobjects.NavBar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class NavBarTest extends BaseTest {
  private NavBar navBar;

  @BeforeEach
  public void navigate(TestInfo testinfo) {
    new HomePage(driver).open();
    navBar = new NavBar(driver);
  }

  @Test
  void navigateToSignIn() {
    Assertions.assertDoesNotThrow(navBar::navigateToLogin);
  }

  @Test
  void navigateToHome() {
    navBar.navigateToLogin();
    Assertions.assertDoesNotThrow(navBar::navigateToHome);
  }

  @Test
  void navigateToContact() {
    Assertions.assertDoesNotThrow(navBar::navigateToContact);
  }

  @Test
  @Disabled
  void navigateToRentals() {
    Assertions.assertDoesNotThrow(navBar::navigateToRentals);
  }

  @Test
  @Disabled
  void navigateToHandTools() {
    Assertions.assertDoesNotThrow(navBar::navigateToHandTools);
  }

  @Test
  @Disabled
  void navigateToPowerTools() {
    Assertions.assertDoesNotThrow(navBar::navigateToPowerTools);
  }

  @Test
  @Disabled
  void navigateToSpecialTools() {
    Assertions.assertDoesNotThrow(navBar::navigateToSpecialTools);
  }
}
