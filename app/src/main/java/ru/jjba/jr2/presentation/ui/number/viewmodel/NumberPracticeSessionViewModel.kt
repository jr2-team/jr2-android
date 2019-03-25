package ru.jjba.jr2.presentation.ui.number.viewmodel

import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import kotlinx.coroutines.launch
import ru.jjba.jr2.presentation.ui.number.data.NumberPracticeSessionRepository
import ru.jjba.jr2.presentation.ui.number.view.settings.NumberPracticeSettingsFragmentDirections
import ru.jjba.jr2.presentation.viewmodel.base.BaseViewModel
import ru.jjba.jr2.presentation.viewmodel.util.LiveData.ExecutorLiveData
import ru.jjba.jr2.presentation.viewmodel.util.LiveData.SingleLiveEvent

class NumberPracticeSessionViewModel : BaseViewModel() {
    private var sessionRepositoryInstance: NumberPracticeSessionRepository? = null

    private val isLoadingLiveData = ExecutorLiveData(true, false)
    fun observeIsLoading(): LiveData<Boolean> = isLoadingLiveData

    private val navToQuizLiveData = SingleLiveEvent<NavDirections>()
    fun observeNavToQuiz(): LiveData<NavDirections> = navToQuizLiveData

    fun initSession() = launch {
        clearPreviousSession()
        isLoadingLiveData.execute { sessionPrepare() }
        sessionLaunch()
    }

    fun getSessionRepository(): NumberPracticeSessionRepository {
        if (sessionRepositoryInstance == null) {
            sessionRepositoryInstance = NumberPracticeSessionRepository.restoreSession()
        }
        return sessionRepositoryInstance!!
    }

    private fun sessionPrepare() {
        sessionRepositoryInstance = NumberPracticeSessionRepository()
    }

    private fun sessionLaunch() {
        val direction = NumberPracticeSettingsFragmentDirections.actionNavNumberPracticeSettingsToQuiz()
        navToQuizLiveData.postValue(direction)
    }

    private fun sessionFinish() {

    }

    private fun clearPreviousSession() {

    }
}