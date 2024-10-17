package com.practicesoftwaretesting.testng;

import com.practicesoftwaretesting.pageobjects.ContactPage;
import com.practicesoftwaretesting.pageobjects.NavBar;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriverException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactPageTest extends BaseTest {

  @BeforeMethod
  public void navigate() {
    new ContactPage(driver.get()).open();
  }

  @Test(groups = {"contents"})
  public void pageCheck() {
    ContactPage contactPage = new ContactPage(driver.get());
    Assertions.assertDoesNotThrow(() -> contactPage.validateContent("files must be 0kb"));
  }

  @Test(groups = {"contents"})
  public void hasNavBar() {
    try {
      new NavBar(driver.get()).isDisplayed();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test(
      enabled = false,
      groups = {"features"})
  public void sendMessageSuccessfully() {
    try {
      String message = "The message that gets sent must be greater than 50 characters";
      ContactPage contactPage = new ContactPage(driver.get());
      contactPage.sendMessageSuccessfully("First Name", "Last Name", "me@example.com", message);
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test(groups = {"features"})
  public void sendMessageUnsuccessfully() {
    String expectedMessage =
        "Error messages:\n"
            + "First name is required\n"
            + "Last name is required\n"
            + "Email is required\n"
            + "Subject is required\n"
            + "Message is required";

    try {
      ContactPage contactPage = new ContactPage(driver.get());
      contactPage.sendMessageUnsuccessfully("", "", "", "");
      Assert.fail("This test should time out");
    } catch (RuntimeException e) {
      Assert.assertEquals(e.getMessage(), expectedMessage);
    }
  }
}
