package com.practicesoftwaretesting.pageobjects;

import java.util.List;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePage {
  @Getter private final By contactLink = By.cssSelector("a[data-test=\"nav-contact\"]");
  @Getter private final By signInLink = By.cssSelector("a[data-test=\"nav-sign-in\"]");
  @Getter private final By logoLink = By.id("Layer_1");
  private final By card = By.cssSelector("a.card");
  private final By outOfStockAlert = By.cssSelector("span[data-test=out-of-stock]");
  private final By overview = By.tagName("app-overview");
  private final By firstCard = By.cssSelector(".card-img-top:first-child");

  @Override
  protected String getUrlPath() {
    return "";
  }

  @Override
  public boolean isDisplayed() {
    return driver.findElement(overview).isDisplayed();
  }

  public boolean isPresent() {
    return !driver.findElements(overview).isEmpty();
  }


  public HomePage(WebDriver driver) {
    super(driver);
  }

  public LoginPage navigateToLogin() {
    WebElement signIn = driver.findElement(signInLink);
    signIn.click();
    LoginPage loginPage = new LoginPage(driver);
    loginPage.sync();
    return loginPage;
  }

  public ContactPage navigateToContactPage() {
    WebElement contact = driver.findElement(contactLink);
    contact.click();
    ContactPage contactPage = new ContactPage(driver);
    contactPage.sync();
    return contactPage;
  }

  public void viewInStockProduct() {
    List<WebElement> cards = driver.findElements(card);
    WebElement inStockCard =
        cards.stream()
            .filter(card -> card.findElements(outOfStockAlert).isEmpty())
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("No in stock cards found."));
    inStockCard.click();
    wait.withMessage("Overview element is not present").until((_driver) -> !isPresent());
  }

  public void viewOutOfStockProduct() {
    List<WebElement> cards = driver.findElements(card);
    WebElement outOfStockCard =
        cards.stream()
            .filter(card -> !card.findElements(outOfStockAlert).isEmpty())
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("No out of stock cards found."));
    outOfStockCard.click();
    wait.withMessage("Overview element is not present").until((_driver) -> !isPresent());
  }
}
