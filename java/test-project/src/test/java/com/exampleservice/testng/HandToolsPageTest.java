package com.exampleservice.testng;

import com.exampleservice.pageobjects.HandToolsPage;
import com.exampleservice.pageobjects.NavBar;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HandToolsPageTest extends BaseTest {

  @BeforeMethod
  public void navigate() {
    new HandToolsPage(driver.get()).open();
  }

  @Test(groups = {"contents"})
  public void pageCheck() {
    HandToolsPage handToolsPage = new HandToolsPage(driver.get());
    Assertions.assertDoesNotThrow(() -> handToolsPage.validateContent("Hand Saw"));
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
