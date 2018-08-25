package ru.jjba.jr2.presentation.navigation

import android.app.Activity
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import android.widget.Toast
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace

abstract class DefaultNavigator(
        private val activity: FragmentActivity,
        containerId: Int
) : SupportFragmentNavigator(activity.supportFragmentManager, containerId) {

    fun setLaunchScreen(screenKey: String, data: Any? = null) {
        val commandChain: Array<Command> = arrayOf(BackTo(null), Replace(screenKey, data))
        applyCommands(commandChain)
    }

    override fun applyCommand(command: Command?) {
        when (command) {
            is StartFlow -> startFlow(command.screenKey, command.transitionData)
            else -> super.applyCommand(command)
        }
    }

    protected open fun startFlow(flowKey: String, date: Any? = null) {
        val flow = Flow.fromFlowKey(flowKey)
        activity.startActivityForResult(flow.getIntent(activity), getRequestCodeForFlow(flowKey))
    }

    open fun getRequestCodeForFlow(flowKey: String): Int = -1

    override fun exit() {
        activity.setResult(Activity.RESULT_CANCELED)
        activity.finish()
    }

    override fun showSystemMessage(message: String?) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}