package com.saucelabs.simplesauce.config;

import com.saucelabs.simplesauce.config.SauceConfig;
import org.junit.Test;

public class SauceConfigTest {

	@Test
	public void configuration_with_linux_should_allow_chrome_browser()
	{
		SauceConfig config = new SauceConfig();
		config.withLinux().withChrome();
	}
}
