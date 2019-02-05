package ru.jjba.jr2.presentation.ui.vocab.word.list

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_word_list.*
import org.jetbrains.anko.support.v4.ctx
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.ui.util.isVisible
import ru.jjba.jr2.presentation.ui.util.restoreLayoutState
import ru.jjba.jr2.presentation.viewmodel.vocab.word.WordListViewModel

class WordListFragment : BaseFragment<WordListViewModel>() {
    override var viewModel = WordListViewModel()
    override val layoutRes: Int = R.layout.fragment_word_list
    override val titleDefault: String
        get() = ""

    private val args by navArgs<WordListFragmentArgs>()
    private var wordListAdapter = WordListAdapter()
    private var rvWordState: Parcelable? by instanceState()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBottomNavigation(false)

        viewModel.setArgs(args.wordGroupId)
    }

    override fun initContent() {
        rvWord.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(ctx)
            addItemDecoration(DividerItemDecoration(ctx, LinearLayoutManager.VERTICAL))
            adapter = wordListAdapter.apply {
                onItemClicked = viewModel::onWordClick
            }
        }
    }

    override fun observeData() = with(viewModel) {
        observeWordGroup().observe(viewLifecycleOwner, Observer { wordGroup ->
            setTitle(wordGroup?.name ?: getString(R.string.word_list_words_are_loading))
        })
        observeWords().observe(viewLifecycleOwner, Observer { words ->
            wordListAdapter.words = words
            rvWord.restoreLayoutState(rvWordState)
        })
        observeNavToWordDetailEvent().observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.run {
                findNavController().navigate(this)
            }
        })
        observeWordsIsLoading().observe(viewLifecycleOwner, Observer {
            pbWordsLoading.isVisible = it
        })
    }

    override fun saveInstanceState() {
        if (rvWord != null) {
            rvWordState = rvWord.layoutManager?.onSaveInstanceState()
        }
    }
}