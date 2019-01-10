package ru.jjba.jr2.presentation.ui.vocab.word.group

import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.vocab.word.WordGroupViewModel

class WordGroupFragment : BaseFragment<WordGroupViewModel>() {
    override var viewModel = WordGroupViewModel()
    override val layoutRes = R.layout.fragment_word_group
    override val titleDefault
        get() = getString(R.string.word_group_fragment_title)

    override fun initContent() {

    }

    override fun observeData() {

    }

}