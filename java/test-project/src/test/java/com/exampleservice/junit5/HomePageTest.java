package com.exampleservice.junit5;

import com.exampleservice.pageobjects.HomePage;
import com.exampleservice.pageobjects.NavBar;
import com.exampleservice.pageobjects.ProductPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

public class HomePageTest extends BaseTest {
  private HomePage homePage;

  @BeforeEach
  public void navigate(TestInfo testinfo) {
    homePage = new HomePage(driver);
    homePage.open();
  }

  @Test
  @Tag("content")
  void pageCheck() {
    Assertions.assertDoesNotThrow(() -> homePage.validateContent("Combination Pliers"));
  }

  @Test
  @Tag("content")
  void hasNavBar() {
    Assertions.assertDoesNotThrow(() -> new NavBar(driver).isDisplayed());
  }

  @Test
  @Tag("feature")
  void viewInStockProduct() {
    homePage.viewInStockProduct();
    ProductPage productPage = new ProductPage(driver);
    Assertions.assertTrue(productPage.isInStock());
  }

  @Test
  @Tag("feature")
  void viewOutOfStockProduct() {
    homePage.viewOutOfStockProduct();
    ProductPage productPage = new ProductPage(driver);
    Assertions.assertFalse(productPage.isInStock());
  }

  @Test
  @Tag("feature")
  @Disabled("Not implemented yet")
  void filterByWrench() {}

  @Test
  @Tag("feature")
  @Disabled("Not implemented yet")
  void sortAlphabetical() {}

  @Test
  @Tag("feature")
  @Disabled("Not implemented yet")
  void reverseSortAlphabetical() {}

  @Test
  @Tag("feature")
  @Disabled("Not implemented yet")
  void reverseSortPrice() {}

  @Test
  @Tag("feature")
  @Disabled("Not implemented yet")
  void search() {
    // "Claw"
  }

  @Test
  @Tag("feature")
  void clearSearch() {}
}
