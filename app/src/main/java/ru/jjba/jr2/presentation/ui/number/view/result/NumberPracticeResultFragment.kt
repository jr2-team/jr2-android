package ru.jjba.jr2.presentation.ui.number.view.result

import androidx.lifecycle.ViewModelProviders
import org.jetbrains.anko.support.v4.act
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.base.BaseFragment
import ru.jjba.jr2.presentation.ui.number.viewmodel.NumberPracticeSessionViewModel
import ru.jjba.jr2.presentation.ui.number.viewmodel.result.NumberPracticeResultViewModel

class NumberPracticeResultFragment : BaseFragment<NumberPracticeResultViewModel>() {
    private val sessionViewModel: NumberPracticeSessionViewModel by lazy {
        ViewModelProviders.of(act).get(NumberPracticeSessionViewModel::class.java)
    }

    override var viewModel = NumberPracticeResultViewModel()
    override val layoutRes = R.layout.fragment_number_practice_result
    override val titleDefault: String
        get() = "Результат"

    override fun initContent() {
        showBottomNavigation(false)
    }

    override fun observeData() {

    }
}