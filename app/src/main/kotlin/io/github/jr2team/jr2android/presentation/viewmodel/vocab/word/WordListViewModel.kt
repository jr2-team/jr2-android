package io.github.jr2team.jr2android.presentation.viewmodel.vocab.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import io.github.jr2team.jr2android.data.repository.GroupDbRepository
import io.github.jr2team.jr2android.data.repository.WordDbRepository
import io.github.jr2team.jr2android.domain.entity.Group
import io.github.jr2team.jr2android.domain.entity.Word
import io.github.jr2team.jr2android.presentation.ui.vocab.word.list.WordListFragmentDirections
import io.github.jr2team.jr2android.presentation.viewmodel.BaseViewModel
import io.github.jr2team.jr2android.presentation.viewmodel.ViewModelEvent
import kotlin.properties.Delegates.observable

class WordListViewModel(
        private val groupRepository: GroupDbRepository = GroupDbRepository(),
        private val wordRepository: WordDbRepository = WordDbRepository()
) : BaseViewModel() {
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

    private fun fetchData() = launch {
        areWordsLoading.postValue(true)
        delay(1000L)
        wordGroup.postValue(groupRepository.getById(wordGroupIdArg).await())
        words.postValue(wordRepository.getWordsByGroupId(wordGroupIdArg).await())
    }.invokeOnCompletion {
        areWordsLoading.postValue(false)
    }

    private fun clearData() {
        wordGroup.value = null
        words.value = emptyList()
    }
}