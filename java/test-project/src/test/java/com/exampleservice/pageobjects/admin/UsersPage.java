package com.exampleservice.pageobjects.admin;

import com.exampleservice.pageobjects.BasePage;
import com.exampleservice.pageobjects.LoginPage;
import org.openqa.selenium.WebDriver;

public class UsersPage extends BasePage {
  public UsersPage(WebDriver driver) {
    super(driver);
  }

  @Override
  protected String getUrlPath() {
    return "admin/users";
  }

  @Override
  public void open() {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.open();
    loginPage.loginAdmin();
    super.open();
  }
}
