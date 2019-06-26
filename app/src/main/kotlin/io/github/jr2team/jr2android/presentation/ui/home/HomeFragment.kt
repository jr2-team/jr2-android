package io.github.jr2team.jr2android.presentation.ui.home

import io.github.jr2team.jr2android.R
import io.github.jr2team.jr2android.presentation.ui.BaseFragment
import io.github.jr2team.jr2android.presentation.viewmodel.home.HomeViewModel

class HomeFragment : BaseFragment<HomeViewModel>() {
    override var viewModel = HomeViewModel()
    override val layoutRes: Int = R.layout.fragment_home
    override val titleDefault: String
        get() = getString(R.string.main_fragment_title)

    override fun initContent() {
        showMainToolbar(false)
    }
}