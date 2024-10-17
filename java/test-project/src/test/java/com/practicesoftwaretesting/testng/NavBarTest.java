package com.practicesoftwaretesting.testng;

import com.practicesoftwaretesting.pageobjects.HomePage;
import com.practicesoftwaretesting.pageobjects.NavBar;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NavBarTest extends BaseTest {

  @BeforeMethod
  public void navigate() {
    new HomePage(driver.get()).open();
  }

  @Test(groups = {"navigation"})
  public void navigateToLogin() {
    try {
      new NavBar(driver.get()).navigateToLogin();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test(groups = {"navigation"})
  public void navigateToHome() {
    NavBar navBar = new NavBar(driver.get());
    navBar.navigateToLogin();

    try {
      new NavBar(driver.get()).navigateToHome();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test(groups = {"navigation"})
  public void navigateToContact() {
    try {
      new NavBar(driver.get()).navigateToContact();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test(enabled = false, groups = {"navigation"})
  public void navigateToRentals() {
    try {
      new NavBar(driver.get()).navigateToRentals();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test(enabled = false, groups = {"navigation"})
  public void navigateToHandTools() {
    try {
      new NavBar(driver.get()).navigateToHandTools();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test(enabled = false, groups = {"navigation"})
  public void navigateToPowerTools() {
    try {
      new NavBar(driver.get()).navigateToPowerTools();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test(enabled = false, groups = {"navigation"})
  public void navigateToSpecialTools() {
    try {
      new NavBar(driver.get()).navigateToSpecialTools();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }
}
