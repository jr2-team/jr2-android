package io.github.jr2team.jr2android.presentation.vocabulary.word.list._viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jr2team.jr2android.domain.room_entities.entity.Word
import io.github.jr2team.jr2android.domain.usecase.vocabular.word.list.GetWordsUseCase
import io.github.jr2team.jr2android.presentation.vocabulary.word.list._view.WordListFragmentDirections
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.launch

class WordListViewModel : ViewModel() {
    val statePublisher = PublishSubject.create<WordListState>()

    fun onEvent(event: WordListEvent) = when(event) {
        is WordListEvent.OnWordClicked -> onWordClicked(event.word)
        is WordListEvent.OnGetWordListRequested -> onGetWordListRequested(event.wordGroupId)
    }

    private fun onWordClicked(word: Word) {
        val navDirection = WordListFragmentDirections.actionWordListToWordDetail(wordId = word.id)
        statePublisher.onNext(WordListState.NavToWord(navDirection))
    }

    private fun onGetWordListRequested(wordGroupId: Int) {
        statePublisher.onNext(WordListState.GetWordListLoading(true))
        viewModelScope.launch {
            val words = GetWordsUseCase(wordGroupId).execute()
            statePublisher.onNext(WordListState.GetWordListSucceeded(words))
        }.invokeOnCompletion {
            statePublisher.onNext(WordListState.GetWordListLoading(false))
        }
    }
}