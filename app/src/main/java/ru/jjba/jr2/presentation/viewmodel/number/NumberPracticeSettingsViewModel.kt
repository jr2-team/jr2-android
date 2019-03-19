package ru.jjba.jr2.presentation.viewmodel.number

import android.content.SharedPreferences
import ru.jjba.jr2.data.sharedpref.LiveSharedPreference
import ru.jjba.jr2.presentation.viewmodel.BaseViewModel

class NumberPracticeSettingsViewModel(
        private val sharedPreferences: SharedPreferences
) : BaseViewModel() {
    val numberQuizCountPref = LiveSharedPreference(sharedPreferences, NUMBER_QUIZ_COUNT, 100)

    fun onSaveNumberQuizCount(quizCount: Int?) = with(sharedPreferences.edit()) {
        putInt(NUMBER_QUIZ_COUNT, quizCount ?: 100)
        apply()
    }

    fun onSaveNumberQuizTypes(quizTypes: List<String>) {

    }

    fun onSaveNumberQuizOtherSettings(
            tapBeforeShowVariants: Boolean,
            throwTimeOut: Boolean
    ) {

    }

    companion object {
        private val NUMBER_QUIZ_COUNT = "NumberQuizCount"
    }
}