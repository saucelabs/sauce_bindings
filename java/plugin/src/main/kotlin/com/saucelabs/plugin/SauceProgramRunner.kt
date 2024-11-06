package com.saucelabs.plugin

import com.intellij.execution.ExecutorRegistry
import com.intellij.execution.ProgramRunnerUtil
import com.intellij.execution.RunManager
import com.intellij.execution.RunnerAndConfigurationSettings
import com.intellij.execution.application.ApplicationConfiguration
import com.intellij.execution.configurations.RunProfile
import com.intellij.execution.junit.JUnitConfiguration
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.execution.runners.ExecutionEnvironmentBuilder
import com.intellij.execution.runners.ProgramRunner
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.ui.Messages
import java.util.concurrent.ExecutionException


class SauceProgramRunner : ProgramRunner<Nothing> {
    private val logger = Logger.getInstance(SauceProgramRunner::class.java)

    override fun getRunnerId(): String = "SauceRunner"

    override fun canRun(executorId: String, profile: RunProfile): Boolean {
        return executorId == SauceExecutor.EXECUTOR_ID
    }

    @Throws(ExecutionException::class)
    override fun execute(environment: ExecutionEnvironment) {
        val project = environment.project
        val testName = environment.runProfile.name  // Get the test or class name

        // Find the Run Configuration for the test
        val runManager = RunManager.getInstance(project)
        val runConfig: RunnerAndConfigurationSettings? = runManager.allSettings.find {
            it.name == testName  // Match based on test name; adjust to be more specific if needed
        }

        if (runConfig == null) {
            Messages.showMessageDialog(project, "Test configuration not found for $testName", "Error", Messages.getErrorIcon())
            return
        }

        // Get the "Run" executor
        val executor = ExecutorRegistry.getInstance().getExecutorById("Run")
        if (executor == null) {
            Messages.showMessageDialog(project, "Run executor not found", "Error", Messages.getErrorIcon())
            return
        }

        // Modify environment variables or system properties if the configuration supports it
        when (val config = runConfig.configuration) {
            is ApplicationConfiguration -> {
                logger.info("ApplicationConfiguration; setting environment variable to false")
                config.envs["SAUCE_ENABLED"] = "true"
            }
            is JUnitConfiguration -> {
                logger.info("JUnitConfiguration; setting environment variable to false")
                config.envs["SAUCE_ENABLED"] = "true"

            }
            else -> {
                logger.info(config.type.toString() + "; cannot set environment variable")

                // Optional: Handle unsupported configuration types
                Messages.showMessageDialog(
                    project,
                    "Environment variables not supported for this configuration type.",
                    "Warning",
                    Messages.getWarningIcon()
                )
                return
            }
        }

        // Build a new ExecutionEnvironment for the found Run Configuration
        val newEnvironment = ExecutionEnvironmentBuilder.create(executor, runConfig.configuration)
            .build()

        // Execute the configuration to actually run the test
        ProgramRunnerUtil.executeConfiguration(newEnvironment, true, true)
    }
}