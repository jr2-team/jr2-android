package io.github.jr2team.jr2android.presentation.vocabulary.word.list._view

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.jr2team.jr2android.R
import io.github.jr2team.jr2android.common.extensions.isVisible
import io.github.jr2team.jr2android.common.extensions.restoreLayoutState
import io.github.jr2team.jr2android.presentation._base._view.BaseFragment
import io.github.jr2team.jr2android.presentation.vocabulary.word.list._view.list_adapter.WordListAdapter
import io.github.jr2team.jr2android.presentation.vocabulary.word.list._viewmodel.WordListViewModel
import kotlinx.android.synthetic.main.fragment_word_list.*
import org.jetbrains.anko.support.v4.ctx

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

    override fun observeLiveData() = with(viewModel) {
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