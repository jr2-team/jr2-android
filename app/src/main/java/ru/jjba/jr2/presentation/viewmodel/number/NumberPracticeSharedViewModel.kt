package ru.jjba.jr2.presentation.viewmodel.number

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import kotlinx.coroutines.launch
import ru.jjba.jr2.presentation.ui.number.PracticeNumberQuizType
import ru.jjba.jr2.presentation.ui.number.PracticeNumberSingleQuiz
import ru.jjba.jr2.presentation.ui.number.quiz.NumberPracticeQuizFragmentDirections
import ru.jjba.jr2.presentation.ui.number.settings.NumberPracticeSettingsFragmentDirections
import ru.jjba.jr2.presentation.util.JpNumber
import ru.jjba.jr2.presentation.viewmodel.base.BaseViewModel
import ru.jjba.jr2.presentation.viewmodel.util.LiveData.CounterLiveData
import ru.jjba.jr2.presentation.viewmodel.util.LiveData.ExecutorLiveData
import ru.jjba.jr2.presentation.viewmodel.util.ViewModelEvent
import kotlin.random.Random

class NumberPracticeSharedViewModel : BaseViewModel() {
    val sessionTimer = CounterLiveData(coroutineContext)

    private var quizzesPool = emptyList<PracticeNumberSingleQuiz>()
    private val currentQuiz = MutableLiveData<PracticeNumberSingleQuiz>()
    private var currentPoolIdx = -1
    fun observeCurrentQuiz(): LiveData<PracticeNumberSingleQuiz> = currentQuiz

    private val navigateToQuiz = MutableLiveData<ViewModelEvent<NavDirections>>()
    fun observeNavigateToQuiz(): LiveData<ViewModelEvent<NavDirections>> = navigateToQuiz
    private val navigateToResult = MutableLiveData<ViewModelEvent<NavDirections>>()
    fun observeNavigateToResult(): LiveData<ViewModelEvent<NavDirections>> = navigateToResult

    val isLoaderShown = ExecutorLiveData(true, false)

    init {
        sessionRepositoryInstance = NumberPracticeSessionRepository()
    }

    fun initSession() = launch {
        clearPreviousSession()
        isLoaderShown.execute { onSessionPrepare() }
        onSessionLaunch()
    }

    fun pullNextQuiz() {
        quizzesPool.getOrNull(++currentPoolIdx)?.let {
            currentQuiz.postValue(it)
        } ?: onSessionFinish()
    }

    private fun onSessionPrepare() {
        //TODO: set all data to initial state except quizzesPool if isRepeatRequested == false

    }

    private fun onSessionLaunch() {
        val direction = NumberPracticeSettingsFragmentDirections
                .actionNavNumberPracticeSettingsToQuiz()
        navigateToQuiz.postValue(ViewModelEvent(direction))
        sessionTimer.changeCounterState(false)
    }

    private fun onSessionFinish() {
        val direction = if (/*givenAnswers.count() > 0*/false) {
            NumberPracticeQuizFragmentDirections.actionNavNumberPracticeQuizToResult()
        } else {
            NumberPracticeQuizFragmentDirections.actionNavNumberPracticeQuizToSettingsPop()
        }
        navigateToResult.postValue(ViewModelEvent(direction))
    }

    private fun clearPreviousSession() {
        currentPoolIdx = -1
    }

    companion object {
        lateinit var sessionRepositoryInstance: NumberPracticeSessionRepository
    }
}