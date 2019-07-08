package io.github.jr2team.jr2android.presentation.vocabulary.kana.detail._view

import android.os.Bundle
import android.view.View
import io.github.jr2team.jr2android.R
import io.github.jr2team.jr2android.presentation._base._view.BaseFragment
import io.github.jr2team.jr2android.presentation.vocabulary.kana.detail._viewmodel.KanaDetailViewModel

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