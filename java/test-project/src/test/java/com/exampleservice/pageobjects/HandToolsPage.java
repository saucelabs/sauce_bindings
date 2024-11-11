package com.exampleservice.pageobjects;

import org.openqa.selenium.WebDriver;

public class HandToolsPage extends BasePage {

  @Override
  protected String getUrlPath() {
    return "category/hand-tools";
  }

  public HandToolsPage(WebDriver driver) {
    super(driver);
  }
}
