package io.github.jr2team.jr2android.presentation.vocabulary.word.group._view

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.jr2team.jr2android.R
import io.github.jr2team.jr2android.common.extensions.isVisible
import io.github.jr2team.jr2android.common.extensions.restoreLayoutState
import io.github.jr2team.jr2android.common.extensions.subscribe
import io.github.jr2team.jr2android.presentation._base._view.BaseFragment
import io.github.jr2team.jr2android.presentation.vocabulary.word.group._view.list_adapter.WordGroupAdapter
import io.github.jr2team.jr2android.presentation.vocabulary.word.group._viewmodel.WordGroupEvent
import io.github.jr2team.jr2android.presentation.vocabulary.word.group._viewmodel.WordGroupState
import io.github.jr2team.jr2android.presentation.vocabulary.word.group._viewmodel.WordGroupViewModel
import kotlinx.android.synthetic.main.fragment_word_group.*
import org.jetbrains.anko.support.v4.ctx
import org.zakariya.stickyheaders.StickyHeaderLayoutManager

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
        viewModel.onEvent(WordGroupEvent.OnGetSectionsRequested)
    }

    override fun initContent() {
        rvWordGroup.apply {
            setHasFixedSize(true)
            layoutManager = StickyHeaderLayoutManager()
            adapter = wordGroupListAdapter.apply {
                onWordGroupItemClicked = viewModel::onEvent
            }
            addItemDecoration(DividerItemDecoration(ctx, LinearLayoutManager.VERTICAL))
        }
    }

    override fun subscribeOnRx() {
        compositeDisposable.subscribe(viewModel.statePublisher) { state ->
            when (state) {
                is WordGroupState.NavigateToWordListFragment -> {
                    findNavController().navigate(state.direction)
                }
                is WordGroupState.GetWordSectionsLoading -> {
                    rvWordGroup.isVisible = !state.isLoading
                    pbWordsLoading.isVisible = state.isLoading
                }
                is WordGroupState.GetWordSectionsSucceeded -> {
                    wordGroupListAdapter.sections = state.sections
                    restoreInstanceState()
                }
            }
        }
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