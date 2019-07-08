package io.github.jr2team.jr2android.presentation.vocabulary.word.group._viewmodel

import androidx.navigation.NavDirections
import io.github.jr2team.jr2android.domain.room.select.SectionWithGroups

sealed class WordGroupState {
    class NavigateToWordListFragment(val direction: NavDirections) : WordGroupState()
    class GetWordSectionsLoading(val isLoading: Boolean) : WordGroupState()
    class GetWordSectionsSucceeded(val sections: List<SectionWithGroups>) : WordGroupState()
}