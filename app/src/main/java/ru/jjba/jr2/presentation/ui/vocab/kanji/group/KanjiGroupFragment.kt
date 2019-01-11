package ru.jjba.jr2.presentation.ui.vocab.kanji.group

import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.vocab.kanji.KanjiGroupViewModel

class KanjiGroupFragment : BaseFragment<KanjiGroupViewModel>() {
    override var viewModel = KanjiGroupViewModel()
    override val layoutRes = R.layout.fragment_kanji_group
    override val titleDefault: String
        get() = getString(R.string.kanji_group_fragment_title)

    override fun initContent() {

    }

    override fun observeData() {

    }
}