package ru.jjba.jr2.presentation.ui.number.quiz

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_number_practice_quiz.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.act
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.base.BaseFragment
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
        sharedViewModel.pollNextQuiz()
        sharedViewModel.onTimerStateChange(false)
    }

    override fun initContent() {
        this.view?.onClick {
            val s = ""
            sharedViewModel.pollNextQuiz()
        }
    }

    override fun observeData() {
        observe(sharedViewModel.observeSessionTime()) {
            tvTimer.text = it.toString()
        }
        observe(sharedViewModel.observeNextQuiz()) {
            viewModel.onNextQuizRequested(it)
        }

        observe(viewModel.observeCorrectVariant()) {
            tvQuestion.text = it
        }
        observe(viewModel.observeVariants()) {
            variants.setAnswers(it)
        }

        observe(variants.observeGivenAnswer()) {
            val s = ""
            sharedViewModel.pollNextQuiz()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            sharedViewModel.onTimerStateChange(false)
        } else {
            sharedViewModel.onTimerStateChange(true)
        }
    }
}