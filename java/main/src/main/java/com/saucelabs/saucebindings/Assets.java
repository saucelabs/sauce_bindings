package com.saucelabs.saucebindings;

import com.saucelabs.saucerest.TestAsset;
import com.saucelabs.saucerest.model.jobs.JobAssets;
import java.nio.file.Path;

public class Assets {

  private final SauceRest rest;

  public Assets(SauceRest rest) {
    this.rest = rest;
  }

  public JobAssets listAll() {
    return rest.listAssets();
  }

  public void downloadAll(Path path) {
    rest.downloadAllAssets(path);
  }

  public void download(Path path, TestAsset asset) {
    rest.downloadAsset(path, asset);
  }
}
