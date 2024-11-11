package com.exampleservice.junit4;

import com.exampleservice.pageobjects.HandToolsPage;
import com.exampleservice.pageobjects.NavBar;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriverException;

public class HandToolsPageTest extends BaseTest {
  private HandToolsPage handToolsPage;

  @Before
  public void navigate() {
    handToolsPage = new HandToolsPage(driver);
    handToolsPage.open();
  }

  @Test
  @Category(ContentTests.class)
  public void pageCheck() {
    Assertions.assertDoesNotThrow(() -> handToolsPage.validateContent("Hand Saw"));
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
  @Ignore("Not implemented yet")
  public void filterCategory() {}

  @Test
  @Category(FeatureTests.class)
  @Ignore("Not implemented yet")
  public void filterBrand() {}

  @Test
  @Category(FeatureTests.class)
  @Ignore("Not implemented yet")
  public void sort() {}

  @Test
  @Category(FeatureTests.class)
  @Ignore("Not implemented yet")
  public void selectProduct() {}
}
