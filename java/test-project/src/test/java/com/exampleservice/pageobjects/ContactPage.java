package com.exampleservice.pageobjects;

import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContactPage extends BasePage {

  private final By firstNameText = By.cssSelector("input[data-test=\"email\"]");
  private final By lastNameText = By.cssSelector("input[data-test=\"email\"]");
  private final By emailText = By.cssSelector("input[data-test=\"email\"]");
  private final By messageText = By.cssSelector("input[data-test=\"email\"]");
  private final By submitBtn = By.cssSelector("input[data-test=\"contact-submit\"]");
  private final By error = By.cssSelector("div.alert-danger");

  @Override
  protected String getUrlPath() {
    return "contact";
  }

  public ContactPage(WebDriver driver) {
    super(driver);
  }

  public void sendMessageSuccessfully(
      String firstName, String lastName, String email, String message) {
    sendMessage(firstName, lastName, email, message);
  }

  public void sendMessageUnsuccessfully(
      String firstName, String lastName, String email, String message) {
    sendMessage(firstName, lastName, email, message);
    wait.until(d -> d.findElements(error).size() > 3);
    String errorMessages =
        driver.findElements(error).stream()
            .map(WebElement::getText)
            .collect(Collectors.joining("\n"));
    throw new RuntimeException("Error messages:\n" + errorMessages);
  }

  // TODO - support subject dropdown
  private void sendMessage(String firstName, String lastName, String email, String message) {
    driver.findElement(firstNameText).sendKeys(firstName);
    driver.findElement(lastNameText).sendKeys(lastName);
    driver.findElement(emailText).sendKeys(email);
    driver.findElement(messageText).sendKeys(message);
    driver.findElement(submitBtn).click();
  }
}
