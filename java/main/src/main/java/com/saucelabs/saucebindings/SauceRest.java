package com.saucelabs.saucebindings;

import com.saucelabs.saucerest.SauceREST;
import com.saucelabs.saucerest.TestAsset;
import com.saucelabs.saucerest.api.JobsEndpoint;
import com.saucelabs.saucerest.model.jobs.Job;
import com.saucelabs.saucerest.model.jobs.JobAssets;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;
import org.openqa.selenium.remote.SessionId;

public class SauceRest {

  private final String id;
  private final JobsEndpoint jobs;

  public SauceRest(DataCenter dataCenter, SessionId id) {
    this.id = id.toString();
    SauceREST rest =
        new SauceREST(com.saucelabs.saucerest.DataCenter.fromString(dataCenter.name()));
    this.jobs = rest.getJobsEndpoint();
  }

  @SneakyThrows
  public void setResult(Boolean result) {
    jobs.changeResults(id, result);
  }

  public Map<String, String> getCustomData() {
    return getDetails().customData;
  }

  @SneakyThrows
  public void addCustomData(Map<String, String> data) {
    jobs.addCustomData(id, data);
  }

  public List<String> getTags() {
    return getDetails().tags;
  }

  @SneakyThrows
  public void addTags(List<String> tags) {
    jobs.addTags(id, tags);
  }

  @SneakyThrows
  public void changeName(String name) {
    jobs.changeName(id, name);
  }

  @SneakyThrows
  public String getName() {
    return jobs.getJobDetails(id).name;
  }

  @SneakyThrows
  public void changeBuild(String build) {
    jobs.changeBuild(id, build);
  }

  @SneakyThrows
  public String getBuild() {
    return String.valueOf(jobs.getJobDetails(id).build);
  }

  @SneakyThrows
  public void stop() {
    jobs.stopJob(id);
  }

  @SneakyThrows
  public void delete() {
    jobs.deleteJob(id);
  }

  @SneakyThrows
  public JobAssets listAssets() {
    return jobs.listJobAssets(id);
  }

  @SneakyThrows
  public void downloadAllAssets(Path path) {
    jobs.downloadAllAssets(id, path);
  }

  @SneakyThrows
  public void downloadAsset(Path path, TestAsset asset) {
    jobs.downloadJobAsset(id, path, asset);
  }

  @SneakyThrows
  private Job getDetails() {
    return jobs.getJobDetails(id);
  }
}
