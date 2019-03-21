package ru.jjba.jr2.presentation.ui.number.quiz

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_number_practice_quiz.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.act
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.base.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.number.NumberPracticeSessionRepository
import ru.jjba.jr2.presentation.viewmodel.number.NumberPracticeSharedViewModel
import ru.jjba.jr2.presentation.viewmodel.number.quiz.NumberPracticeQuizViewModel
import ru.jjba.jr2.presentation.viewmodel.util.observe

class NumberPracticeQuizFragment : BaseFragment<NumberPracticeQuizViewModel>() {
    private val sharedViewModel: NumberPracticeSharedViewModel by lazy {
        ViewModelProviders.of(act).get(NumberPracticeSharedViewModel::class.java)
    }
    override var viewModel = NumberPracticeQuizViewModel()
    override val layoutRes = R.layout.fragment_number_practice_quiz
    override val titleDefault: String
        get() = "Тест"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.pullNextQuiz()
    }

    override fun initContent() {
        showBottomNavigation(false)
        view?.onClick {
            sharedViewModel.pullNextQuiz()
            sharedViewModel.sessionTimer.changeCounterState(true)
        }
    }

    override fun observeData() {
        observe(sharedViewModel.observeNavigateToResult()) {
            it.getContentIfNotHandled()?.run { findNavController().navigate(this) }
        }
        observe(sharedViewModel.sessionTimer) {
            val minutes: Int = it / 60
            val seconds = it % 60
            tvNumberPracticeQuizSessionTimer.text = "$minutes:$seconds"
        }
        observe(sharedViewModel.observeCurrentQuiz()) {
            viewModel.onNextQuiz(it)
        }

        observe(viewModel.observeQuestion()) { askedNumber ->
            tvNumberPracticeQuizAskedNumber.text = askedNumber
        }
    }
}