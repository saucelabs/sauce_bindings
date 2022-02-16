package com.saucelabs.saucebindings.options;

import com.saucelabs.saucebindings.Browser;
import com.saucelabs.saucebindings.CITools;
import com.saucelabs.saucebindings.GitManager;
import com.saucelabs.saucebindings.SaucePlatform;
import com.saucelabs.saucebindings.SystemManager;
import org.junit.Test;
import org.openqa.selenium.MutableCapabilities;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class VisualOptionsTest {
    @Test
    public void createsDefaultVisualOptions() {
        VisualOptions visualOptions = new VisualOptions("Required Test Name");

        MutableCapabilities visualCapabilities = new MutableCapabilities();
        visualCapabilities.setCapability("apiKey", System.getenv("SCREENER_API_KEY"));
        visualCapabilities.setCapability("branch", GitManager.getCurrentBranch());
        visualCapabilities.setCapability("projectName", CITools.getBuildName());

        assertEquals(visualCapabilities, visualOptions.toCapabilities().getCapability("sauce:visual"));
    }

    @Test
    public void acceptsVisualOptions() {
        SauceOptions sauceOptions = SauceOptions.firefox()
                .setPlatformName(SaucePlatform.MAC_HIGH_SIERRA)
                .setBrowserVersion("99")
                .setName("Visual Options Needs a Name")
                .build();

        Map<String, Object> diffOptions = new HashMap<>();
        diffOptions.put("structure", true);
        diffOptions.put("layout", true);
        diffOptions.put("style", true);
        diffOptions.put("content", true);
        diffOptions.put("minLayoutPosition", 4);
        diffOptions.put("minLayoutDimension", 10);

        Map<String, Object> iframesOptions = new HashMap<>();
        iframesOptions.put("maxFrames", "Infinity");

        VisualOptions visualOptions = new VisualOptions(sauceOptions);
        visualOptions
                .setProjectName("Visual Options Project Name")
                .setViewportSize("1024x768")
                .setBranch("branch")
                .setBaseBranch("baseBranch")
                .setDiffOptions(diffOptions)
                .setIgnore("#some-id, .some-selector")
                .setFailOnNewStates(true)
                .setAlwaysAcceptBaseBranch(true)
                .setDisableBranchBaseline(true)
                .setScrollAndStitchScreenshots(true)
                .setDisableCORS(true)
                .setIframes(true)
                .setIframesOptions(iframesOptions);

        assertEquals("Visual Options Project Name", visualOptions.getProjectName());
        assertEquals("1024x768", visualOptions.getViewportSize());
        assertEquals("baseBranch", visualOptions.getBaseBranch());
        assertEquals(diffOptions, visualOptions.getDiffOptions());
        assertEquals("#some-id, .some-selector", visualOptions.getIgnore());
        assertTrue(visualOptions.getFailOnNewStates());
        assertTrue(visualOptions.getAlwaysAcceptBaseBranch());
        assertTrue(visualOptions.getDisableBranchBaseline());
        assertTrue(visualOptions.getScrollAndStitchScreenshots());
        assertTrue(visualOptions.getDisableCORS());
        assertTrue(visualOptions.getIframes());
        assertEquals(iframesOptions, visualOptions.getIframesOptions());
        assertEquals(SaucePlatform.MAC_HIGH_SIERRA, visualOptions.getSauceOptions().platformName);
        assertEquals(Browser.FIREFOX, visualOptions.getSauceOptions().browserName);
        assertEquals("99", visualOptions.getSauceOptions().browserVersion);
    }

    @Test
    public void parsesCapabilitiesFromVisualOptions() {
        Map<String, Object> diffOptions = new HashMap<>();
        diffOptions.put("structure", true);
        diffOptions.put("layout", true);
        diffOptions.put("style", true);
        diffOptions.put("content", true);
        diffOptions.put("minLayoutPosition", 4);
        diffOptions.put("minLayoutDimension", 10);

        Map<String, Object> iframesOptions = new HashMap<>();
        iframesOptions.put("maxFrames", "Infinity");

        SauceOptions sauceOptions = SauceOptions.firefox()
                .setPlatformName(SaucePlatform.MAC_HIGH_SIERRA)
                .setBrowserVersion("68")
                .setName("Parses Name From Sauce Options")
                .build();

        VisualOptions visualOptions = new VisualOptions(sauceOptions);
        visualOptions
                .setProjectName("My Project")
                .setViewportSize("1024x768")
                .setBranch("branch")
                .setBaseBranch("baseBranch")
                .setDiffOptions(diffOptions)
                .setIgnore("#some-id, .some-selector")
                .setFailOnNewStates(true)
                .setAlwaysAcceptBaseBranch(true)
                .setDisableBranchBaseline(true)
                .setScrollAndStitchScreenshots(true)
                .setDisableCORS(true)
                .setIframes(true)
                .setIframesOptions(iframesOptions);

        MutableCapabilities visualCapabilities = new MutableCapabilities();
        visualCapabilities.setCapability("projectName", "My Project");
        visualCapabilities.setCapability("viewportSize", "1024x768");
        visualCapabilities.setCapability("branch", "branch");
        visualCapabilities.setCapability("baseBranch", "baseBranch");
        visualCapabilities.setCapability("diffOptions", diffOptions);
        visualCapabilities.setCapability("ignore", "#some-id, .some-selector");
        visualCapabilities.setCapability("failOnNewStates", true);
        visualCapabilities.setCapability("alwaysAcceptBaseBranch", true);
        visualCapabilities.setCapability("disableBranchBaseline", true);
        visualCapabilities.setCapability("scrollAndStitchScreenshots", true);
        visualCapabilities.setCapability("disableCORS", true);
        visualCapabilities.setCapability("iframes", true);
        visualCapabilities.setCapability("iframesOptions", iframesOptions);
        visualCapabilities.setCapability("apiKey", System.getenv("SCREENER_API_KEY"));

        MutableCapabilities actualCapabilities = visualOptions.toCapabilities();
        assertEquals(visualCapabilities, actualCapabilities.getCapability("sauce:visual"));

        MutableCapabilities sauceLabsCapabilities = new MutableCapabilities();
        sauceLabsCapabilities.setCapability("name", "Parses Name From Sauce Options");
        sauceLabsCapabilities.setCapability("username", SystemManager.get("SAUCE_USERNAME"));
        sauceLabsCapabilities.setCapability("accessKey", SystemManager.get("SAUCE_ACCESS_KEY"));

        // Dynamic, not worried about testing this here, just need to know it is there
        sauceLabsCapabilities.setCapability("build", visualOptions.getSauceOptions().sauce().getBuild());

        assertEquals(sauceLabsCapabilities, actualCapabilities.getCapability("sauce:options"));
    }

    @Test
    public void requiresTestName() {
        SauceOptions sauceOptions = SauceOptions.chrome().build();
        try {
            new VisualOptions(sauceOptions);
            fail("Expecting InvalidSauceOptionsArgumentException");
        } catch (InvalidSauceOptionsArgumentException e) {
            // Expected result
        }
    }
}
