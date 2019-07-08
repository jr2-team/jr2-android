package io.github.jr2team.jr2android.presentation.vocabulary.kanji.detail._view

import io.github.jr2team.jr2android.R
import io.github.jr2team.jr2android.presentation._base._view.BaseFragment
import io.github.jr2team.jr2android.presentation.vocabulary.kanji.detail._viewmodel.KanjiDetailViewModel

class KanjiDetailFragment : BaseFragment<KanjiDetailViewModel>() {
    override var viewModel = KanjiDetailViewModel()
    override val layoutRes = R.layout.fragment_kanji_detail
    override val titleDefault: String
        get() = ""
}