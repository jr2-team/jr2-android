package ru.jjba.jr2.presentation.ui.vocab.kanji.detail

import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.base.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.vocab.kanji.KanjiDetailViewModel

class KanjiDetailFragment : BaseFragment<KanjiDetailViewModel>() {
    override var viewModel = KanjiDetailViewModel()
    override val layoutRes = R.layout.fragment_kanji_detail
    override val titleDefault: String
        get() = ""
}