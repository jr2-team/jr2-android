package ru.jjba.jr2.presentation.ui.main

import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.main.MainViewModel

class MainFragment : BaseFragment<MainViewModel>() {
    override var viewModel = MainViewModel()
    override val layoutRes: Int = R.layout.fragment_main
    override val titleDefault: String = getString(R.string.main_fragment_title)

    override fun initContent() {
        showToolbar(false)
    }

    override fun observeData() {

    }
}