package io.github.jr2team.jr2android.presentation.ui.vocab.kana.list

import android.os.Bundle
import android.view.View
import io.github.jr2team.jr2android.R
import io.github.jr2team.jr2android.presentation.ui.BaseFragment
import io.github.jr2team.jr2android.presentation.viewmodel.vocab.kana.KanaListViewModel

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