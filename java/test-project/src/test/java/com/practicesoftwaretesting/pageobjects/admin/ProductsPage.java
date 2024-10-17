package com.practicesoftwaretesting.pageobjects.admin;

import com.practicesoftwaretesting.pageobjects.BasePage;
import com.practicesoftwaretesting.pageobjects.LoginPage;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {
  public ProductsPage(WebDriver driver) {
    super(driver);
  }

  @Override
  protected String getUrlPath() {
    return "admin/products";
  }

  @Override
  public void open() {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.open();
    loginPage.loginAdmin();
    super.open();
  }
}
