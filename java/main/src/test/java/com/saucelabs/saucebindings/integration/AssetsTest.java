package com.saucelabs.saucebindings.integration;

import com.saucelabs.saucebindings.SauceOptions;
import com.saucelabs.saucebindings.SauceSession;
import com.saucelabs.saucerest.TestAsset;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AssetsTest {
    private SauceSession session = new SauceSession();

    @Test
    public void getList() {
        session = new SauceSession(SauceOptions.chrome().setCapturePerformance().build());
        session.start();
        session.stop(true);
        JSONObject assetList = session.assets().getList();

        Assert.assertEquals("log.json", assetList.get("sauce-log"));
        Assert.assertEquals("video.mp4", assetList.get("video"));
        Assert.assertEquals("selenium-server.log", assetList.get("selenium-log"));
    }

    @Test
    public void download() throws IOException {
        session.start();
        session.stop(true);

        String directory = "src/test/assets";
        session.assets().download(TestAsset.SCREENSHOTS, Paths.get(directory, "filename.zip"));
        session.assets().download(TestAsset.SCREENSHOTS, Paths.get(directory));

        Assert.assertTrue(Files.exists(Paths.get(directory, "screenshots.zip")));
        Assert.assertTrue(Files.exists(Paths.get(directory, "filename.zip")));

        FileUtils.cleanDirectory(new File("src/test/assets"));
    }

    @Test
    public void downloadAll() {
        session = new SauceSession(SauceOptions.chrome().setName("Test Demo").setCapturePerformance().build());
        RemoteWebDriver driver = session.start();
        driver.get("https://www.saucedemo.com");
        session.stop(true);
        session.assets().downloadAll(Paths.get("src/test/assets", session.getSessionID()));

//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
//        String prepend = String.format("%s_%s_", session.getSessionID(), format.format(new Date()));
//        session.assets().downloadAll(Paths.get("src/test/assets"), prepend);
    }
}

