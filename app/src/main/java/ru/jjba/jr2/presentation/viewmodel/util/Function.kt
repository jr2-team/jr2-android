package ru.jjba.jr2.presentation.viewmodel.util

import androidx.lifecycle.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlin.coroutines.coroutineContext

fun <T : ViewModel> T.createFactory(): ViewModelProvider.Factory {
    val viewModel = this
    return object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModel as T
    }
}

fun <T> LifecycleOwner.observe(
        liveData: LiveData<T>,
        action: (t: T) -> Unit
) {
    liveData.observe(this, Observer {
        it?.let { t ->
            action(t)
        }
    })
}

fun <T : Any?> MutableLiveData<T>.defaultValue(initialValue: T) = apply {
    setValue(initialValue)
}

suspend fun withLoader(loader: Job, f: suspend () -> Unit) = with(coroutineContext) {
    loader.start()
    f.invoke()
    loader.cancelAndJoin()
}