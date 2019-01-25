package ru.jjba.jr2.presentation.ui.vocab.word.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.vocab.word.WordDetailViewModel

class WordDetailFragment : BaseFragment<WordDetailViewModel>() {
    override var viewModel = WordDetailViewModel()
    override val layoutRes = R.layout.fragment_word_detail
    override val titleDefault
            get() = ""

    private val args by navArgs<WordDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBottomNavigation(false)

        viewModel.setArgs(args.wordId)
    }

    override fun observeData() = with(viewModel) {
        observeWord().observe(viewLifecycleOwner, Observer { word ->
            setTitle(word.value)
        })
    }
}