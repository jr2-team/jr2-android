package ru.jjba.jr2.presentation.ui.number.result

import androidx.lifecycle.ViewModelProviders
import org.jetbrains.anko.support.v4.act
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.base.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.number.NumberPracticeSharedViewModel
import ru.jjba.jr2.presentation.viewmodel.number.result.NumberPractiveResultViewModel

class NumberPracticeResultFragment : BaseFragment<NumberPractiveResultViewModel>() {
    private val sharedViewModel: NumberPracticeSharedViewModel by lazy {
        ViewModelProviders.of(act).get(NumberPracticeSharedViewModel::class.java)
    }

    override var viewModel = NumberPractiveResultViewModel()
    override val layoutRes = R.layout.fragment_number_practice_result
    override val titleDefault: String
        get() = "Результат"

    override fun initContent() {
        showBottomNavigation(false)
    }

    override fun observeData() {

    }
}