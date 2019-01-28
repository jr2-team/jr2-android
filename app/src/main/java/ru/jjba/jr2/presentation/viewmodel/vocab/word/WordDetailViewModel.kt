package ru.jjba.jr2.presentation.viewmodel.vocab.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import ru.jjba.jr2.data.repository.WordDbRepository
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.presentation.viewmodel.BaseViewModel
import kotlin.properties.Delegates.observable

class WordDetailViewModel(
        private val wordRepository: WordDbRepository = WordDbRepository()
) : BaseViewModel() {
    private val word = MutableLiveData<Word>()
    private var wordIdArg: Int by observable(Int.MIN_VALUE) { _, oldValue, newValue ->
        if (oldValue != newValue) {
            //word.postValue(wordRepository.getById(newValue))
        }
    }

    fun setArgs(wordId: Int) {
        wordIdArg = wordId
    }

    fun fetchData() = launch {
        word.postValue(wordRepository.getById(wordIdArg).await())
    }

    fun observeWord(): LiveData<Word> = word
}