package com.practicesoftwaretesting.pageobjects.admin;

import com.practicesoftwaretesting.pageobjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavBarAdmin extends BasePage {
  private final By dropdownMenu = By.cssSelector("a[data-test=nav-admin-menu");
  private final By dashboardLink = By.cssSelector("a[data-test=nav-admin-dashboard]");
  private final By brandsLink = By.cssSelector("a[data-test=nav-admin-brands]");
  private final By categoriesLink = By.cssSelector("a[data-test=nav-admin-categories]");
  private final By productsLink = By.cssSelector("a[data-test=nav-admin-products]");
  private final By ordersLink = By.cssSelector("a[data-test=nav-admin-orders]");
  private final By usersLink = By.cssSelector("a[data-test=nav-admin-users]");
  private final By messagesLink = By.cssSelector("a[data-test=nav-admin-messages]");
  private final By settingsLink = By.cssSelector("a[data-test=nav-admin-settings]");

  @Override
  protected String getUrlPath() {
    return "";
  }

  @Override
  public boolean isDisplayed() {
    return driver.findElement(dropdownMenu).isDisplayed();
  }

  public NavBarAdmin(WebDriver driver) {
    super(driver);
  }

  public DashboardPage navigateToDashboard() {
    driver.findElement(dropdownMenu).click();
    driver.findElement(dashboardLink).click();
    DashboardPage dashboardPage = new DashboardPage(driver);
    wait.until((_driver) -> dashboardPage.isDisplayed());
    return dashboardPage;
  }

  public BrandsPage navigateToBrands() {
    driver.findElement(dropdownMenu).click();
    driver.findElement(brandsLink).click();
    BrandsPage brandsPage = new BrandsPage(driver);
    wait.until(_driver -> brandsPage.isDisplayed());
    return brandsPage;
  }

  public CategoriesPage navigateToCategories() {
    driver.findElement(dropdownMenu).click();
    driver.findElement(categoriesLink).click();
    CategoriesPage categoriesPage = new CategoriesPage(driver);
    wait.until(_driver -> categoriesPage.isDisplayed());
    return categoriesPage;
  }

  public ProductsPage navigateToProducts() {
    driver.findElement(dropdownMenu).click();
    driver.findElement(productsLink).click();
    ProductsPage productsPage = new ProductsPage(driver);
    wait.until(_driver -> productsPage.isDisplayed());
    return productsPage;
  }

  public OrdersPage navigateToOrders() {
    driver.findElement(dropdownMenu).click();
    driver.findElement(ordersLink).click();
    OrdersPage ordersPage = new OrdersPage(driver);
    wait.until(_driver -> ordersPage.isDisplayed());
    return ordersPage;
  }

  public UsersPage navigateToUsers() {
    driver.findElement(dropdownMenu).click();
    driver.findElement(usersLink).click();
    UsersPage usersPage = new UsersPage(driver);
    wait.until(_driver -> usersPage.isDisplayed());
    return usersPage;
  }

  public MessagesPage navigateToMessages() {
    driver.findElement(dropdownMenu).click();
    driver.findElement(messagesLink).click();
    MessagesPage messagesPage = new MessagesPage(driver);
    wait.until(_driver -> messagesPage.isDisplayed());
    return messagesPage;
  }

  public SettingsPage navigateToSettings() {
    driver.findElement(dropdownMenu).click();
    driver.findElement(settingsLink).click();
    SettingsPage settingsPage = new SettingsPage(driver);
    wait.until(_driver -> settingsPage.isDisplayed());
    return settingsPage;
  }

  // TODO implement Reports, which requires hovering
  // POs -> Stats, Monthly sales, Weekly sales
}
