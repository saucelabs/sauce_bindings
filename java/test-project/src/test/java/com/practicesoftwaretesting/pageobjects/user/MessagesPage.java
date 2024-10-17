package com.practicesoftwaretesting.pageobjects.user;

import com.practicesoftwaretesting.pageobjects.BasePage;
import com.practicesoftwaretesting.pageobjects.LoginPage;
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
