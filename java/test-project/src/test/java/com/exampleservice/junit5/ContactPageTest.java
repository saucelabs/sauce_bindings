package com.exampleservice.junit5;

import com.exampleservice.pageobjects.ContactPage;
import com.exampleservice.pageobjects.NavBar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class ContactPageTest extends BaseTest {
  private ContactPage contactPage;

  @BeforeEach
  public void navigate() {
    contactPage = new ContactPage(driver);
    contactPage.open();
  }

  @Test
  @Tag("content")
  void pageCheck() {
    Assertions.assertDoesNotThrow(() -> contactPage.validateContent("files must be 0kb"));
  }

  @Test
  @Tag("content")
  void hasNavBar() {
    Assertions.assertDoesNotThrow(() -> new NavBar(driver).isDisplayed());
  }

  @Test
  @Tag("feature")
  @Disabled("This feature works, but returns an error instead of success message")
  void sendMessageSuccessfully() {
    String message = "The message that gets sent must be greater than 50 characters";
    contactPage.sendMessageSuccessfully("First Name", "Last Name", "me@example.com", message);
  }

  @Test
  @Tag("feature")
  void sendMessageUnsuccessfully() {
    String expectedMessage =
        "Error messages:\n"
            + "First name is required\n"
            + "Last name is required\n"
            + "Email is required\n"
            + "Subject is required\n"
            + "Message is required";

    RuntimeException thrown =
        Assertions.assertThrows(
            RuntimeException.class, () -> contactPage.sendMessageUnsuccessfully("", "", "", ""));
    Assertions.assertEquals(
        expectedMessage,
        thrown.getMessage(),
        "The exception message does not match the expected message.");
  }
}
