package com.exampleservice.testng;

import com.exampleservice.pageobjects.HomePage;
import com.exampleservice.pageobjects.NavBar;
import com.exampleservice.pageobjects.ProductPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

  @BeforeMethod
  public void navigate() {
    new HomePage(driver.get()).open();
  }

  @Test(groups = {"contents"})
  public void pageCheck() {
    HomePage homePage = new HomePage(driver.get());
    Assertions.assertDoesNotThrow(() -> homePage.validateContent("Combination Pliers"));
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
  public void viewInStockProduct() {
    HomePage homePage = new HomePage(driver.get());
    homePage.viewInStockProduct();
    ProductPage productPage = new ProductPage(driver.get());
    Assert.assertTrue(productPage.isInStock());
  }

  @Test(groups = {"features"})
  public void viewOutOfStockProduct() {
    HomePage homePage = new HomePage(driver.get());
    homePage.viewOutOfStockProduct();
    ProductPage productPage = new ProductPage(driver.get());
    Assert.assertFalse(productPage.isInStock());
  }

  @Test(
      enabled = false,
      groups = {"features"})
  public void filterByWrench() {}

  @Test(
      enabled = false,
      groups = {"features"})
  public void sortAlphabetical() {}

  @Test(
      enabled = false,
      groups = {"features"})
  public void reverseSortAlphabetical() {}

  @Test(
      enabled = false,
      groups = {"features"})
  public void reverseSortPrice() {}

  @Test(
      enabled = false,
      groups = {"features"})
  public void search() {
    // "Claw"
  }

  @Test(
      enabled = false,
      groups = {"features"})
  public void clearSearch() {}
}
