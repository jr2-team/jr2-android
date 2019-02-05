package ru.jjba.jr2.presentation.viewmodel.util

import ru.jjba.jr2.presentation.viewmodel.main.MainViewModelFactory
import ru.jjba.jr2.presentation.viewmodel.splash.SplashViewModelFactory

object InjectorUtil {
    fun provideSplashViewModel(): SplashViewModelFactory {
        return SplashViewModelFactory()
    }

    fun provideMainViewModel(): MainViewModelFactory {
        return MainViewModelFactory()
    }
}