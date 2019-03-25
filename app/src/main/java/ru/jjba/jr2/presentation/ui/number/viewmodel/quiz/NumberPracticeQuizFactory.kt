package ru.jjba.jr2.presentation.ui.number.viewmodel.quiz

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import ru.jjba.jr2.presentation.ui.number.data.NumberPracticeSessionRepository

class NumberPracticeQuizFactory(
        private val sessionRepository: NumberPracticeSessionRepository
) : NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
            NumberPracticeQuizViewModel(sessionRepository) as T
}