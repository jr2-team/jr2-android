package ru.jjba.jr2.presentation.navigation

import ru.terrakok.cicerone.BaseRouter
import ru.terrakok.cicerone.commands.*
import ru.terrakok.cicerone.result.ResultListener
import java.util.*

class DefaultRouter : BaseRouter() {

    private val screenStack: Deque<String> = LinkedList<String>()
    private val resultListener: MutableMap<Int, ResultListener> = hashMapOf()
    private val onExitValidator: MutableMap<String, () -> Boolean> = hashMapOf()

    fun startFlow(flowKey: String, data: Any? = null) {
        executeCommands(StartFlow(flowKey, data))
    }

    fun navigateTo(screenKey: String) {
        navigateTo(screenKey, null)
    }

    fun navigateTo(screenKey: String, data: Any?) {
        if (screenStack.peek() == screenKey) return

        screenStack.push(screenKey)
        executeCommands(Forward(screenKey, data))
    }

    fun newRootScreen(screenKey: String) {
        if (screenStack.peek() == screenKey) return

        screenStack.push(screenKey)
        executeCommands(BackTo(null), Replace(screenKey, null))
    }

    fun finishChain() {
        screenStack.clear()
        executeCommands(BackTo(null), Back())
    }

    fun setResultListener(resultCode: Int, listener: (Any) -> Unit) {
        resultListener[resultCode] = ResultListener(listener)
    }

    fun removeResultListener(resultCode: Int) {
        resultListener.remove(resultCode)
    }

    fun setOnExitValidator(screenKey: String, validator: () -> Boolean) {
        onExitValidator[screenKey] = validator
    }

    fun removeOnExitValidator(screenKey: String) {
        onExitValidator.remove(screenKey)
    }

    fun exitWithResult(resultCode: Int, result: Any) {
        exit()
        sendResult(resultCode, result)
    }

    fun exit() {
        if (screenStack.isEmpty()) {
            return
        }

        val screenKey = screenStack.peek()
        val result = onExitValidator[screenKey]?.invoke()

        if (result != false) {
            screenStack.pop()
            executeCommands(Back())
        }
    }

    private fun sendResult(resultCode: Int, result: Any): Boolean {
        val listener = resultListener[resultCode] ?: return false
        listener.onResult(result)
        return true
    }
}