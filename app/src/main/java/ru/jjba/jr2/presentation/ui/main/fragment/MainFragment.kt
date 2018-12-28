package ru.jjba.jr2.presentation.ui.main.fragment

import androidx.lifecycle.ViewModel
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.main.MainViewModel

class MainFragment : BaseFragment() {
    override var viewModel: ViewModel = MainViewModel()
    override val layoutRes: Int = R.layout.fragment_main
    override val titleDefault: String
        get() = getString(R.string.main_title)

    override fun initContent() {

    }

    override fun observeData() {

    }

}