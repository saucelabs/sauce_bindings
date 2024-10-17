package com.practicesoftwaretesting.junit4;

import com.practicesoftwaretesting.pageobjects.NavBar;
import com.practicesoftwaretesting.pageobjects.PowerToolsPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriverException;

public class PowerToolsPageTest extends BaseTest {
  private PowerToolsPage powerToolsPage;

  @Before
  public void navigate() {
    powerToolsPage = new PowerToolsPage(driver);
    powerToolsPage.open();
  }

  @Test
  @Category(ContentTests.class)
  public void pageCheck() {
    Assertions.assertDoesNotThrow(() -> powerToolsPage.validateContent("Grinder"));
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
