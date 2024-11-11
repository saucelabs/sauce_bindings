package com.exampleservice.junit4;

import com.exampleservice.pageobjects.NavBar;
import com.exampleservice.pageobjects.user.InvoicesPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriverException;

public class InvoicesPageTest extends BaseTest {
  private InvoicesPage invoicesPage;

  @Before
  public void navigate() {
    invoicesPage = new InvoicesPage(driver);
    invoicesPage.open();
  }

  @Test
  @Category(ContentTests.class)
  public void invoicesPageCheck() {
    Assertions.assertDoesNotThrow(() -> invoicesPage.validateContent("Invoice Number"));
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
  public void selectInvoice() {
    try {
      invoicesPage.viewRandomInvoice();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }
}
