package com.exampleservice.testng;

import com.exampleservice.pageobjects.NavBar;
import com.exampleservice.pageobjects.PowerToolsPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PowerToolsPageTest extends BaseTest {

  @BeforeMethod
  public void navigate() {
    new PowerToolsPage(driver.get()).open();
  }

  @Test(groups = {"contents"})
  public void pageCheck() {
    PowerToolsPage powerToolsPage = new PowerToolsPage(driver.get());
    Assertions.assertDoesNotThrow(() -> powerToolsPage.validateContent("Grinder"));
  }

  @Test(groups = {"contents"})
  public void hasNavBar() {
    try {
      new NavBar(driver.get()).isDisplayed();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test(
      enabled = false,
      groups = {"features"})
  public void filterCategory() {}

  @Test(
      enabled = false,
      groups = {"features"})
  public void filterBrand() {}

  @Test(
      enabled = false,
      groups = {"features"})
  public void sort() {}

  @Test(
      enabled = false,
      groups = {"features"})
  public void selectProduct() {}
}
