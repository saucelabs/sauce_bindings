package com.saucelabs.plugin

import com.intellij.execution.Executor
import com.intellij.icons.AllIcons
import javax.swing.Icon

class SauceExecutor : Executor() {
    companion object {
        const val EXECUTOR_ID = "SauceExecutor"
    }

    override fun getToolWindowId(): String = EXECUTOR_ID

    override fun getToolWindowIcon(): Icon = AllIcons.Actions.Execute

    override fun getIcon(): Icon = AllIcons.Actions.Execute

    override fun getDisabledIcon(): Icon = AllIcons.Plugins.Disabled

    override fun getDescription(): String = "Custom run action for Run/Debug popup"

    override fun getActionName(): String = "Custom Run"

    override fun getId(): String = EXECUTOR_ID

    override fun getStartActionText(): String = "Run on Sauce Labs: "

    override fun getContextActionId(): String = "$EXECUTOR_ID Action"

    override fun getHelpId(): String? = null
}