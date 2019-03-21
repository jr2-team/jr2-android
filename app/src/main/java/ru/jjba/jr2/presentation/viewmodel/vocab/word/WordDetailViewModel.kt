package ru.jjba.jr2.presentation.viewmodel.vocab.word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import ru.jjba.jr2.data.repository.WordDbRepository
import ru.jjba.jr2.domain.entity.Word
import ru.jjba.jr2.presentation.viewmodel.base.BaseViewModel

class WordDetailViewModel(
        private val wordRepository: WordDbRepository = WordDbRepository()
) : BaseViewModel() {
    private var wordIdArg = 0

    private val word = MutableLiveData<Word>()

    fun setArgs(wordId: Int) {
        wordIdArg = wordId
        fetchData()
    }

    fun observeWord(): LiveData<Word> = word

    private fun fetchData() = launch {
        word.postValue(wordRepository.getById(wordIdArg).await())
    }
}