package com.practicesoftwaretesting.pageobjects;

import com.practicesoftwaretesting.pageobjects.admin.DashboardPage;
import com.practicesoftwaretesting.pageobjects.user.AccountPage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

  @Getter private final By usernameInput = By.cssSelector("input[data-test=\"email\"]");

  @Getter private final By passwordInput = By.cssSelector("input[data-test=\"password\"]");

  @Getter private final By submitBtn = By.cssSelector("input[data-test=\"login-submit\"]");

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  @Override
  protected String getUrlPath() {
    return "auth/login";
  }

  @Override
  public boolean isDisplayed() {
    return driver.findElement(submitBtn).isDisplayed();
  }

  public DashboardPage loginAdmin() {
    driver.findElement(usernameInput).sendKeys("admin@practicesoftwaretesting.com");
    driver.findElement(passwordInput).sendKeys("welcome01");
    driver.findElement(submitBtn).click();
    DashboardPage dashboardPage = new DashboardPage(driver);
    dashboardPage.sync();
    return dashboardPage;
  }

  public AccountPage loginCustomer() {
    driver.findElement(usernameInput).sendKeys("customer@practicesoftwaretesting.com");
    driver.findElement(passwordInput).sendKeys("welcome01");
    driver.findElement(submitBtn).click();
    AccountPage accountPage = new AccountPage(driver);
    accountPage.sync();
    return accountPage;
  }

  public AccountPage login(String username, String password) {
    driver.findElement(usernameInput).sendKeys(username);
    driver.findElement(passwordInput).sendKeys(password);
    driver.findElement(submitBtn).click();
    AccountPage accountPage = new AccountPage(driver);
    accountPage.sync();
    return accountPage;
  }
}
