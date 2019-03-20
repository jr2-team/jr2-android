package ru.jjba.jr2.presentation.viewmodel.util

import android.content.SharedPreferences
import ru.jjba.jr2.presentation.viewmodel.main.MainViewModelFactory
import ru.jjba.jr2.presentation.viewmodel.number.settings.NumberPracticeSettingsFactory
import ru.jjba.jr2.presentation.viewmodel.splash.SplashViewModelFactory

object InjectorUtil {
    fun provideSplashViewModel(): SplashViewModelFactory {
        return SplashViewModelFactory()
    }

    fun provideMainViewModel(): MainViewModelFactory {
        return MainViewModelFactory()
    }

    fun provideNumberPracticeSettingsViewModel(
            sharedPreferences: SharedPreferences
    ): NumberPracticeSettingsFactory {
        return NumberPracticeSettingsFactory(sharedPreferences)
    }
}