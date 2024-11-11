package com.exampleservice.testng;

import com.exampleservice.pageobjects.NavBar;
import com.exampleservice.pageobjects.RentalsPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RentalsPageTest extends BaseTest {

  @BeforeMethod
  public void navigate() {
    new RentalsPage(driver.get()).open();
  }

  @Test(groups = {"contents"})
  public void pageCheck() {
    RentalsPage rentalsPage = new RentalsPage(driver.get());
    Assertions.assertDoesNotThrow(() -> rentalsPage.validateContent("Bulldozer"));
  }

  @Test(groups = {"contents"})
  public void hasNavBar() {
    try {
      new NavBar(driver.get()).isDisplayed();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }
}
