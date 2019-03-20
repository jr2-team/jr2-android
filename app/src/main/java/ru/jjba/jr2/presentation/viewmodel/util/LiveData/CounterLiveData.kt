package ru.jjba.jr2.presentation.viewmodel.util.LiveData

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CounterLiveData(
        override val coroutineContext: CoroutineContext,
        private val counterDelay: Long = 1000L,
        initialValue: Int = 0
) : LiveData<Int>(), CoroutineScope {
    private var job: Job = Job()

    init {
        coroutineContext + job
        value = initialValue
    }

    fun changeCounterState(isPauseRequested: Boolean) {
        if (isPauseRequested) {
            job.cancel()
        } else {
            if (hasActiveObservers()) job = launch { launchSessionTimer() }
        }
    }

    override fun onInactive() {
        super.onInactive()
        job.cancel()
    }

    private suspend fun launchSessionTimer() {
        while (true) {
            delay(counterDelay)
            value = value?.plus(1)
        }
    }
}