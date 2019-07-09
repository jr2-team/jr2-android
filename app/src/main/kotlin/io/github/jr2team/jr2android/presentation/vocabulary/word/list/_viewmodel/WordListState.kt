package io.github.jr2team.jr2android.presentation.vocabulary.word.list._viewmodel

import androidx.navigation.NavDirections
import io.github.jr2team.jr2android.domain.usecase.vocabular.word.list.GetWordsUseCase

sealed class WordListState {
    class GetWordListLoading(val isLoading: Boolean) : WordListState()
    class GetWordListSucceeded(val result: GetWordsUseCase.Result) : WordListState()
    class NavToWord(val navDirection: NavDirections) : WordListState()
}