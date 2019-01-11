package ru.jjba.jr2.presentation.ui.vocab.word.list

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_word_list.*
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.vocab.word.WordListViewModel

class WordListFragment : BaseFragment<WordListViewModel>() {
    override var viewModel = WordListViewModel()
    override val layoutRes: Int = R.layout.fragment_word_list
    override val titleDefault: String = ""

    private var wordListAdapter = WordListAdapter()
    private var rvWordLayoutState: Parcelable? by instanceState()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBottomNavigation(false)

        arguments?.let {
            WordListFragmentArgs.fromBundle(it).apply {
                viewModel.setArgs(wordGroupId)
            }
        }
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
        
        if (rvWordLayoutState != null) {
            if (rvWord.itemDecorationCount > 1)
                rvWord.removeItemDecorationAt(rvWord.itemDecorationCount - 1)
            rvWord.layoutManager?.onRestoreInstanceState(rvWordLayoutState)
            toast(rvWord.itemDecorationCount.toString())
        }
    }

    override fun observeData() = with(viewModel) {
        observeWords().observe(viewLifecycleOwner, Observer { words ->
            wordListAdapter.words = words
        })
        observeNavToWordDetailEvent().observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { d -> act.findNavController(R.id.navController).navigate(d) }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (rvWord != null) {
            rvWordLayoutState = rvWord.layoutManager?.onSaveInstanceState()
        }
    }
}