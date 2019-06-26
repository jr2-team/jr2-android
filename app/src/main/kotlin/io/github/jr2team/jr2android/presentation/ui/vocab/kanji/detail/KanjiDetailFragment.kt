package io.github.jr2team.jr2android.presentation.ui.vocab.kanji.detail

import io.github.jr2team.jr2android.R
import io.github.jr2team.jr2android.presentation.ui.BaseFragment
import io.github.jr2team.jr2android.presentation.viewmodel.vocab.kanji.KanjiDetailViewModel

class KanjiDetailFragment : BaseFragment<KanjiDetailViewModel>() {
    override var viewModel = KanjiDetailViewModel()
    override val layoutRes = R.layout.fragment_kanji_detail
    override val titleDefault: String
        get() = ""
}