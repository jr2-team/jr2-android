package io.github.jr2team.jr2android.presentation.ui.vocab.kanji.list

import io.github.jr2team.jr2android.R
import io.github.jr2team.jr2android.presentation.ui.BaseFragment
import io.github.jr2team.jr2android.presentation.viewmodel.vocab.kanji.KanjiListViewModel

class KanjiListFragment : BaseFragment<KanjiListViewModel>() {
    override var viewModel = KanjiListViewModel()
    override val layoutRes = R.layout.fragment_kanji_list
    override val titleDefault
        get() = ""
}