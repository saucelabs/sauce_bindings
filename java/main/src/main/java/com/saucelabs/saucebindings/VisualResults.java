package com.saucelabs.saucebindings;

import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Setter @Getter
public class VisualResults {
    Map<String, Object> results;
    private Map<String, Object> totals;

    private final Boolean passed;
    private final String message;
    private final String status;
    private final List<VisualSnapshot> snapshots = new ArrayList<>();
    private final Long totalNew;
    private final Long totalChanged;
    private final Long totalRejected;
    private final Long totalAccepted;
    private final Long total;

    public static VisualResults generate(RemoteWebDriver driver) {
        return new VisualResults(driver);
    }

    private VisualResults(RemoteWebDriver driver) {
        results = (Map<String, Object>) driver.executeScript("/*@visual.end*/");

        this.passed = (Boolean) results.get("passed");
        this.message = (String) results.get("message");
        this.status = (String) results.get("status"); // success, failure, error, timeout, cancelled

        for (Map state : (List<Map>) results.get("states")) {
            snapshots.add(new VisualSnapshot(state));
        }

        this.totals = (Map) results.get("totals");
        this.totalNew = (Long) totals.get("new");
        this.totalChanged = (Long) totals.get("changed");
        this.totalRejected = (Long) totals.get("rejected");
        this.totalAccepted = (Long) totals.get("accepted");
        this.total = (Long) totals.get("all");
    }

    public String getSummary() {
        String disposition = getPassed() ? getStatus() : getMessage();
        return "Totals: " + getTotals() + "; Disposition: " + disposition + "\n";
    }
}
