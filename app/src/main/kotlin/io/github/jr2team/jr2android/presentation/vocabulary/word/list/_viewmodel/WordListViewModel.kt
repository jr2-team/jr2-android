package io.github.jr2team.jr2android.presentation.vocabulary.word.list._viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import io.github.jr2team.jr2android.common.ViewModelEvent
import io.github.jr2team.jr2android.data.repository.GroupDbRepository
import io.github.jr2team.jr2android.data.repository.WordDbRepository
import io.github.jr2team.jr2android.domain.entity.Group
import io.github.jr2team.jr2android.domain.entity.Word
import io.github.jr2team.jr2android.presentation.vocabulary.word.list._view.WordListFragmentDirections
import kotlinx.coroutines.launch
import kotlin.properties.Delegates.observable

class WordListViewModel(
    private val groupRepository: GroupDbRepository = GroupDbRepository(),
    private val wordRepository: WordDbRepository = WordDbRepository()
) : ViewModel() {
    private var wordGroupIdArg: Int by observable(Int.MIN_VALUE) { _, oldValue, newValue ->
        if (oldValue != newValue) {
            clearData()
            fetchData()
        }
    }

    private val wordGroup = MutableLiveData<Group?>()
    private val words = MutableLiveData<List<Word>>()
    private val areWordsLoading = MutableLiveData<Boolean>()
    private val navToWordDetailEvent = MutableLiveData<ViewModelEvent<NavDirections>>()

    fun setArgs(wordGroupId: Int) {
        wordGroupIdArg = wordGroupId
    }

    fun observeWordGroup(): LiveData<Group?> = wordGroup
    fun observeWords(): LiveData<List<Word>> = words
    fun observeWordsIsLoading(): LiveData<Boolean> = areWordsLoading
    fun observeNavToWordDetailEvent(): LiveData<ViewModelEvent<NavDirections>> =
        navToWordDetailEvent

    fun onWordClick(word: Word) {
        val direction = WordListFragmentDirections.actionWordListToWordDetail(wordId = word.id)
        navToWordDetailEvent.value = ViewModelEvent(direction)
    }

    private fun fetchData() = viewModelScope.launch {
        areWordsLoading.postValue(true)
        wordGroup.postValue(groupRepository.getById(wordGroupIdArg))
        words.postValue(wordRepository.getWordsByGroupId(wordGroupIdArg))
    }.invokeOnCompletion {
        areWordsLoading.postValue(false)
    }

    private fun clearData() {
        wordGroup.value = null
        words.value = emptyList()
    }
}