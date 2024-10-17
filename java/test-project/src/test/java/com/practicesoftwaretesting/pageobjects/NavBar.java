package com.practicesoftwaretesting.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavBar extends BasePage {
  private final By homeLink = By.cssSelector("a[data-test=nav-home]");
  private final By contactLink = By.cssSelector("a[data-test=nav-contact]");
  private final By signInLink = By.cssSelector("a[data-test=nav-sign-in]");
  private final By signOutLink = By.cssSelector("a[data-test=nav-sign-out]");
  private final By dropDownList = By.cssSelector("ul.dropdown-menu");
  private final By handToolsLink = By.cssSelector("a[data-test=nav-hand-tools]");
  private final By powerToolsLink = By.cssSelector("a[data-test=nav-power-tools]");
  private final By specialToolsLink = By.cssSelector("a[data-test=nav-special-tools]");
  private final By rentalsLink = By.cssSelector("a[data-test=nav-rentals]");
  private final By logoLink = By.cssSelector("#Layer_1");
  private final By cartLink = By.cssSelector("a[data-test=nav-cart]");
  private final By cartValue = By.cssSelector("span[data-test=cart-quantity]");

  @Override
  protected String getUrlPath() {
    return "";
  }

  @Override
  public boolean isDisplayed() {
    return driver.findElement(homeLink).isDisplayed();
  }

  public NavBar(WebDriver driver) {
    super(driver);
  }

  public HomePage navigateToHome() {
    driver.findElement(homeLink).click();
    HomePage homePage = new HomePage(driver);
    wait.until((_driver) -> homePage.isDisplayed());
    return homePage;
  }

  public ContactPage navigateToContact() {
    driver.findElement(contactLink).click();
    ContactPage contactPage = new ContactPage(driver);
    wait.until((_driver) -> contactPage.isDisplayed());
    return contactPage;
  }

  public LoginPage navigateToLogin() {
    driver.findElement(signInLink).click();
    LoginPage loginPage = new LoginPage(driver);
    wait.until((_driver) -> loginPage.isDisplayed());
    return loginPage;
  }

  public void navigateToHandTools() {
    driver.findElement(dropDownList).click();
    driver.findElement(handToolsLink).click();
  }

  public void navigateToPowerTools() {
    driver.findElement(dropDownList).click();
    driver.findElement(powerToolsLink).click();
  }

  public void navigateToSpecialTools() {
    driver.findElement(dropDownList).click();
    driver.findElement(specialToolsLink).click();

  }

  public void navigateToRentals() {
    driver.findElement(dropDownList).click();
    driver.findElement(rentalsLink).click();
  }

  public int getCartQuantity() {
    WebElement cartValueElement = driver.findElement(cartValue);
    String value = cartValueElement.getText();
    return Integer.parseInt(value);
  }

  public void signOut() {
    driver.findElement(signOutLink).click();
  }
}
