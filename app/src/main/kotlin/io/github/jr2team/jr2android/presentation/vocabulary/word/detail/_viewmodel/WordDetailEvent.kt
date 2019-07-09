package io.github.jr2team.jr2android.presentation.vocabulary.word.detail._viewmodel

sealed class WordDetailEvent {
    class OnGetWordRequested(val wordId: Int) : WordDetailEvent()
}