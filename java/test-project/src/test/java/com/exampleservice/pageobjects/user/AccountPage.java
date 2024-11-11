package com.exampleservice.pageobjects.user;

import com.exampleservice.pageobjects.BasePage;
import com.exampleservice.pageobjects.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends BasePage {
  private final By favoritesLink = By.cssSelector("a[data-test=nav-favorites]");
  private final By profileLink = By.cssSelector("a[data-test=nav-profile]");
  private final By invoicesLink = By.cssSelector("a[data-test=nav-invoices]");
  private final By messagesLink = By.cssSelector("a[data-test=nav-messages]");

  public AccountPage(WebDriver driver) {
    super(driver);
  }

  @Override
  protected String getUrlPath() {
    return "account";
  }

  @Override
  public boolean isDisplayed() {
    return driver.findElement(favoritesLink).isDisplayed();
  }

  @Override
  public void open() {
    LoginPage loginPage = new LoginPage(driver);
    loginPage.open();
    loginPage.loginCustomer();
    super.open();
  }

  public FavoritesPage navigateToFavorites() {
    driver.findElement(favoritesLink).click();
    FavoritesPage favoritesPage = new FavoritesPage(driver);
    wait.until(_driver -> favoritesPage.isDisplayed());
    return favoritesPage;
  }

  public ProfilePage navigateToProfile() {
    driver.findElement(profileLink).click();
    ProfilePage profilePage = new ProfilePage(driver);
    wait.until(_driver -> profilePage.isDisplayed());
    return profilePage;
  }

  public InvoicesPage navigateToInvoices() {
    driver.findElement(invoicesLink).click();
    InvoicesPage invoicesPage = new InvoicesPage(driver);
    wait.until(_driver -> invoicesPage.isDisplayed());
    return invoicesPage;
  }

  public MessagesPage navigateToMessages() {
    driver.findElement(messagesLink).click();
    MessagesPage messagesPage = new MessagesPage(driver);
    wait.until(_driver -> messagesPage.isDisplayed());
    return messagesPage;
  }
}
