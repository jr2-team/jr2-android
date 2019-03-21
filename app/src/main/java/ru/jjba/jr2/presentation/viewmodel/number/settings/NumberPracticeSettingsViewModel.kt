package ru.jjba.jr2.presentation.viewmodel.number.settings

import android.content.SharedPreferences
import ru.jjba.jr2.presentation.viewmodel.util.LiveData.SharedPreferenceLiveData
import ru.jjba.jr2.presentation.viewmodel.base.BaseViewModel

class NumberPracticeSettingsViewModel(
        private val sharedPreferences: SharedPreferences
) : BaseViewModel() {
    // TODO: Create repository to access shared pref
    // Count and allowed value range
    val numberQuizCountPref = SharedPreferenceLiveData(sharedPreferences, NUMBER_QUIZ_COUNT, 10)
    val numberQuizNumberRndLowerBound = SharedPreferenceLiveData(sharedPreferences, "", 1)
    val numberQuizNumberRndUpperBound = SharedPreferenceLiveData(sharedPreferences, "", 1000)
    // Types settings
    val numberQuizUseTypeHiroganaKanji = SharedPreferenceLiveData(sharedPreferences, "", 1)
    val numberQuizUseTypeHiroganaNumber = SharedPreferenceLiveData(sharedPreferences, "", 1)
    val numberQuizUseTypeKanjiHirogana = SharedPreferenceLiveData(sharedPreferences, "", 1)
    val numberQuizUseTypeKanjiNumber = SharedPreferenceLiveData(sharedPreferences, "", 1)
    val numberQuizUseTypeNumberHirogana = SharedPreferenceLiveData(sharedPreferences, "", 1)
    val numberQuizUseTypeNumberKanji = SharedPreferenceLiveData(sharedPreferences, "", 1)
    // Other settings
    val numberQuizNeedToPerformTap = SharedPreferenceLiveData(sharedPreferences, "", false)
    val numberQuizIsTimeOutHappen = SharedPreferenceLiveData(sharedPreferences, "", false)
    val numberQuizRetryWrong = SharedPreferenceLiveData(sharedPreferences, "", false)

    fun onSaveNumberQuizCount(quizCount: Int?) = with(sharedPreferences.edit()) {
        putInt(NUMBER_QUIZ_COUNT, quizCount ?: 100)
        apply()
    }

    // TODO: Find a better solution to store value practice types
    fun onSaveNumberQuizTypes(checkState: String) {
        with(sharedPreferences.edit()) {
            putString(NUMBER_QUIZ_TYPES, checkState)
            apply()
        }
    }

    fun onSaveNumberQuizOtherSettings(
            tapBeforeShowVariants: Boolean,
            throwTimeOut: Boolean
    ) {

    }

    companion object {
        private const val NUMBER_QUIZ_COUNT = "NumberQuizCount"
        private const val NUMBER_QUIZ_TYPES = "NumberQuizTypes"
    }
}