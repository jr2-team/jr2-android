package io.github.jr2team.jr2android.presentation.vocabulary.word.detail._viewmodel

import io.github.jr2team.jr2android.domain.room_entities.entity.Word

sealed class WordDetailState {
    class GetWordLoading(val isLoading: Boolean) : WordDetailState()
    class GetWordSucceeded(val word: Word) : WordDetailState()
}