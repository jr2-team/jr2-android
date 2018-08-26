package ru.jjba.jr2.presentation.navigation

import android.content.Context
import android.content.Intent
import ru.jjba.jr2.presentation.ui.main.activity.MainActivity

enum class Flow(val title: String) {
    MAIN("screen main") {
        override fun getIntent(context: Context) = Intent(context, MainActivity::class.java).also {
            // вместо finish()
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
    };

    abstract fun getIntent(context: Context): Intent

    companion object {
        fun fromFlowKey(flowKey: String): Flow = when (flowKey) {
            MAIN.title -> MAIN
            else -> throw IllegalArgumentException("No such flowKey")
        }
    }
}