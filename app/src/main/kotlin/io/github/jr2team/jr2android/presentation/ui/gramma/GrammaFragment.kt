package io.github.jr2team.jr2android.presentation.ui.gramma

import io.github.jr2team.jr2android.R
import io.github.jr2team.jr2android.presentation.ui.BaseFragment
import io.github.jr2team.jr2android.presentation.viewmodel.gramma.GrammaViewModel

class GrammaFragment : BaseFragment<GrammaViewModel>() {
    override var viewModel = GrammaViewModel()
    override val layoutRes: Int = R.layout.fragment_gramma
    override val titleDefault: String
        get() = getString(R.string.gramma_fragment_title)
}