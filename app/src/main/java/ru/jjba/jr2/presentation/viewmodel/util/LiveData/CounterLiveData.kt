package ru.jjba.jr2.presentation.viewmodel.util.LiveData

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CounterLiveData(
        override val coroutineContext: CoroutineContext,
        initialValue: Int = 0,
        private val countUntil: Int = Int.MAX_VALUE,
        private val counterDelay: Long = 1000L,
        private val doOnTimeOut: (() -> Unit)? = null
) : LiveData<Int>(), CoroutineScope {
    private var sessionTimerJob = Job()

    init {
        coroutineContext + sessionTimerJob
        value = initialValue
    }

    fun changeCounterState(isPaused: Boolean) {
        when {
            isPaused -> sessionTimerJob.cancel()
            !isPaused -> sessionTimerJob = launch { launchSessionTimer() }
        }
    }

    private suspend fun launchSessionTimer() {
        while ((value as Int) < countUntil) {
            delay(counterDelay)
            value = value?.plus(1)
        }
        doOnTimeOut?.invoke()
    }
}