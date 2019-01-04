package ru.jjba.jr2.presentation.ui.gramma

import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.gramma.GrammaViewModel

class GrammaFragment : BaseFragment<GrammaViewModel>() {
    override var viewModel = GrammaViewModel()
    override val layoutRes: Int = R.layout.fragment_gramma
    override val titleDefault: String
        get() = getString(R.string.gramma_fragment_title)

    override fun initContent() {

    }

    override fun observeData() {

    }
}