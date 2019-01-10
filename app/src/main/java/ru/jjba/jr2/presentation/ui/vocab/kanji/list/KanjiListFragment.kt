package ru.jjba.jr2.presentation.ui.vocab.kanji.list

import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.vocab.kanji.KanjiListViewModel

class KanjiListFragment : BaseFragment<KanjiListViewModel>() {
    override var viewModel = KanjiListViewModel()
    override val layoutRes = R.layout.fragment_kanji_list
    override val titleDefault
        get() = "KanjiListFragment"

    override fun initContent() {

    }

    override fun observeData() {

    }
}