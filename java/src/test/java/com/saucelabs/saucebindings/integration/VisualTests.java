package com.saucelabs.saucebindings.integration;

import com.saucelabs.saucebindings.OptionForVisualTestingOnlyException;
import com.saucelabs.saucebindings.SauceOptions;
import com.saucelabs.saucebindings.SauceSession;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class VisualTests {
    private RemoteWebDriver webDriver;
    private SauceSession session;

    @After
    public void cleanUp() {
        session.stop(true);
    }

    @Test
    public void defaultStart() {
        SauceOptions sauceOptions = new SauceOptions().visual("SauceBindings");
        session = new SauceSession(sauceOptions);
        session.start();
        assertNotNull(session.getDriver());
    }

    //TODO I don't like the fact that our tests will need to throw
    //specific visual exceptions
    @Test
    public void settingCommonOptions() throws OptionForVisualTestingOnlyException {
        SauceOptions sauceOptions = new SauceOptions().visual("SauceBindings");
        sauceOptions.setName("testName");
        //my biggest problem here is that this error will only be caught at run time
        sauceOptions.setViewportSize("1280x1024");

        session = new SauceSession(sauceOptions);
        webDriver = session.start();
        assertNotNull(session.getDriver());
    }

    @Test
    public void settingUniqueOptions() throws OptionForVisualTestingOnlyException {
        SauceOptions sauceOptions = new SauceOptions().visual("SauceBindings");
        //my biggest problem here is that these errors will only be caught at run time
        // if someone tries to use them without setting .visual();
        sauceOptions.setProjectName("App Name");
        sauceOptions.setViewportSize("1280x1024");

        //weird one
        //sauceOptions.setBranch("testBranch");

        Map<String, Object> diffOptions = new HashMap<>();
        diffOptions.put("structure", true);
        diffOptions.put("layout", true);
        diffOptions.put("style", true);
        diffOptions.put("content", true);
        diffOptions.put("minLayoutPosition", 4);
        diffOptions.put("minLayoutDimension", 10);

        sauceOptions.setDiffOptions(diffOptions);

        List<String> elementsToIgnore = new ArrayList<>();
        elementsToIgnore.add("#foo");
        elementsToIgnore.add(".bar");

        sauceOptions.selectorsToIgnore(elementsToIgnore);
        sauceOptions.failOnNewStates(true);
        sauceOptions.alwaysAcceptBaseBranch(true);
        //sauceOptions.disableBranchBaseline(true);
        sauceOptions.scrollAndStitchScreenshots(true);
        sauceOptions.disableCORS(true);

        session = new SauceSession(sauceOptions);
        webDriver = session.start();
        webDriver.get("https://www.saucedemo.com/");
        session.takeSnapshot("Snapshot name");
        assertNotNull(session.getDriver());
    }
}
