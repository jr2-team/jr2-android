package ru.jjba.jr2.presentation.ui.vocab.kana.list

import android.os.Bundle
import android.view.View
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.base.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.vocab.kana.KanaListViewModel

class KanaListFragment : BaseFragment<KanaListViewModel>() {
    override var viewModel = KanaListViewModel()
    override val layoutRes: Int = R.layout.fragment_kana_list
    override val titleDefault
        get() = getString(R.string.kana_list_fragment_title)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBottomNavigation(false)
    }
}