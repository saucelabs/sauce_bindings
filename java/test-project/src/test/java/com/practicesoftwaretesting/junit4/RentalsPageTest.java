package com.practicesoftwaretesting.junit4;

import com.practicesoftwaretesting.pageobjects.NavBar;
import com.practicesoftwaretesting.pageobjects.RentalsPage;
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
