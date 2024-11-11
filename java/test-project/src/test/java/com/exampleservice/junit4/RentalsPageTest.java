package com.exampleservice.junit4;

import com.exampleservice.pageobjects.NavBar;
import com.exampleservice.pageobjects.RentalsPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriverException;

public class RentalsPageTest extends BaseTest {
  private RentalsPage rentalsPage;

  @Before
  public void navigate() {
    rentalsPage = new RentalsPage(driver);
    rentalsPage.open();
  }

  @Test
  @Category(ContentTests.class)
  public void pageCheck() {
    Assertions.assertDoesNotThrow(() -> rentalsPage.validateContent("Bulldozer"));
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
}
