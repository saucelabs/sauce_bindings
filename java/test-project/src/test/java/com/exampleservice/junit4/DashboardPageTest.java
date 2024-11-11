package com.exampleservice.junit4;

import com.exampleservice.pageobjects.NavBar;
import com.exampleservice.pageobjects.admin.DashboardPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriverException;

public class DashboardPageTest extends BaseTest {
  private DashboardPage dashboardPage;

  @Before
  public void navigate() {
    dashboardPage = new DashboardPage(driver);
    dashboardPage.open();
  }

  @Test
  @Category(ContentTests.class)
  public void pageCheck() {
    Assertions.assertDoesNotThrow(() -> dashboardPage.validateContent("Latest orders"));
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
  public void editInvoice() {
    // Requires new "Orders" PO that can update status
  }
}
