package com.exampleservice.pageobjects;

import org.openqa.selenium.WebDriver;

public class RentalsPage extends BasePage {

  @Override
  protected String getUrlPath() {
    return "rentals";
  }

  public RentalsPage(WebDriver driver) {
    super(driver);
  }
}
