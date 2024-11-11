package com.exampleservice.testng;

import com.exampleservice.pageobjects.NavBar;
import com.exampleservice.pageobjects.SpecialToolsPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SpecialToolsPageTest extends BaseTest {

  @BeforeMethod
  public void navigate() {
    new SpecialToolsPage(driver.get()).open();
  }

  @Test(groups = {"contents"})
  public void pageCheck() {
    SpecialToolsPage specialToolsPage = new SpecialToolsPage(driver.get());
    Assertions.assertDoesNotThrow(() -> specialToolsPage.validateContent("no products"));
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
