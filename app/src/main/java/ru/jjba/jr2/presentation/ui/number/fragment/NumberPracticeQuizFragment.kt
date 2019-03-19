package ru.jjba.jr2.presentation.ui.number.fragment

import androidx.lifecycle.ViewModelProviders
import org.jetbrains.anko.support.v4.act
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.number.NumberPractiveQuizViewModel
import ru.jjba.jr2.presentation.viewmodel.number.NumberPractiveSharedViewModel

class NumberPracticeQuizFragment : BaseFragment<NumberPractiveQuizViewModel>() {
        private val sharedViewModel: NumberPractiveSharedViewModel by lazy {
            ViewModelProviders.of(act).get(NumberPractiveSharedViewModel::class.java)
        }

    override var viewModel = NumberPractiveQuizViewModel()
    override val layoutRes = 0//R.layout.fragment_number_practice_quiz
    override val titleDefault: String
        get() = "Тест"// Should by empty

    override fun initContent() {

    }

    override fun observeData() {

    }
}