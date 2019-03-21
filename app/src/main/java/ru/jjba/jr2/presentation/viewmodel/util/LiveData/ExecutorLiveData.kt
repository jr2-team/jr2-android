package ru.jjba.jr2.presentation.viewmodel.util.LiveData

import androidx.lifecycle.LiveData

class ExecutorLiveData<T>(
        private val valueBeforeInvocation: T,
        private val valueAfterInvocation: T
) : LiveData<T>() {
    fun execute(f: () -> Unit) {
        value = valueBeforeInvocation
        f.invoke()
        value = valueAfterInvocation
    }
}