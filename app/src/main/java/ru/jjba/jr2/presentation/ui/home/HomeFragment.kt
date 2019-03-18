package ru.jjba.jr2.presentation.ui.home

import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.home.HomeViewModel

class HomeFragment : BaseFragment<HomeViewModel>() {
    override var viewModel = HomeViewModel()
    override val layoutRes: Int = R.layout.fragment_home
    override val titleDefault: String
        get() = getString(R.string.main_fragment_title)

    override fun initContent() {
        showMainToolbar(false)

        btnNumberPractice.onClick {
            findNavController().navigate(HomeFragmentDirections.actionHomeToNumberPractice())
        }
    }
}