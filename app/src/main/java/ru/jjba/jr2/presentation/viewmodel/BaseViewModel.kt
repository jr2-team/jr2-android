package ru.jjba.jr2.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {
    private var viewModelJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = viewModelJob + Main

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}