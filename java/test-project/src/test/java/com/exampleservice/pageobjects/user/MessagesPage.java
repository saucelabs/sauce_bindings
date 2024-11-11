package com.exampleservice.pageobjects.user;

import com.exampleservice.pageobjects.BasePage;
import com.exampleservice.pageobjects.LoginPage;
import org.openqa.selenium.WebDriver;

public class MessagesPage extends BasePage {
  public MessagesPage(WebDriver driver) {
    super(driver);
  }

  @Override
  protected String getUrlPath() {
    return "account/messages";
  }

  @Override
  public void open() {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.open();
    loginPage.loginCustomer();
    super.open();
  }
}
