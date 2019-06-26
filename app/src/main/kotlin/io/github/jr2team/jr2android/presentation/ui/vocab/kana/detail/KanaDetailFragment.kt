package io.github.jr2team.jr2android.presentation.ui.vocab.kana.detail

import android.os.Bundle
import android.view.View
import io.github.jr2team.jr2android.R
import io.github.jr2team.jr2android.presentation.ui.BaseFragment
import io.github.jr2team.jr2android.presentation.viewmodel.vocab.kana.KanaDetailViewModel

class KanaDetailFragment : BaseFragment<KanaDetailViewModel>() {
    override var viewModel = KanaDetailViewModel()
    override val layoutRes: Int = R.layout.fragment_kana_detail
    override val titleDefault: String
        get() = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBottomNavigation(false)
    }
}