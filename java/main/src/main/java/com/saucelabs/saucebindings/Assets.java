package com.saucelabs.saucebindings;

import com.saucelabs.saucerest.SauceREST;
import com.saucelabs.saucerest.TestAsset;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
public class Assets {

    private final SauceREST sauceREST;
    private final String sessionID;

    public Assets(String sessionID, SauceREST sauceREST) {
        this.sessionID = sessionID;
        this.sauceREST = sauceREST;
    }

    public JSONObject getList() {
        try {
            BufferedInputStream stream = sauceREST.getAvailableAssets(sessionID);
            return new JSONObject(IOUtils.toString(stream, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void download(TestAsset asset, Path path) {
        download(asset, path, "");
    }

    private void download(TestAsset asset, Path path, String prepend) {
        if (!path.getFileName().toString().contains(".")) {
            path = Paths.get(path.toString(), prepend + asset.label);
        }
        String name = path.getFileName().toString();
        String filePath = path.toAbsolutePath().getParent().toString();

        Map<String, Object> availableAssets = getList().toMap();

        switch (asset) {
        case AUTOMATOR_LOG:
            if (!availableAssets.containsValue(asset.label)) {
                throw new SauceAssetNotAvailableException("Automator Log is not available for download");
            }
            sauceREST.downloadAutomatorLog(sessionID, filePath, name);
            break;
        case SAUCE_LOG:
            if (!availableAssets.containsValue(asset.label)) {
                throw new SauceAssetNotAvailableException("Sauce Log is not available for download");
            }
            sauceREST.downloadSauceLabsLog(sessionID, filePath, name);
            break;
        case VIDEO:
            if (!availableAssets.containsValue(asset.label)) {
                throw new SauceAssetNotAvailableException("Video file is not available for download");
            }
            sauceREST.downloadVideo(sessionID, filePath, name);
            break;
        case SELENIUM_LOG:
            if (!availableAssets.containsValue(asset.label)) {
                throw new SauceAssetNotAvailableException("Selenium Log is not available for download");
            }
            sauceREST.downloadServerLog(sessionID, filePath, name);
            break;
        case HAR:
            if (!availableAssets.containsValue(asset.label)) {
                throw new SauceAssetNotAvailableException("HAR File is not available for download");
            }
            sauceREST.downloadHAR(sessionID, filePath, name);
            break;
        case SCREENSHOTS:
            if (!availableAssets.containsKey("screenshots")) {
                throw new SauceAssetNotAvailableException("Screenshots are not available for download");
            }
            sauceREST.downloadScreenshots(sessionID, filePath, name);
            break;
        default:
            throw new UnsupportedOperationException("this library can not download: " + asset.label);
        }
    }

    public void downloadAll(Path path) {
        downloadAll(path, "");
    }

    public void downloadAll(Path path, String prependFile) {
        getList().toMap().values().forEach((assetName) -> {
            // TODO: re-implement so as not to require reverse lookup of enum here
            Arrays.asList(TestAsset.values()).forEach((testAsset) -> {
                if (assetName.equals(testAsset.label)) {
                    try {
                        download(testAsset, path, prependFile);
                    } catch(UnsupportedOperationException e) {
                        System.out.println("WARNING: " + e.getMessage());
                    }
                }
            });
        });
    }

    static class SauceAssetNotAvailableException extends RuntimeException {
        public SauceAssetNotAvailableException(String message) {
            super(message);
        }
    }
}
