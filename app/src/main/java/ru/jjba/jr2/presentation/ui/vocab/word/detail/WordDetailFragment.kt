package ru.jjba.jr2.presentation.ui.vocab.word.detail

import android.os.Bundle
import android.view.View
import org.jetbrains.anko.bundleOf
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.vocab.word.WordDetailViewModel
import ru.jjba.jr2.presentation.viewmodel.vocab.word.WordListViewModel

class WordDetailFragment : BaseFragment<WordDetailViewModel>() {
    override var viewModel = WordDetailViewModel()
    override val layoutRes = R.layout.fragment_word_detail
    override val titleDefault
            get() = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBottomNavigation(false)

        WordDetailFragmentArgs.fromBundle(arguments!!).let {
            //viewModel.setArgs(it.id)
        }
    }

    override fun initContent() {

    }

    override fun observeData() {

    }
}