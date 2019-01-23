package ru.jjba.jr2.presentation.viewmodel.vocab.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.jjba.jr2.data.repository.GroupDbRepository
import ru.jjba.jr2.data.repository.WordDbRepository
import ru.jjba.jr2.domain.entity.Group
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.presentation.ui.vocab.word.list.WordListFragmentDirections
import ru.jjba.jr2.presentation.viewmodel.BaseViewModel
import ru.jjba.jr2.presentation.viewmodel.ViewModelEvent
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
        val direction = WordListFragmentDirections.actionWordListToWordDetail().apply {
            wordId = word.id
        }
        navToWordDetailEvent.value = ViewModelEvent(direction)
    }

    private fun fetchData() {
        areWordsLoading.postValue(true)
        launch {
            delay(2000L)
            withContext(Dispatchers.Default) {
                wordGroup.postValue(groupRepository.getById(wordGroupIdArg))
                words.postValue(wordRepository.getWordsByGroupId(wordGroupIdArg))
            }
        }.invokeOnCompletion {
            areWordsLoading.postValue(false)
        }
    }

    private fun clearData() {
        wordGroup.value = null
        words.value = emptyList()
    }
}