package com.exampleservice.testng;

import com.exampleservice.pageobjects.NavBar;
import com.exampleservice.pageobjects.admin.DashboardPage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DashboardPageTest extends BaseTest {

  @BeforeMethod
  public void navigate() {
    new DashboardPage(driver.get()).open();
  }

  @Test(groups = {"contents"})
  public void pageCheck() {
    DashboardPage dashboardPage = new DashboardPage(driver.get());
    Assertions.assertDoesNotThrow(() -> dashboardPage.validateContent("Latest orders"));
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
  public void editInvoice() {}
}
