package io.github.jr2team.jr2android.presentation.activity_splash._viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    val statePublisher = PublishSubject.create<SplashState>()

    init {
        setupDbData()
    }

    private fun setupDbData() = viewModelScope.launch {
        delay(1000L)
    }.invokeOnCompletion {
        statePublisher.onNext(SplashState.NavigateToMainActivity)
    }
}