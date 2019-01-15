package ru.jjba.jr2.presentation.viewmodel.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SplashViewModel : ViewModel() {
    // TODO: Найти лучший способ роутить между активностями через ViewModel
    private val isAllowedNavToMain = MutableLiveData<Boolean>()

    private val tasks = CompositeDisposable()

    fun onSetupDb() {
        emptyList<Unit>().toObservable()
                .delay(2, TimeUnit.SECONDS)
                .doOnComplete {
                    isAllowedNavToMain.postValue(true)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy()
                .addTo(tasks)
    }

    fun observeIsAllowedNavToMain(): LiveData<Boolean> = isAllowedNavToMain

    override fun onCleared() {
        tasks.clear()
        super.onCleared()
    }
}