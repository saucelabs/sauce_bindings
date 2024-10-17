package com.practicesoftwaretesting.junit4;

import com.practicesoftwaretesting.pageobjects.ContactPage;
import com.practicesoftwaretesting.pageobjects.NavBar;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriverException;

public class ContactPageTest extends BaseTest {
  private ContactPage contactPage;

  @Before
  public void navigate() {
    contactPage = new ContactPage(driver);
    contactPage.open();
  }

  @Category(ContentTests.class)
  @Test
  public void pageCheck() {
    Assertions.assertDoesNotThrow(() -> contactPage.validateContent("files must be 0kb"));
  }

  @Test
  @Category(ContentTests.class)
  public void hasNavBar() {
    try {
      new NavBar(driver).isDisplayed();
    } catch (WebDriverException e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  @Category(FeatureTests.class)
  @Ignore("This feature works, but returns an error instead of success message")
  public void sendMessageSuccessfully() {
    String message = "The message that gets sent must be greater than 50 characters";
    contactPage.sendMessageSuccessfully("First Name", "Last Name", "me@example.com", message);
  }

  @Test
  @Category(FeatureTests.class)
  public void sendMessageUnsuccessfully() {
    String expectedMessage =
        "Error messages:\n"
            + "First name is required\n"
            + "Last name is required\n"
            + "Email is required\n"
            + "Subject is required\n"
            + "Message is required";

    try {
      contactPage.sendMessageUnsuccessfully("", "", "", "");
    } catch (RuntimeException e) {
      Assert.assertEquals("Exception message didn't match", expectedMessage, e.getMessage());
    }
  }
}
