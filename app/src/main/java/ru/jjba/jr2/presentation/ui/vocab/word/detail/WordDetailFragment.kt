package ru.jjba.jr2.presentation.ui.vocab.word.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import org.jetbrains.anko.bundleOf
import org.jetbrains.anko.support.v4.act
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

        arguments?.let {
            WordDetailFragmentArgs.fromBundle(it).apply {
                viewModel.setArgs(wordId)
            }
        }
    }

    override fun initContent() {

    }

    override fun observeData() = with(viewModel) {
        observeWord().observe(viewLifecycleOwner, Observer { word ->
            setTitle(word.value)
        })
    }
}