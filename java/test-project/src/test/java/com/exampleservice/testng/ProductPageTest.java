package com.exampleservice.testng;

import com.exampleservice.pageobjects.NavBar;
import com.exampleservice.pageobjects.ProductPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductPageTest extends BaseTest {

  @BeforeMethod
  public void navigate() {
    new ProductPage(driver.get()).open();
    ((JavascriptExecutor) driver.get()).executeScript("sessionStorage.clear()");
  }

  @Test(groups = {"contents"})
  public void pageCheck() {
    ProductPage productPage = new ProductPage(driver.get());
    Assertions.assertDoesNotThrow(() -> productPage.validateContent("Add to cart"));
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
  public void addToCart() {
    new ProductPage(driver.get()).addToCart();

    Assert.assertEquals(1, (new NavBar(driver.get()).getCartQuantity()));
  }

  @Test(groups = {"features"})
  public void addMoreToCart() {
    ProductPage productPage = new ProductPage(driver.get());
    productPage.increaseQuantity();
    productPage.increaseQuantity();
    productPage.addToCart();

    Assert.assertEquals(3, (new NavBar(driver.get()).getCartQuantity()));
  }

  @Test(groups = {"features"})
  public void addFewerToCart() {
    ProductPage productPage = new ProductPage(driver.get());
    productPage.increaseQuantity();
    productPage.increaseQuantity();
    productPage.decreaseQuantity();
    productPage.addToCart();

    Assert.assertEquals(2, (new NavBar(driver.get()).getCartQuantity()));
  }
}
