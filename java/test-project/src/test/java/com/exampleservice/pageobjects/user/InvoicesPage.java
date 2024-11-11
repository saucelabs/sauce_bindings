package com.exampleservice.pageobjects.user;

import com.exampleservice.pageobjects.BasePage;
import com.exampleservice.pageobjects.LoginPage;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InvoicesPage extends BasePage {
  private final By detailsButton = By.cssSelector(".btn-primary");

  public InvoicesPage(WebDriver driver) {
    super(driver);
  }

  @Override
  protected String getUrlPath() {
    return "account/invoices";
  }

  @Override
  public boolean isDisplayed() {
    return !driver.findElements(detailsButton).isEmpty();
  }

  @Override
  public void open() {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.open();
    loginPage.loginCustomer();
    super.open();
  }

  public void viewRandomInvoice() {
    List<WebElement> detailsButtons = driver.findElements(detailsButton);
    detailsButtons.get(new Random().nextInt(detailsButtons.size())).click();
    wait.until((_driver) -> !isDisplayed());
  }
}
