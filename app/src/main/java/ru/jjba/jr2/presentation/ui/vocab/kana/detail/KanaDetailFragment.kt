package ru.jjba.jr2.presentation.ui.vocab.kana.detail

import android.os.Bundle
import android.view.View
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.base.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.vocab.kana.KanaDetailViewModel

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