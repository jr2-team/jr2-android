package io.github.jr2team.jr2android.presentation.vocabulary.kanji.list._view

import io.github.jr2team.jr2android.R
import io.github.jr2team.jr2android.presentation._base._view.BaseFragment
import io.github.jr2team.jr2android.presentation.vocabulary.kanji.list._viewmodel.KanjiListViewModel

class KanjiListFragment : BaseFragment<KanjiListViewModel>() {
    override var viewModel = KanjiListViewModel()
    override val layoutRes = R.layout.fragment_kanji_list
    override val titleDefault
        get() = ""
}