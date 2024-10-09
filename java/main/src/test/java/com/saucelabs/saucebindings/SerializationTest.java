package com.saucelabs.saucebindings;

import com.saucelabs.saucebindings.options.SauceOptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

public class SerializationTest {

    @Test
    public void copyOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--hide-scrollbars");
        SauceOptions options = SauceOptions.chrome(chromeOptions).setBuild("TEST BUILD").build();

        SauceOptions copiedOptions = options.copy();
        Assertions.assertNotEquals(options, copiedOptions);
        Assertions.assertEquals(options.toCapabilities(), copiedOptions.toCapabilities());
    }
}