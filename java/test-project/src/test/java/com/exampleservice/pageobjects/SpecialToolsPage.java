package com.exampleservice.pageobjects;

import org.openqa.selenium.WebDriver;

public class SpecialToolsPage extends BasePage {

  @Override
  protected String getUrlPath() {
    return "category/special-tools";
  }

  public SpecialToolsPage(WebDriver driver) {
    super(driver);
  }
}
