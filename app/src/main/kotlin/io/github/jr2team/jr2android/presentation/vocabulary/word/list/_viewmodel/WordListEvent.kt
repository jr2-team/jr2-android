package io.github.jr2team.jr2android.presentation.vocabulary.word.list._viewmodel

import io.github.jr2team.jr2android.domain.room_entities.entity.Word

sealed class WordListEvent {
    class OnWordClicked(val word: Word) : WordListEvent()
    class OnGetWordListRequested(val wordGroupId: Int) : WordListEvent()
}