package io.github.jr2team.jr2android.presentation.vocabulary.word.group._viewmodel

import io.github.jr2team.jr2android.domain.entity.Group

sealed class WordGroupEvent {
    object OnGetSectionsRequested : WordGroupEvent()
    class OnWordGroupClicked(val wordGroup: Group) : WordGroupEvent()
}