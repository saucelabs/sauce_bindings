package com.exampleservice.junit5;

import com.exampleservice.pageobjects.LoginPage;
import com.exampleservice.pageobjects.NavBar;
import com.exampleservice.pageobjects.ProductPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.JavascriptExecutor;

public class ProductPageTest extends BaseTest {
  ProductPage productPage;

  @BeforeEach
  public void navigate(TestInfo testinfo) {
    productPage = new ProductPage(driver);
    productPage.open();
    ((JavascriptExecutor) driver).executeScript("sessionStorage.clear()");
  }

  @Test
  @Tag("content")
  void pageCheck() {
    Assertions.assertDoesNotThrow(() -> productPage.validateContent("Add to cart"));
  }

  @Tag("content")
  @Test
  void hasNavBar() {
    Assertions.assertDoesNotThrow(() -> new NavBar(driver).isDisplayed());
  }

  @Test
  @Tag("feature")
  void addToCart() {
    productPage.addToCart();
    NavBar navBar = new NavBar(driver);
    Assertions.assertEquals(1, (navBar.getCartQuantity()));
  }

  @Test
  @Tag("feature")
  void addMoreToCart() {
    productPage.increaseQuantity();
    productPage.increaseQuantity();
    productPage.addToCart();
    NavBar navBar = new NavBar(driver);
    Assertions.assertEquals(3, (navBar.getCartQuantity()));
  }

  @Test
  @Tag("feature")
  void addFewerToCart() {
    productPage.increaseQuantity();
    productPage.increaseQuantity();
    productPage.decreaseQuantity();
    productPage.addToCart();
    NavBar navBar = new NavBar(driver);
    Assertions.assertEquals(2, (navBar.getCartQuantity()));
  }

  @Test
  @Tag("feature")
  void cannotAddToFavoritesLoggedOut() {
    productPage.addToFavorites();
  }

  @Test
  @Tag("feature")
  @Disabled("Can't manage the test data for this test")
  void addToFavoritesLoggedIn() {
    String productUrl = driver.getCurrentUrl();
    NavBar navBar = new NavBar(driver);
    navBar.navigateToLogin();
    LoginPage loginPage = new LoginPage(driver);
    loginPage.loginCustomer();
    driver.get(productUrl);
    productPage.addToFavorites();
  }
}
