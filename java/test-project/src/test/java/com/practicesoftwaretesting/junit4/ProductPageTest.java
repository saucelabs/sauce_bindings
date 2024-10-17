package com.practicesoftwaretesting.junit4;

import com.practicesoftwaretesting.pageobjects.LoginPage;
import com.practicesoftwaretesting.pageobjects.NavBar;
import com.practicesoftwaretesting.pageobjects.ProductPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;

public class ProductPageTest extends BaseTest {
  ProductPage productPage;

  @Before
  public void navigate() {
    productPage = new ProductPage(driver);
    productPage.open();
    ((JavascriptExecutor) driver).executeScript("sessionStorage.clear()");
  }

  @Test
  @Category(ContentTests.class)
  public void pageCheck() {
    Assertions.assertDoesNotThrow(() -> productPage.validateContent("Add to cart"));
  }

  @Category(ContentTests.class)
  @Test
  public void hasNavBar() {
    try {
      new NavBar(driver).isDisplayed();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  @Category(FeatureTests.class)
  public void addToCart() {
    productPage.addToCart();
    NavBar navBar = new NavBar(driver);
    Assert.assertEquals(1, (navBar.getCartQuantity()));
  }

  @Test
  @Category(FeatureTests.class)
  public void addMoreToCart() {
    productPage.increaseQuantity();
    productPage.increaseQuantity();
    productPage.addToCart();
    NavBar navBar = new NavBar(driver);
    Assert.assertEquals(3, (navBar.getCartQuantity()));
  }

  @Test
  @Category(FeatureTests.class)
  public void addFewerToCart() {
    productPage.increaseQuantity();
    productPage.increaseQuantity();
    productPage.decreaseQuantity();
    productPage.addToCart();
    NavBar navBar = new NavBar(driver);
    Assert.assertEquals(2, (navBar.getCartQuantity()));
  }

  @Test
  @Category(FeatureTests.class)
  public void cannotAddToFavoritesLoggedOut() {
    productPage.addToFavorites();
  }

  @Test
  @Category(FeatureTests.class)
  @Ignore("Can't manage the test data for this test")
  public void addToFavoritesLoggedIn() {
    String productUrl = driver.getCurrentUrl();
    NavBar navBar = new NavBar(driver);
    navBar.navigateToLogin();
    LoginPage loginPage = new LoginPage(driver);
    loginPage.loginCustomer();
    driver.get(productUrl);
    productPage.addToFavorites();
  }
}
