package ru.jjba.jr2.presentation.viewmodel.vocab.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import ru.jjba.jr2.data.repository.WordDbRepository
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.presentation.ui.vocab.word.list.WordListFragmentDirections
import ru.jjba.jr2.presentation.viewmodel.BaseViewModel
import ru.jjba.jr2.presentation.viewmodel.ViewModelEvent
import kotlin.properties.Delegates.observable

class WordListViewModel(
        private val wordRepository: WordDbRepository = WordDbRepository()
) : BaseViewModel() {
    private var wordListIdArg by observable(0) { _, oldValue, newValue ->
        if (oldValue != newValue) getWords()
    }
    private val words = MediatorLiveData<List<Word>>().apply { value = emptyList() }
    private val areWordsLoading = MutableLiveData<Boolean>()
    private val navToWordDetailEvent = MutableLiveData<ViewModelEvent<NavDirections>>()

    fun setArgs(wordListId: Int) {
        wordListIdArg = wordListId
        getWords()
    }

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

    fun onWordSearch(query: String): LiveData<List<Word>> =
            wordRepository.getByQuery(query)

    private fun getWords() {
        words.addSource(wordRepository.getAll(), words::setValue)
    }
}