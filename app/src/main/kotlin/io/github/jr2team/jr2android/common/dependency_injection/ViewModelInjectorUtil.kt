package io.github.jr2team.jr2android.common.dependency_injection

import io.github.jr2team.jr2android.presentation.activity_main._viewmodel.MainViewModelFactory
import io.github.jr2team.jr2android.presentation.activity_splash._viewmodel.SplashViewModelFactory

object ViewModelInjectorUtil {
    fun provideSplashViewModel(): SplashViewModelFactory {
        return SplashViewModelFactory()
    }

    fun provideMainViewModel(): MainViewModelFactory {
        return MainViewModelFactory()
    }
}