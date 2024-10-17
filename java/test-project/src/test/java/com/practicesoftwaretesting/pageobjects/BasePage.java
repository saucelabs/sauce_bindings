package com.practicesoftwaretesting.pageobjects;

import java.time.Duration;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
  protected final WebDriver driver;
  protected final WebDriverWait wait;

  protected abstract String getUrlPath();

  protected void sync() {
      wait.until((_driver) -> isDisplayed());
  }

  public boolean isDisplayed() {
    return driver.getCurrentUrl().contains(getUrlPath());
  }

  public BasePage(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
  }

  public void open() {
    String path = getFullUrl() + getUrlPath();
    driver.get(path);
    sync();
  }

  protected String getFullUrl() {
    return "https://example-service.com/#/";
  }

  public void validateContent(String content) {
    try {
      wait.until((driver) -> driver.getPageSource().contains(content));
    } catch (TimeoutException e) {
      throw new RuntimeException("Timed out trying to locate " + content + " in " + this.getClass(), e);
    }
  }
}
