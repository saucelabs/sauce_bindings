package com.practicesoftwaretesting.junit4;

import com.practicesoftwaretesting.pageobjects.HomePage;
import com.practicesoftwaretesting.pageobjects.NavBar;
import com.practicesoftwaretesting.pageobjects.ProductPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriverException;

public class HomePageTest extends BaseTest {
  private HomePage homePage;

  @Before
  public void navigate() {
    homePage = new HomePage(driver);
    homePage.open();
  }

  @Test
  @Category(ContentTests.class)
  public void pageCheck() {
    Assertions.assertDoesNotThrow(() -> homePage.validateContent("Combination Pliers"));
  }

  @Test
  @Category(ContentTests.class)
  public void hasNavBar() {
    try {
      new NavBar(driver).isDisplayed();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  @Category(FeatureTests.class)
  public void viewInStockProduct() {
    homePage.viewInStockProduct();
    ProductPage productPage = new ProductPage(driver);
    Assert.assertTrue(productPage.isInStock());
  }

  @Test
  @Category(FeatureTests.class)
  public void viewOutOfStockProduct() {
    homePage.viewOutOfStockProduct();
    ProductPage productPage = new ProductPage(driver);
    Assert.assertFalse(productPage.isInStock());
  }

  @Test
  @Category(FeatureTests.class)
  @Ignore("Not implemented yet")
  public void filterByWrench() {}

  @Test
  @Category(FeatureTests.class)
  @Ignore("Not implemented yet")
  public void sortAlphabetical() {}

  @Test
  @Category(FeatureTests.class)
  @Ignore("Not implemented yet")
  public void reverseSortAlphabetical() {}

  @Test
  @Category(FeatureTests.class)
  @Ignore("Not implemented yet")
  public void reverseSortPrice() {}

  @Test
  @Category(FeatureTests.class)
  @Ignore("Not implemented yet")
  public void search() {
    // "Claw"
  }

  @Test
  @Category(FeatureTests.class)
  @Ignore("Not implemented yet")
  public void clearSearch() {}
}
