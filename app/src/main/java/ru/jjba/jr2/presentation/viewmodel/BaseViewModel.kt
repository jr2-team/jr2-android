package ru.jjba.jr2.presentation.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    internal val compDisp = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compDisp.clear()
    }
}