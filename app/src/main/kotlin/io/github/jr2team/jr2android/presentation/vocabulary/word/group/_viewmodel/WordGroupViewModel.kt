package io.github.jr2team.jr2android.presentation.vocabulary.word.group._viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jr2team.jr2android.domain.entity.Group
import io.github.jr2team.jr2android.domain.usecase.SectionUseCase
import io.github.jr2team.jr2android.presentation.vocabulary.word.group._view.WordGroupFragmentDirections
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.launch

class WordGroupViewModel(
    private val sectionUseCase: SectionUseCase = SectionUseCase()
) : ViewModel() {
    val statePublisher = PublishSubject.create<WordGroupState>()

    fun onEvent(event: WordGroupEvent) = when (event) {
        is WordGroupEvent.OnGetSectionsRequested -> onGetSectionsRequested()
        is WordGroupEvent.OnWordGroupClicked -> onWordGroupClick(event.wordGroup)
    }

    private fun onGetSectionsRequested() {
        statePublisher.onNext(WordGroupState.GetWordSectionsLoading(true))
        viewModelScope.launch {
            val sections = sectionUseCase.getWordSections()
            statePublisher.onNext(WordGroupState.GetWordSectionsSucceeded(sections))
        }.invokeOnCompletion {
            statePublisher.onNext(WordGroupState.GetWordSectionsLoading(false))
        }
    }

    private fun onWordGroupClick(wordGroup: Group) {
        val direction = WordGroupFragmentDirections
            .actionWordGroupToWordList(wordGroupId = wordGroup.id)
        statePublisher.onNext(WordGroupState.NavigateToWordListFragment(direction))
    }
}