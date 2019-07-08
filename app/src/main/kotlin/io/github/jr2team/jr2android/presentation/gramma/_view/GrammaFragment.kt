package io.github.jr2team.jr2android.presentation.gramma._view

import io.github.jr2team.jr2android.R
import io.github.jr2team.jr2android.presentation._base._view.BaseFragment
import io.github.jr2team.jr2android.presentation.gramma._viewmodel.GrammaViewModel

class GrammaFragment : BaseFragment<GrammaViewModel>() {
    override var viewModel = GrammaViewModel()
    override val layoutRes: Int = R.layout.fragment_gramma
    override val titleDefault: String
        get() = getString(R.string.gramma_fragment_title)
}