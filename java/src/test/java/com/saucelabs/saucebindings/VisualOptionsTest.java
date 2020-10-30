package com.saucelabs.saucebindings;

import lombok.SneakyThrows;
import org.junit.Test;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.openqa.selenium.UnexpectedAlertBehaviour.DISMISS;

public class VisualOptionsTest {
    @Test
    public void acceptsChromeOptionsClass() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--foo");
        chromeOptions.setUnhandledPromptBehaviour(DISMISS);

        SauceOptions sauceOptions = SauceOptions.visual("acceptsChromeOptionsClass", chromeOptions);

        assertEquals(Browser.CHROME, sauceOptions.getBrowserName());
        assertEquals(chromeOptions, sauceOptions.getSeleniumCapabilities());
        assertNotNull(sauceOptions.visual());
    }

    @Test
    public void acceptsEdgeOptionsClass() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setPageLoadStrategy("eager");

        SauceOptions sauceOptions = SauceOptions.visual("acceptsEdgeOptionsClass", edgeOptions);

        assertEquals(Browser.EDGE, sauceOptions.getBrowserName());
        assertEquals(edgeOptions, sauceOptions.getSeleniumCapabilities());
        assertNotNull(sauceOptions.visual());
    }

    @SneakyThrows
    public Map<String, Object> serialize(String key) {
        InputStream input = new FileInputStream(new File("src/test/java/com/saucelabs/saucebindings/options.yml"));
        Yaml yaml = new Yaml();
        Map<String, Object> data = yaml.load(input);
        return (Map<String, Object>) data.get(key);
    }

    @Test
    public void setsVisualCapabilitiesFromMap() {
        SauceOptions sauceOptions = SauceOptions.visual("setsVisualCapabilitiesFromMap");

        Map<String, Object> map = serialize("visualValues");
        sauceOptions.mergeCapabilities(map);

        Map<String, Object> diffMap = new HashMap<>();
        diffMap.put("structure", true);
        diffMap.put("layout", true);
        diffMap.put("style", true);
        diffMap.put("content", true);
        diffMap.put("minLayoutPosition", 3);
        diffMap.put("minLayoutDimension", 12);

        assertEquals(Browser.FIREFOX, sauceOptions.getBrowserName());
        assertEquals("68", sauceOptions.getBrowserVersion());
        assertEquals(SaucePlatform.MAC_HIGH_SIERRA, sauceOptions.getPlatformName());
        assertEquals("Sample Build Name", sauceOptions.getBuild());
        assertEquals("fromYAML", sauceOptions.visual().getProjectName());
        assertEquals("1280x1024", sauceOptions.visual().getViewportSize());
        assertEquals(diffMap, sauceOptions.visual().getDiffOptions());
        assertEquals("#foo, .bar", sauceOptions.visual().getIgnore());
        assertTrue(sauceOptions.visual().getFailOnNewStates());
        assertTrue(sauceOptions.visual().getScrollAndStitchScreenshots());
        assertTrue(sauceOptions.visual().getDisableCORS());
    }

    @Test
    public void parsesCapabilitiesFromVisualValues() {
        SauceOptions sauceOptions = SauceOptions.visual("parsesCapabilitiesFromVisualValues");

        Map<String, Object> diffMap = new HashMap<>();
        diffMap.put("structure", false);
        diffMap.put("layout", false);
        diffMap.put("style", false);
        diffMap.put("content", false);
        diffMap.put("minLayoutPosition", 5);
        diffMap.put("minLayoutDimension", 9);

        sauceOptions.setBuild("Build Name");

        sauceOptions.visual().setViewportSize("1900x1200");
        sauceOptions.visual().setBranch("my-branch");
        sauceOptions.visual().setBaseBranch("base-branch");
        sauceOptions.visual().setIgnore("#some-id, .some-class");
        sauceOptions.visual().setFailOnNewStates(true);
        sauceOptions.visual().setAlwaysAcceptBaseBranch(true);
        sauceOptions.visual().setDisableBranchBaseline(true);
        sauceOptions.visual().setScrollAndStitchScreenshots(true);
        sauceOptions.visual().setDisableCORS(true);
        sauceOptions.visual().setDiffOptions(diffMap);

        MutableCapabilities expectedCapabilities = new MutableCapabilities();
        expectedCapabilities.setCapability("browserName", "chrome");
        expectedCapabilities.setCapability("browserVersion", "latest");
        expectedCapabilities.setCapability("platformName", "Windows 10");

        MutableCapabilities sauceCapabilities = new MutableCapabilities();
        sauceCapabilities.setCapability("build", "Build Name");
        sauceCapabilities.setCapability("username", System.getenv("SAUCE_USERNAME"));
        sauceCapabilities.setCapability("accessKey", System.getenv("SAUCE_ACCESS_KEY"));
        expectedCapabilities.setCapability("sauce:options", sauceCapabilities);

        MutableCapabilities visualCapabilities = new MutableCapabilities();
        visualCapabilities.setCapability("apiKey", System.getenv("SCREENER_API_KEY"));

        visualCapabilities.setCapability("projectName", "parsesCapabilitiesFromVisualValues");
        visualCapabilities.setCapability("viewportSize", "1900x1200");
        visualCapabilities.setCapability("branch", "my-branch");
        visualCapabilities.setCapability("baseBranch", "base-branch");

        visualCapabilities.setCapability("diffOptions", diffMap);
        visualCapabilities.setCapability("ignore", "#some-id, .some-class");

        visualCapabilities.setCapability("failOnNewStates", true);
        visualCapabilities.setCapability("alwaysAcceptBaseBranch", true);
        visualCapabilities.setCapability("disableBranchBaseline", true);
        visualCapabilities.setCapability("scrollAndStitchScreenshots", true);
        visualCapabilities.setCapability("disableCORS", true);

        expectedCapabilities.setCapability("sauce:visual", visualCapabilities);

        MutableCapabilities actualCapabilities = sauceOptions.toCapabilities();
        // toString() serializes the enums
        assertEquals(expectedCapabilities.asMap().toString(), actualCapabilities.asMap().toString());
    }
}
