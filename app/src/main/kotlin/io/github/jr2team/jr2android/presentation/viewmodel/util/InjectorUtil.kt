package io.github.jr2team.jr2android.presentation.viewmodel.util

import io.github.jr2team.jr2android.presentation.viewmodel.main.MainViewModelFactory
import io.github.jr2team.jr2android.presentation.viewmodel.splash.SplashViewModelFactory

object InjectorUtil {
    fun provideSplashViewModel(): SplashViewModelFactory {
        return SplashViewModelFactory()
    }

    fun provideMainViewModel(): MainViewModelFactory {
        return MainViewModelFactory()
    }
}