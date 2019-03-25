package ru.jjba.jr2.presentation.ui.number.viewmodel.settings

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory

@Suppress("UNCHECKED_CAST")
class NumberPracticeSettingsFactory(
        private val sharedPreferences: SharedPreferences
) : NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
            NumberPracticeSettingsViewModel(sharedPreferences) as T
}