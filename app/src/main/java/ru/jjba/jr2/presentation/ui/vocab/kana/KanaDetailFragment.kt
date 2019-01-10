package ru.jjba.jr2.presentation.ui.vocab.kana

import org.jetbrains.anko.bundleOf
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.vocab.kana.KanaDetailViewModel

class KanaDetailFragment : BaseFragment<KanaDetailViewModel>() {
    override var viewModel = KanaDetailViewModel()
    override val layoutRes: Int = R.layout.fragment_kana_detail
    override val titleDefault: String
        get() = ""

    override fun initContent() {

    }

    override fun observeData() {

    }
}