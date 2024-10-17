package com.practicesoftwaretesting.pageobjects;

import org.openqa.selenium.WebDriver;

public class PowerToolsPage extends BasePage {

  @Override
  protected String getUrlPath() {
    return "category/power-tools";
  }

  public PowerToolsPage(WebDriver driver) {
    super(driver);
  }
}
