package ru.jjba.jr2.presentation.ui.vocab.word.group

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_word_group.*
import org.jetbrains.anko.support.v4.ctx
import org.zakariya.stickyheaders.StickyHeaderLayoutManager
import ru.jjba.jr2.R
import ru.jjba.jr2.presentation.ui.BaseFragment
import ru.jjba.jr2.presentation.ui.util.isVisible
import ru.jjba.jr2.presentation.ui.util.restoreLayoutState
import ru.jjba.jr2.presentation.viewmodel.vocab.word.WordGroupViewModel

class WordGroupFragment : BaseFragment<WordGroupViewModel>() {
    override var viewModel = WordGroupViewModel()
    override val layoutRes = R.layout.fragment_word_group
    override val titleDefault
        get() = getString(R.string.word_group_fragment_title)

    private val wordGroupListAdapter = WordGroupAdapter()
    private var rvWordCollapseSectionsState: IntArray by instanceState(intArrayOf())
    private var rvWordGroupState: Parcelable? by instanceState()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBottomNavigation(false)
    }

    override fun initContent() {
        rvWordGroup.apply {
            setHasFixedSize(true)
            layoutManager = StickyHeaderLayoutManager()
            adapter = wordGroupListAdapter.apply {
                onWordGroupItemClicked = viewModel::onWordGroupClick
            }
            addItemDecoration(DividerItemDecoration(ctx, LinearLayoutManager.VERTICAL))
        }
    }

    override fun observeData() = with(viewModel) {
        observeWordGroupSections().observe(viewLifecycleOwner, Observer { sections ->
            wordGroupListAdapter.sections = sections
            restoreInstanceState()
        })
        observeNavToWordListEvent().observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.run { findNavController().navigate(this) }
        })
        observeAreSectionsLoading().observe(viewLifecycleOwner, Observer {
            rvWordGroup.isVisible = !it
            pbWordsLoading.isVisible = it
        })
    }

    override fun saveInstanceState() {
        rvWordCollapseSectionsState = wordGroupListAdapter.collapseSectionState
        rvWordGroupState = rvWordGroup?.layoutManager?.onSaveInstanceState()
    }

    private fun restoreInstanceState() {
        wordGroupListAdapter.collapseSectionState = rvWordCollapseSectionsState
        rvWordGroup.restoreLayoutState(rvWordGroupState)
    }
}