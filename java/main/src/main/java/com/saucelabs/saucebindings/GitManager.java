package com.saucelabs.saucebindings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GitManager {
    private static String currentBranch = "_default_";

    public static String getCurrentBranch() {
        try {
            Process process = Runtime.getRuntime().exec("git rev-parse --abbrev-ref HEAD");
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            if (!line.contains("fatal:")) {
                currentBranch = line;
            }
        } catch (IOException | InterruptedException e) {
            // Ignore exception and use default
        }
        return currentBranch;
    }
}