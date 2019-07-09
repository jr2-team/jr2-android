package io.github.jr2team.jr2android.presentation.vocabulary.word.detail._viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.jr2team.jr2android.data.repository.WordDbRepository
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.launch

class WordDetailViewModel(
    private val wordRepository: WordDbRepository = WordDbRepository()
) : ViewModel() {
    val statePublisher = PublishSubject.create<WordDetailState>()

    fun onEvent(event: WordDetailEvent) = when (event) {
        is WordDetailEvent.OnGetWordRequested -> onGetWordRequested(event.wordId)
    }

    private fun onGetWordRequested(wordId: Int) {
        statePublisher.onNext(WordDetailState.GetWordLoading(true))
        viewModelScope.launch {
            val word = wordRepository.getById(wordId)
            statePublisher.onNext(WordDetailState.GetWordSucceeded(word))
        }.invokeOnCompletion {
            statePublisher.onNext(WordDetailState.GetWordLoading(false))
        }
    }
}