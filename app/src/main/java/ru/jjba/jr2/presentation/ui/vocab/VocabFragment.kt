package ru.jjba.jr2.presentation.ui.vocab

import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.vocab.VocabViewModel

class VocabFragment : BaseFragment<VocabViewModel>() {
    override var viewModel = VocabViewModel()
    override val layoutRes: Int = R.layout.fragment_vocab
    override val titleDefault: String
        get() = getString(R.string.vocab_fragment_title)

    override fun initContent() {

    }

    override fun observeData() {

    }
}