package com.exampleservice.junit4;

import com.exampleservice.pageobjects.HomePage;
import com.exampleservice.pageobjects.NavBar;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriverException;

public class NavBarTest extends BaseTest {
  private NavBar navBar;

  @Before
  public void navigate() {
    new HomePage(driver).open();
    navBar = new NavBar(driver);
  }

  @Test
  public void navigateToSignIn() {
    try {
      navBar.navigateToLogin();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  public void navigateToHome() {
    navBar.navigateToLogin();
    try {
      navBar.navigateToHome();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  public void navigateToContact() {
    try {
      navBar.navigateToContact();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  @Ignore
  public void navigateToRentals() {
    try {
      navBar.navigateToRentals();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  @Ignore
  public void navigateToHandTools() {
    try {
      navBar.navigateToHandTools();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  @Ignore
  public void navigateToPowerTools() {
    try {
      navBar.navigateToPowerTools();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  @Ignore
  public void navigateToSpecialTools() {
    try {
      navBar.navigateToSpecialTools();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }
}
