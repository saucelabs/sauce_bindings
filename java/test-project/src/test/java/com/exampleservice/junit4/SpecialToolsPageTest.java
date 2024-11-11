package com.exampleservice.junit4;

import com.exampleservice.pageobjects.NavBar;
import com.exampleservice.pageobjects.SpecialToolsPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriverException;

public class SpecialToolsPageTest extends BaseTest {
  private SpecialToolsPage specialToolsPage;

  @Before
  public void navigate() {
    specialToolsPage = new SpecialToolsPage(driver);
    specialToolsPage.open();
  }

  @Test
  @Category(ContentTests.class)
  public void pageCheck() {
    Assertions.assertDoesNotThrow(() -> specialToolsPage.validateContent("no products"));
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
