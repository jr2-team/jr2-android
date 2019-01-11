package ru.jjba.jr2.presentation.ui.vocab.word.group

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_word_group.*
import org.jetbrains.anko.support.v4.ctx
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.viewmodel.vocab.word.WordGroupViewModel
import ru.jjba.jr2.utils.ItemOffsetDecoration

class WordGroupFragment : BaseFragment<WordGroupViewModel>() {
    override var viewModel = WordGroupViewModel()
    override val layoutRes = R.layout.fragment_word_group
    override val titleDefault
        get() = getString(R.string.word_group_fragment_title)

    private val wordGroupListAdapter = WordGroupAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBottomNavigation(false)
    }

    override fun initContent() {
        rvWordGroup.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(ctx)
            adapter = wordGroupListAdapter.apply {
                onItemClicked = viewModel::onWordGroupClick
            }
            addItemDecoration(ItemOffsetDecoration(ctx, R.dimen.rv_offset_between_items))
        }
    }

    override fun observeData() = with(viewModel) {
        getWordGroups().observe(viewLifecycleOwner, Observer { wordGroups ->
            wordGroupListAdapter.wordGroups = wordGroups
        })
        getNavigationToWordList().observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { d -> findNavController().navigate(d) }
        })
    }
}