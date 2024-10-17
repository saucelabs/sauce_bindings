package com.practicesoftwaretesting.pageobjects.user;

import com.practicesoftwaretesting.pageobjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavBarUser extends BasePage {
  private final By dropdownMenu = By.cssSelector("a[data-test=nav-user-menu");
  private final By accountLink = By.cssSelector("a[data-test=nav-my-account]");
  private final By favoritesLink = By.cssSelector("a[data-test=nav-my-favorites]");
  private final By profileLink = By.cssSelector("a[data-test=nav-my-profile]");
  private final By invoicesLink = By.cssSelector("a[data-test=nav-my-invoices]");
  private final By messagesLink = By.cssSelector("a[data-test=nav-my-messages]");

  @Override
  protected String getUrlPath() {
    return "";
  }

  @Override
  public boolean isDisplayed() {
    return driver.findElement(dropdownMenu).isDisplayed();
  }

  public NavBarUser(WebDriver driver) {
    super(driver);
  }

  public AccountPage navigateToAccount() {
    driver.findElement(dropdownMenu).click();
    driver.findElement(accountLink).click();
    AccountPage accountPage = new AccountPage(driver);
    wait.until((_driver) -> accountPage.isDisplayed());
    return accountPage;
  }

  public FavoritesPage navigateToFavorites() {
    driver.findElement(dropdownMenu).click();
    driver.findElement(favoritesLink).click();
    FavoritesPage favoritesPage = new FavoritesPage(driver);
    wait.until(_driver -> favoritesPage.isDisplayed());
    return favoritesPage;
  }

  public ProfilePage navigateToProfile() {
    driver.findElement(dropdownMenu).click();
    driver.findElement(profileLink).click();
    ProfilePage profilePage = new ProfilePage(driver);
    wait.until(_driver -> profilePage.isDisplayed());
    return profilePage;
  }

  public InvoicesPage navigateToInvoices() {
    driver.findElement(dropdownMenu).click();
    driver.findElement(invoicesLink).click();
    InvoicesPage invoicesPage = new InvoicesPage(driver);
    wait.until(_driver -> invoicesPage.isDisplayed());
    return invoicesPage;
  }

  public MessagesPage navigateToMessages() {
    driver.findElement(dropdownMenu).click();
    driver.findElement(messagesLink).click();
    MessagesPage messagesPage = new MessagesPage(driver);
    wait.until(_driver -> messagesPage.isDisplayed());
    return messagesPage;
  }
}
