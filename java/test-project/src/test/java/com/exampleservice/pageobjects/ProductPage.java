package com.exampleservice.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {
  private final By productName = By.cssSelector("h1[data-test=product-name]");
  private final By addToCartBtn = By.cssSelector("button[data-test=add-to-cart]");
  private final By addToFavoritesBtn = By.cssSelector("button[data-test=add-to-favorites]");
  private final By increaseQuantityBtn = By.cssSelector("[data-test=increase-quantity]");
  private final By decreaseQuantityBtn = By.cssSelector("[data-test=decrease-quantity]");
  private final By outOfStockAlert = By.cssSelector("p[data-test=out-of-stock]");

  @Override
  protected String getUrlPath() {
    return "product/<ID>";
  }

  @Override
  public void open() {
    HomePage homePage = new HomePage(driver);
    homePage.open();
    homePage.viewInStockProduct();
    sync();
  }

  @Override
  public boolean isDisplayed() {
    return driver.findElement(productName).isDisplayed();
  }

  public ProductPage(WebDriver driver) {
    super(driver);
  }

  public void addToCart() {
    driver.findElement(addToCartBtn).click();
  }

  public void increaseQuantity() {
    driver.findElement(increaseQuantityBtn).click();
  }

  public void decreaseQuantity() {
    driver.findElement(decreaseQuantityBtn).click();
  }

  public void addToFavorites() {
    driver.findElement(addToFavoritesBtn).click();
  }

  public boolean isInStock() {
    return driver.findElements(outOfStockAlert).isEmpty();
  }
}
